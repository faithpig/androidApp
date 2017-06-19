package com.whu.faithfish.Task;

import android.os.AsyncTask;
import android.os.Environment;
import com.whu.faithfish.listener.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by faithfish on 17-6-19.
 */

public class DownLoadTask extends AsyncTask<String,Integer,Integer> {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCLED = 3;

    private DownloadListener downloadListener;

    private boolean isCanceled = false;
    private boolean isPaused = false;

    private int lastProgress;

    public DownLoadTask(DownloadListener downloadListener){
        this.downloadListener = downloadListener;
    }

    @Override
    protected Integer doInBackground(String... strings) {//...代表可变长参数
        InputStream in = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try{
            long downloadLength = 0;
            String url = strings[0];
            String fileName = url.substring(url.lastIndexOf('/'));
            //外部存储的公有文件,不随应用删除而删除
            String dir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(dir+fileName);
            if(file.exists()){
                downloadLength = file.length();
            }
            long contentLength = getContentLength(url);
            if(contentLength == 0)
                return TYPE_FAILED;
            else if(contentLength == downloadLength)
                return TYPE_SUCCESS;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE","bytes="+downloadLength+"-")
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if(response!=null){
                in = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while((len = in.read(b))!=-1){
                    if(isCanceled){
                        return  TYPE_CANCLED;
                    }else if(isPaused){
                        return  TYPE_PAUSED;
                    }else{
                        total+=len;
                    }
                    saveFile.write(b, 0 , len);
                    int progress = (int)((total+downloadLength)*100/contentLength);
                    publishProgress(progress);
                }
            }
        }catch(Exception e){
            e.printStackTrace();

        }finally {
            try{
                if(in!=null){
                    in.close();
                }
                if(saveFile!=null){
                    saveFile.close();
                }
                if(isCanceled && file!=null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_SUCCESS;
    }

    private long getContentLength(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();//链式调用
        Response response = client.newCall(request).execute();
        if(response!=null&&response.isSuccessful()){
            long l = response.body().contentLength();
            response.body().close();
            return l;
        }
        return 0;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress > lastProgress){
            downloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                downloadListener.onPaused();
                break;
            case TYPE_CANCLED:
                downloadListener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public void cancelDownload(){
        isCanceled = true;
    }
}

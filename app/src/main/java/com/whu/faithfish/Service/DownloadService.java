package com.whu.faithfish.Service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.whu.faithfish.Task.DownLoadTask;
import com.whu.faithfish.androidapp.DownLoadActivity;
import com.whu.faithfish.androidapp.R;
import com.whu.faithfish.listener.DownloadListener;

import java.io.File;

/**
 * Created by faithfish on 17-6-19.
 */

public class DownloadService extends Service{

    private DownLoadTask downLoadTask;
    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("下载中...",progress));
        }

        @Override
        public void onSuccess() {
            downLoadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("下载成功！", -1));
        }

        @Override
        public void onFailed() {
            downLoadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("下载失败！", -1));
        }

        @Override
        public void onPaused() {
            downLoadTask = null;
        }

        @Override
        public void onCanceled() {
            downLoadTask = null;
            stopForeground(true);
        }
    };

    public class DownloadBinder extends Binder{

        public void startDownload(String url){
            if(downLoadTask==null){
                downloadUrl = url;
                downLoadTask = new DownLoadTask(listener);
                downLoadTask.execute(downloadUrl);//开始下载
                startForeground(1, getNotification("正在下载中...", 0));
            }
        }

        public void pauseDownload(){
            if(downLoadTask!=null){
                downLoadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if(downLoadTask!=null){
                downLoadTask.cancelDownload();
            }else{
                if(downloadUrl!=null){
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String dir = Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            .getPath();
                    File file = new File(dir+fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                }
            }
        }
    }

    private DownloadBinder binder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress){
        Intent intent = new Intent(this, DownLoadActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if(progress > 0){
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }
}

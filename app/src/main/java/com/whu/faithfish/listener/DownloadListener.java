package com.whu.faithfish.listener;

/**
 * 下载文件的回调方法
 * Created by faithfish on 17-6-19.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}

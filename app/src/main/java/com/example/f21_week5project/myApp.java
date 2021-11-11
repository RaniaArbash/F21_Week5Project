package com.example.f21_week5project;

import android.app.Application;

public class myApp extends Application {
    private StorageManager storageManager = new StorageManager();

    public ExternalStorageManager getEStorageManger() {
        return EStorageManger;
    }

    private ExternalStorageManager EStorageManger = new ExternalStorageManager();

    public StorageManager getStorageManager() {
        return storageManager;
    }
}

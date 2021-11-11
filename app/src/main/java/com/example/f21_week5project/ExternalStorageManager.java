package com.example.f21_week5project;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExternalStorageManager {

String fileName = "tasks.txt";
    public void saveNewTaskPrivateExternal(Context context, ToDo task){
        OutputStream os = null;
        try {
            File folder = context.getExternalFilesDir("toDoTasksExternalData");// Folder Name
            File myFile = new File(folder, fileName);// Filename
            OutputStream outStream = new FileOutputStream(myFile);
            outStream.write(task.task.getBytes());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getTasksFromPrivateExternal(Context activity) {
        File folder =  activity.getExternalFilesDir("toDoTasksExternalData"); // Folder Name
        File myFile = new File(folder, fileName); // Filename
        String text = getdata(myFile);
        if (text != null) {
            return text;
        } else {
            return "no Data";
        }
    }

    private String getdata(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}

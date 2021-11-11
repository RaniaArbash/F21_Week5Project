package com.example.f21_week5project;

import android.app.Activity;
import android.content.Context;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager {
    String filename = "tasks.txt";


    public void resetTheStorage(Activity activity){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE); // reset
            fileOutputStream.write("".getBytes());

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

   // 90
    public void saveNewToDOInInternalPrivateFile(Activity activity, ToDo task){
        FileOutputStream fileOutputStream = null;
try {
     fileOutputStream = activity.openFileOutput(filename, Context.MODE_APPEND); // continue writting
    fileOutputStream.write((task.toString()+"$").getBytes());

}catch (Exception ex) {
    ex.printStackTrace();
}finally {
    try {
        fileOutputStream.close();
    }catch (IOException ex){
        ex.printStackTrace();
    }
}
        // internal Stream

    }

    public ArrayList<ToDo> getTasksFromInternalPrivateFile(Activity activity)  {
        FileInputStream fileInputStream = null;
        int read;
        ArrayList<ToDo> list = new ArrayList<>(0);
        StringBuffer buffer = new StringBuffer();
        try {
            fileInputStream = activity.openFileInput(filename);
            while(( read = fileInputStream.read() )!= -1 ){
                buffer.append((char)read);
            }
          list =  fromStringToList( buffer.toString());
        }catch (IOException ex){ex.printStackTrace();}
        finally {
            try {
                fileInputStream.close();
            }catch (IOException ex){ex.printStackTrace();}

        }
        return list;
    }

    // fix the door - Nov 11, 2021 $ fix the window - Nov 12, 2021
    private ArrayList<ToDo> fromStringToList(String str){ // str come from the file
        // there is a $ between tasks
        ArrayList<ToDo> list = new ArrayList(0);
        int index = 0;
        for (int i = 0 ; i < str.toCharArray().length ; i++){
            if (str.toCharArray()[i] == '$'){
                String task = str.substring(index,i - 1);
                list.add(ToDo.toTask(task));
                index = i+1;
            }
        }

        return list;

    }

}

package com.example.f21_week5project;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDo implements Parcelable {
    public  String task;
    public  String date;

    public ToDo(String task, String date) {
        this.task = task;
        this.date = date;
    }

    public String toString(){
        return this.task + "-" + this.date;
    }

    // fix the door - Nov 11, 2021
    // task
    // date
    public static ToDo toTask(String task_string){
        String t = "",d = "";
        for (int i = 0 ; i< task_string.toCharArray().length;i++) {
            if (task_string.toCharArray()[i] == '-') {
                t = task_string.substring(0,i);
                d = task_string.substring(i+1, task_string.toCharArray().length);
            }
        }
       return new ToDo(t,d);

    }
    protected ToDo(Parcel in) {
        task = in.readString();
        date = in.readString();
    }

    public static final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(task);
        parcel.writeString(date);
    }
}

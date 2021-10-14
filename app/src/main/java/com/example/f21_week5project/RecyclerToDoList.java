package com.example.f21_week5project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerToDoList extends AppCompatActivity {
ArrayList<ToDo> listOfToDos;
RecyclerView recyclerlist;
ToDoRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_to_do_list);
        recyclerlist = (RecyclerView) findViewById(R.id.recyclerlist);

        if (getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            listOfToDos = bundleFromMainActivity.getParcelableArrayList("todolist");
            Log.d("the list",listOfToDos.size() + "");

        }
        recyclerlist.setLayoutManager(new LinearLayoutManager(this));
         adapter = new ToDoRecyclerAdapter(listOfToDos, this, new ToDoRecyclerAdapter.OnItemClickListner() {
             @Override
             public void onToDoClicked(ToDo item) {
                 Toast.makeText(getApplicationContext(),item.task, Toast.LENGTH_LONG).show();
             }
         });
        recyclerlist.setAdapter(adapter);
//        recyclerlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"hello", Toast.LENGTH_LONG).show();
//            }
//        });

    }
}
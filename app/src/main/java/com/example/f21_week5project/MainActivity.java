package com.example.f21_week5project;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView todoList;
    //public ActivityResultLauncher<Intent> addToDoActivityResultLauncher;
    TodoAdapter adapter;
    ArrayList<ToDo> listOfDodos = new ArrayList<>(0);
     ActivityResultLauncher<Intent> newToDOActivityResultLauncher;
    StorageManager storageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storageManager = ((myApp) getApplication()).getStorageManager();
        todoList = (ListView) findViewById(R.id.simpleListView);
        ExternalStorageManager esmanager = ((myApp)getApplication()).getEStorageManger();
        esmanager.saveNewTaskPrivateExternal(this, new ToDo("task to external","today"));
        esmanager.saveNewTaskPrivateExternal(this, new ToDo("task to external2","tomorrow"));

        String tasksFromEStorage = esmanager.getTasksFromPrivateExternal(this);


        listOfDodos = storageManager.getTasksFromInternalPrivateFile(MainActivity.this);
         adapter = new TodoAdapter(this, listOfDodos);
        todoList.setAdapter(adapter);


        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),listOfDodos.get(i).task,Toast.LENGTH_LONG).show();
            }
        });




        newToDOActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK )  {
                            // There are no request codes
                            Intent data = result.getData();
                            Bundle extra = data.getExtras();
                            ToDo newTodo =  extra.getParcelable("newTodo");
                            listOfDodos.add(newTodo);
                            storageManager.saveNewToDOInInternalPrivateFile(MainActivity.this,newTodo);
                            adapter.toDos = listOfDodos;
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_mune,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()){
             case R.id.addNewTod:{

                 Intent myIntent = new Intent(this,AddNewTodoActivity.class);
                 newToDOActivityResultLauncher.launch(myIntent);

                 break;
             }
             case R.id.recyclerID:{
                 Intent myIntent = new Intent(this,RecyclerToDoList.class);
                 Bundle bundle = new Bundle();
                 bundle.putParcelableArrayList("todolist",listOfDodos);
                 myIntent.putExtra("bundle",bundle);
                 startActivity(myIntent);
             }
             case R.id.reset:{
                 storageManager.resetTheStorage(MainActivity.this);
                 adapter.notifyDataSetChanged();
             }
             case R.id.cancel:{

                 break;
             }
         }
         return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /// we need to save the list of todos.
        outState.putParcelableArrayList("listoftasks", listOfDodos);
    }
}

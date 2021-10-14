package com.example.f21_week5project;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.viewHolder> {
    ArrayList<ToDo> listOfToDos;
    Context mContext;

    public interface OnItemClickListner{
        void onToDoClicked(ToDo item);
    }

    private final OnItemClickListner listner;

    public ToDoRecyclerAdapter(ArrayList<ToDo> listOfToDos, Context context,OnItemClickListner listnerFromActivity) {
        this.listOfToDos = listOfToDos;
        this.mContext = context;
        listner = listnerFromActivity;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView taskText;
        private final TextView dateText;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.task);
            dateText = itemView.findViewById(R.id.task_date);
        }

        public TextView getTaskText() {
            return taskText;
        }

        public TextView getDateText() {
            return dateText;
        }
    }

    @NonNull
    @Override
    public ToDoRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater myInflater =  LayoutInflater.from(mContext);
      View view =  myInflater.inflate(R.layout.recycler_view_list_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoRecyclerAdapter.viewHolder holder, int position) {
        holder.getTaskText().setText(listOfToDos.get(position).task);
        holder.getDateText().setText(listOfToDos.get(position).date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onToDoClicked(listOfToDos.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfToDos.size();
    }
}

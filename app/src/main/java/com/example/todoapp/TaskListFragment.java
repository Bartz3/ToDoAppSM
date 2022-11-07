package com.example.todoapp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    public static final String KEY_EXTRA_TASK_ID = "key_extra_task_id";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        recyclerView = view.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateView();
        return view;
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;
        private TextView dateTextView;
        private TextView detailsTextView;
        private ImageView iconImageView;
        private CheckBox isDoneCheckBox;
        private Task task = new Task();

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            itemView.setOnClickListener(this);

            task.setCategory(Category.STUDIES);
            task.setDone(true);
            nameTextView = itemView.findViewById(R.id.task_item_name);
            dateTextView = itemView.findViewById(R.id.task_item_date);
            detailsTextView = itemView.findViewById(R.id.task_item_details);
            isDoneCheckBox=itemView.findViewById(R.id.isDoneCheckBox);
            iconImageView = itemView.findViewById(R.id.imageView);

            dateTextView.setText("Abcbcbc");
            isDoneCheckBox.setChecked(task.isDone());
            if(task.isDone()){
                nameTextView.setText(task.getName());
                nameTextView.setPaintFlags(nameTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if(task.getCategory().equals(Category.HOME)){
                iconImageView.setImageResource(R.drawable.ic_home);
            }else{// if(task.getCategory().equals(Category.STUDIES))
                iconImageView.setImageResource(R.drawable.ic_studies);
            }
        }

        public void bind(Task task) {
            this.task = task;
            nameTextView.setText(task.getName());
            dateTextView.setText(task.getDate().toString());
            detailsTextView.setText(task.getDetails());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
            startActivity(intent);
        }

        public CheckBox getCheckBox() {
            return isDoneCheckBox;
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks) {
            this.tasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = tasks.get(position);
            CheckBox checkBox = holder.getCheckBox();
            checkBox.setChecked(tasks.get(position).isDone());
            checkBox.setOnCheckedChangeListener((buttonView,isChecked)->
                    tasks.get(holder.getBindingAdapterPosition()).setDone(isChecked));
            holder.bind(task);

        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }
    }

    private void updateView() {
        TaskStorage taskStorage = TaskStorage.getInstance();
        List<Task> tasks = taskStorage.getTasks();

        if (adapter == null) {
            adapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }
}

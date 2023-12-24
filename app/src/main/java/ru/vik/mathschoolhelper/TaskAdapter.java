package ru.vik.mathschoolhelper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.vik.mathschoolhelper.Room.Task;
import ru.vik.mathschoolhelper.Room.TaskDao;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Task> tasks;
    private final TaskDao taskDao;

    TaskAdapter(Context context, List<Task> tasks, TaskDao taskDao) {
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
        this.taskDao = taskDao;
    }
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        if (!task.taskImageUrl.isEmpty()) {
            Picasso.get().load(task.taskImageUrl).into(holder.taskImg);
        }
        if (task.isDone != null){
            holder.answerText.setVisibility(View.VISIBLE);
            holder.answerButton.setEnabled(false);
            holder.answerEditText.setEnabled(false);
            holder.answerEditText.setText(task.userAnswer);
            if (task.isDone.equals("0")){
                holder.answerText.setText("Ответ верный!");
                holder.answerText.setTextColor(Color.parseColor("#67db65"));
            }
            else if (task.isDone.equals("1")){
                holder.answerText.setText("Ответ неверный! Правильный ответ: " + task.answer);
                holder.answerText.setTextColor(Color.parseColor("#ed2828"));
            }
        }
        else {
            holder.answerText.setVisibility(View.GONE);
            holder.answerEditText.setText("");
            holder.answerButton.setEnabled(true);
            holder.answerEditText.setEnabled(true);
        }

        holder.taskText.setText(task.text);
        holder.answerButton.setOnClickListener(v -> {
            holder.answerButton.setEnabled(false);
            holder.answerEditText.setEnabled(false);
            if (!holder.answerEditText.getText().toString().equals(task.answer)){
                taskDao.updateTask(task.id, holder.answerEditText.getText().toString(), "1");
                holder.answerText.setText("Ответ неверный! Правильный ответ: " + task.answer);
                holder.answerText.setTextColor(Color.parseColor("#ed2828"));
            }
            else {
                taskDao.updateTask(task.id, holder.answerEditText.getText().toString(), "0");
                holder.answerText.setText("Ответ верный!");
                holder.answerText.setTextColor(Color.parseColor("#67db65"));
            }
            holder.answerText.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView taskImg;
        final TextView taskText, answerText;
        final EditText answerEditText;
        final Button answerButton;
        ViewHolder(View view){
            super(view);
            taskImg = view.findViewById(R.id.taskImg);
            taskText = view.findViewById(R.id.taskText);
            answerText = view.findViewById(R.id.answerTextView);
            answerEditText = view.findViewById(R.id.taskAnswerEditText);
            answerButton = view.findViewById(R.id.setAnswerButton);
        }
    }

}

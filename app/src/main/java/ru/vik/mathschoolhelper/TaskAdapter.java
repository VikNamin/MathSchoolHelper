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
    private List<Task> tasks;
    private final TaskDao taskDao;
    private final int typeNum;

    TaskAdapter(Context context, List<Task> tasks, TaskDao taskDao, int typeNum) {
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
        this.taskDao = taskDao;
        this.typeNum = typeNum;
    }
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (typeNum < 13){
            view = inflater.inflate(R.layout.list_task_p1_item, parent, false);
        }
        else {
            view = inflater.inflate(R.layout.list_task_p2_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        Log.d(MainActivity.TAG, "onBindViewHolder: NewBind on id " + task.id + ", userAnswer is " + task.userAnswer);
        if (!task.taskImageUrl.isEmpty()) {
            Picasso.get().load(task.taskImageUrl).into(holder.taskImg);
        }
        if (typeNum < 13){
            if (task.isDone != null){
                holder.answerText.setVisibility(View.VISIBLE);
                holder.answerP1Button.setEnabled(false);
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
                holder.answerP1Button.setEnabled(true);
                holder.answerEditText.setEnabled(true);
            }

            holder.taskText.setText(task.text);
            holder.answerP1Button.setOnClickListener(v -> {
                holder.answerP1Button.setEnabled(false);
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
                tasks = taskDao.getTaskByTypeNum(Integer.toString(typeNum));
            });
        }
        else {
            if (!task.answer.isEmpty()) {
                Picasso.get().load(task.answer).into(holder.taskP2Answer);
            }
            if (task.isDone == null){
                holder.answerP2TextView.setVisibility(View.GONE);
                holder.taskP2Answer.setVisibility(View.GONE);
                holder.showAnswerP2Button.setEnabled(true);
            }
            else {
                holder.answerP2TextView.setVisibility(View.VISIBLE);
                holder.taskP2Answer.setVisibility(View.VISIBLE);
                holder.showAnswerP2Button.setEnabled(false);
            }
            holder.showAnswerP2Button.setOnClickListener(v -> {
                taskDao.updateTask(task.id, "userAnswer", "done");
                holder.answerP2TextView.setVisibility(View.VISIBLE);
                holder.taskP2Answer.setVisibility(View.VISIBLE);
                holder.showAnswerP2Button.setEnabled(false);
                tasks = taskDao.getTaskByTypeNum(Integer.toString(typeNum));
            });
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView taskImg, taskP2Answer;
        final TextView taskText, answerText, answerP2TextView;
        final EditText answerEditText;
        final Button answerP1Button, showAnswerP2Button;
        ViewHolder(View view){
            super(view);
            taskImg = view.findViewById(R.id.taskImg);
            taskP2Answer = view.findViewById(R.id.taskAnswerP2Img);
            taskText = view.findViewById(R.id.taskText);
            answerP2TextView = view.findViewById(R.id.answerP2TextView);
            answerText = view.findViewById(R.id.answerTextView);
            answerEditText = view.findViewById(R.id.taskAnswerEditText);
            answerP1Button = view.findViewById(R.id.setAnswerButton);
            showAnswerP2Button = view.findViewById(R.id.showAnswerButton);
        }
    }

}

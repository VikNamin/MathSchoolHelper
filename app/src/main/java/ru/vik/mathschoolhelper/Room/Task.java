package ru.vik.mathschoolhelper.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task_type")
    public String taskType;

    @ColumnInfo(name = "task_text")
    public String text;

    @ColumnInfo(name = "task_image")
    public String taskImageUrl;

    @ColumnInfo(name = "answer")
    public String answer;

    @ColumnInfo(name = "is_done")
    public String isDone;

    @ColumnInfo(name = "user_answer")
    public String userAnswer;

}

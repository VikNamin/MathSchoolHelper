package ru.vik.mathschoolhelper.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE task_type = :taskType")
    List<Task> getTaskByTypeNum(String taskType);

    @Query("SELECT * FROM task WHERE id = :id")
    Task getById(int id);

    @Query("UPDATE task SET is_done = :isDone, user_answer = :userAnswer WHERE id = :id")
    void updateTask(int id, String userAnswer, String isDone);


    @Insert
    void insertSample(Task task);
}

package ru.vik.mathschoolhelper.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LessonDao {

    @Query("SELECT * FROM lesson")
    List<Lesson> getAll();

    @Query("SELECT * FROM lesson WHERE type_num LIKE :typeNum")
    List<Lesson> getByTypeNum(String typeNum);

    @Query("SELECT * FROM lesson WHERE id = :id")
    Lesson getById(int id);

    @Insert
    void insertSample(Lesson lesson);
}

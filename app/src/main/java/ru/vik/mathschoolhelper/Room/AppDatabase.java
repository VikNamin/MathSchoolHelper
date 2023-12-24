package ru.vik.mathschoolhelper.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Lesson.class, Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LessonDao lessonDao();
    public abstract TaskDao taskDao();

}

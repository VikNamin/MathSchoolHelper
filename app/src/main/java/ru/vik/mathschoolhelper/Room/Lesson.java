package ru.vik.mathschoolhelper.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "html_url")
    public String htmlUrl;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "preview_url")
    public String previewUrl;

    @ColumnInfo(name = "type_num")
    public String typeNum;
}

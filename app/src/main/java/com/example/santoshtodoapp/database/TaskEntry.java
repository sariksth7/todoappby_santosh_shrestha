package com.example.santoshtodoapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "task")
public class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String target;
    private String description;
    private int priority;

    @ColumnInfo(name="updated_at")
    private Date updatedAt;

    @Ignore
    public TaskEntry(String title, String target, String description, int priority, Date updatedAt) {
        this.title = title;
        this.target = target;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public TaskEntry(int id,String title, String target, String description, int priority, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.target = target;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}

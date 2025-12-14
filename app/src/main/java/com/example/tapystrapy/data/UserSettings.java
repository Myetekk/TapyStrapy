package com.example.tapystrapy.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_settings")
public class UserSettings {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String key;
    public String value;

    public UserSettings(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

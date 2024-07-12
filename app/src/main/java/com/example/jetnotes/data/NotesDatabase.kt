package com.example.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetnotes.model.Note
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.jetnotes.util.DateConverter
import com.example.jetnotes.util.UUIDConverter
import java.util.Date
import java.util.UUID



@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDAO(): NoteDatabaseDAO
}
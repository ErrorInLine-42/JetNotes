package com.example.jetnotes.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnotes.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID


@Dao
interface NoteDatabaseDAO {

    @Query("SELECT * from notes_table")
    fun GetNotes(): Flow<List<Note>>

    @Query("SELECT * from notes_table where id = :id")
    suspend fun GetNoteByID(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)
}



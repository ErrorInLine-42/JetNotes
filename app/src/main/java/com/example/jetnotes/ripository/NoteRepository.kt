package com.example.jetnotes.ripository

import com.example.jetnotes.data.NoteDatabaseDAO
import com.example.jetnotes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class NoteRepository @Inject constructor(private val noteDatabaseDAO: NoteDatabaseDAO) {
    suspend fun updateNote(note: Note) = noteDatabaseDAO.update(note)
    suspend fun deleteAllNotes() = noteDatabaseDAO.deleteAll()
    suspend fun addNote(note: Note) {
        noteDatabaseDAO.insert(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDatabaseDAO.delete(note)
    }

    fun getAllNotes(): Flow<List<Note>> {
        return noteDatabaseDAO.GetNotes()
            .flowOn(Dispatchers.IO)
            .conflate()
    }
}


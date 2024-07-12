package com.example.jetnotes

import com.example.jetnotes.data.NoteDatabaseDAO
import com.example.jetnotes.model.Note
import com.example.jetnotes.ripository.NoteRepository
import com.example.jetnotes.screen.NoteViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

class MockNoteDatabaseDAO : NoteDatabaseDAO {
    private val notesList = MutableStateFlow<List<Note>>(
        listOf(
            Note(
                id = UUID.randomUUID(),
                title = "Sample Note 1",
                description = "This is a sample note description",
                entryDate = java.util.Date()
            ),
            Note(
                id = UUID.randomUUID(),
                title = "Sample Note 2",
                description = "Another sample note description",
                entryDate = java.util.Date()
            )
        )
    )

    override suspend fun insert(note: Note) {
        notesList.value = notesList.value + note
    }

    override suspend fun update(note: Note) {
        notesList.value = notesList.value.map {
            if (it.id == note.id) note else it
        }
    }

    override suspend fun delete(note: Note) {
        notesList.value = notesList.value - note
    }

    override suspend fun deleteAll() {
        notesList.value = emptyList()
    }

    override fun GetNotes(): Flow<List<Note>> = notesList
    override suspend fun GetNoteByID(id: String): Note {
        TODO("Not yet implemented")
    }

    suspend fun GetNoteByID(id: UUID): Note {
        return notesList.value.first { it.id == id }
    }
}

class MockNoteRepository(noteDatabaseDAO: NoteDatabaseDAO) : NoteRepository(noteDatabaseDAO)

class MockNoteViewModel : NoteViewModel(MockNoteRepository(MockNoteDatabaseDAO()))

package com.example.jetnotes.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnotes.model.Note
import com.example.jetnotes.ripository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    protected val _noteList = MutableStateFlow<List<Note>>(emptyList())
    open val noteList: StateFlow<List<Note>> = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                _noteList.value = listOfNotes
                Log.d("NoteViewModel", "Notes updated: $listOfNotes")
            }

        }
    }
    // In NoteViewModel
    fun collectNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                _noteList.value = listOfNotes
            }
        }
    }

    fun addNote(note: Note): Job = viewModelScope.launch {
        repository.addNote(note)
        Log.d("NoteViewModel", "Note added: $note")
    }

    fun deleteNote(note: Note): Job = viewModelScope.launch {
        repository.deleteNote(note)
        Log.d("NoteViewModel", "Note deleted: $note")
    }
}


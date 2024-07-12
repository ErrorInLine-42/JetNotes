package com.example.jetnotes.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnotes.MockNoteViewModel
import com.example.jetnotes.NotesApp
import com.example.jetnotes.components.NoteButton
import com.example.jetnotes.components.NoteInputText
import com.example.jetnotes.model.Note
import com.example.jetnotes.R
import com.example.jetnotes.ui.theme.JetNotesTheme
import com.example.jetnotes.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        color = Color(0xFFFED8B1)
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                modifier = Modifier.padding(top = 5.dp).clip(RoundedCornerShape(6.dp)),
                actions = {
                    Icon(imageVector = Icons.Outlined.Create, contentDescription = "Icon")
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFECB176))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputText(
                    modifier = Modifier.padding(top = 9.dp, bottom = 5.dp),
                    text = title,
                    label = "Title",
                    onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                    }
                )
                NoteInputText(
                    text = description,
                    label = "Add a Note",
                    onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) description = it
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
                NoteButton(text = "Save", onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                        keyboardController?.hide()
                    }
                })
            }
            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note, onNoteClicked = {
                        onRemoveNote(note)
                    })
                }
            }
        }
    }
}

@Composable
fun NoteRow(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFA67B5B),
        tonalElevation = 10.dp,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 15.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.labelMedium)
            Text(text = note.description, style = MaterialTheme.typography.labelSmall)
            Text(text = formatDate(note.entryDate.time), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    val noteViewModel = MockNoteViewModel()
    JetNotesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NotesApp(noteViewModel)
        }
    }
}
package com.example.noteapp.notes.presentation.notes

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.domain.utils.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
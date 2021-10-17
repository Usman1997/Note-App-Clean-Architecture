package com.example.noteapp.notes.domain.use_cases

data class NotesUseCases(
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val getNote: GetNote,
    val getNotes: GetNotes
)
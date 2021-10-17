package com.example.noteapp.notes.domain.use_cases

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.domain.repository.NotesRepository

class DeleteNote(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        notesRepository.deleteNote(note)
    }
}
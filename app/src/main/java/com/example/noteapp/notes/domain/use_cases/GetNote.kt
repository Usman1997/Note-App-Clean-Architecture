package com.example.noteapp.notes.domain.use_cases

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.domain.repository.NotesRepository

class GetNote(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return notesRepository.getNoteById(id)
    }
}
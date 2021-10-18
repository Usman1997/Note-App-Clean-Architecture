package com.example.noteapp.notes.data.repository

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNotesRepository : NotesRepository {

    private val notes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow { emit(notes) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    override suspend fun addNote(note: Note): Long {
        return if (notes.add(note)) note.id?.toLong() ?: -1L else -1L
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }
}
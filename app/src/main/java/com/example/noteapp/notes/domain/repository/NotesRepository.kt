package com.example.noteapp.notes.domain.repository

import com.example.noteapp.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository{

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun addNote(note:Note): Long

    suspend fun deleteNote(note:Note)
}
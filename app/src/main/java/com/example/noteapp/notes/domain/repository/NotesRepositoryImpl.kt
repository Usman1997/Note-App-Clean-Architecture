package com.example.noteapp.notes.domain.repository

import com.example.noteapp.notes.data.data_source.NoteDao
import com.example.noteapp.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val noteDao: NoteDao): NotesRepository {

    override fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

    override suspend fun getNoteById(id: Int) = noteDao.getNote(id = id)

    override suspend fun addNote(note: Note) = noteDao.addNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

}
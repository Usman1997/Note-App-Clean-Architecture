package com.example.noteapp.notes.domain.use_cases

import android.provider.ContactsContract
import com.example.noteapp.notes.data.entity.InvalidNoteException
import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.domain.repository.NotesRepository

class AddNote(
    private val notesRepository: NotesRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isEmpty()) {
            throw InvalidNoteException("Title is missing")
        } else if (note.description.isEmpty()) {
            throw InvalidNoteException("Description is missing")
        }
        notesRepository.addNote(note)
    }
}
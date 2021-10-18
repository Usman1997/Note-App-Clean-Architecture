package com.example.noteapp.notes.domain.use_cases

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.data.repository.FakeNotesRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteNoteTest {

    private lateinit var fakeNotesRepository: FakeNotesRepository
    private lateinit var deleteNote: DeleteNote
    private lateinit var getNotes: GetNotes
    private lateinit var getNote: GetNote

    @Before
    fun setup() {
        fakeNotesRepository = FakeNotesRepository()
        deleteNote = DeleteNote(fakeNotesRepository)
        getNotes = GetNotes(fakeNotesRepository)
        getNote = GetNote(fakeNotesRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'b').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    description = c.toString(),
                    timestamp = index.toLong(),
                    color = index,
                    id = index + 1
                )
            )
        }

        runBlocking {
            notesToInsert.forEach {
                fakeNotesRepository.addNote(it)
            }

        }
    }


    @Test
    fun `delete existing note from list, note deleted`() = runBlocking {
        val note = getNote.invoke(1)
        note?.let {
            deleteNote.invoke(it)
        }

        val notes = getNotes.invoke().first()
        assertThat(notes).doesNotContain(note)
    }
}
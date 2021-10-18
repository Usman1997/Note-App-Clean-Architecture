package com.example.noteapp.notes.domain.use_cases

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.data.repository.FakeNotesRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNoteTest {

    private lateinit var getNote: GetNote
    private lateinit var fakeNotesRepository: FakeNotesRepository

    @Before
    fun setup() {
        fakeNotesRepository = FakeNotesRepository()
        getNote = GetNote(fakeNotesRepository)

        val note = Note(
            title = "Note",
            description = "Note Description",
            timestamp = 1L,
            color = 1,
            id = 1
        )

        runBlocking {
            fakeNotesRepository.addNote(note)
        }
    }

    @Test
    fun `get note by id, note not null`() = runBlocking {
        val note = getNote.invoke(1)
        assertThat(note).isNotNull()
    }

    @Test
    fun `get note by id, note null`() = runBlocking {
        val note = getNote.invoke(2)
        assertThat(note).isNull()
    }
}
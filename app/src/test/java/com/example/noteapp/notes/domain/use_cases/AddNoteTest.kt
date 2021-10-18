package com.example.noteapp.notes.domain.use_cases

import com.example.noteapp.notes.data.entity.InvalidNoteException
import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.data.repository.FakeNotesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat


class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeNotesRepository: FakeNotesRepository

    @Before
    fun setup() {
        fakeNotesRepository = FakeNotesRepository()
        addNote = AddNote(fakeNotesRepository)
    }

    @Test
    fun `add note, throw title exception`() = runBlocking {
        val note = Note(
            title = "",
            description = "",
            timestamp = 1L,
            color = 1
        )

        //With Truth
        try {
            addNote.invoke(note)
            return@runBlocking
        } catch (e: InvalidNoteException) {
            assertThat(e).hasMessageThat().isEqualTo("Title is missing")
        }

        //With Junit Assert
//        val exception = assertThrows(InvalidNoteException::class.java) {
//            runBlocking {
//                addNote.invoke(note)
//            }
//        }
//
//        assertTrue(exception.localizedMessage == "Title is missing")
    }

    @Test
    fun `add note, throw description exception`() = runBlocking {
        val note = Note(
            title = "Note 1",
            description = "",
            timestamp = 1L,
            color = 1
        )

        try {
            addNote.invoke(note)
            return@runBlocking
        } catch (e: InvalidNoteException) {
            assertThat(e).hasMessageThat().isEqualTo("Description is missing")
        }

    }

    @Test
    fun `add note, note add successfully`() = runBlocking {
        val note = Note(
            title = "Note 1",
            description = "This is note 1",
            timestamp = 1L,
            color = 1,
            id = 1
        )

        val noteId = addNote.invoke(note)

        assertThat(noteId.toInt()).isEqualTo(1)
    }
}
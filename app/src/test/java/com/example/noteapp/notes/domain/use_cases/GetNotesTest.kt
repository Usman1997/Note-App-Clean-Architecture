package com.example.noteapp.notes.domain.use_cases

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.data.repository.FakeNotesRepository
import com.example.noteapp.notes.domain.utils.NoteOrder
import com.example.noteapp.notes.domain.utils.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class GetNotesTest {

    private lateinit var getNotes: GetNotes
    private lateinit var fakeNotesRepository: FakeNotesRepository

    @Before
    fun setup() {
        fakeNotesRepository = FakeNotesRepository()
        getNotes = GetNotes(fakeNotesRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    description = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }

        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach {
                fakeNotesRepository.addNote(it)
            }
        }
    }

    @Test
    fun `Order note by title ascending, correct order`() = runBlocking {
        val notes = getNotes.invoke(NoteOrder.Title(OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

    @Test
    fun `Order note by title descending, correct order`() = runBlocking {
        val notes = getNotes.invoke(NoteOrder.Title(OrderType.Descending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i + 1].title)
        }
    }

    @Test
    fun `Order note by date ascending, correct order`() = runBlocking {
        val notes = getNotes.invoke(NoteOrder.Date(OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

    @Test
    fun `Order note by date descending, correct order`() = runBlocking {
        val notes = getNotes.invoke(NoteOrder.Date(OrderType.Descending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i + 1].title)
        }
    }

    @Test
    fun `Order note by color ascending, correct order`() = runBlocking {
        val notes = getNotes.invoke(NoteOrder.Color(OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

    @Test
    fun `Order note by color descending, correct order`() = runBlocking {
        val notes = getNotes.invoke(NoteOrder.Color(OrderType.Descending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i + 1].title)
        }
    }
}
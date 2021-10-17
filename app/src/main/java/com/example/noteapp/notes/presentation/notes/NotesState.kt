package com.example.noteapp.notes.presentation.notes

import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.domain.utils.NoteOrder
import com.example.noteapp.notes.domain.utils.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
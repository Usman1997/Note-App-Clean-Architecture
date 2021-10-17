package com.example.noteapp.notes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.notes.data.entity.Note
import com.example.noteapp.notes.domain.use_cases.NotesUseCases
import com.example.noteapp.notes.domain.utils.NoteOrder
import com.example.noteapp.notes.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var deletedNote: Note? = null
    private var notesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == notesEvent.noteOrder::class &&
                    state.value.noteOrder.orderType == notesEvent.noteOrder.orderType
                ) {
                    return
                }
                getNotes(notesEvent.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNote(note = notesEvent.note)
                    deletedNote = notesEvent.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(deletedNote ?: return@launch)
                    deletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        notesJob?.cancel()
        notesJob = notesUseCases.getNotes(noteOrder = noteOrder)
            .onEach {
                _state.value = state.value.copy(
                    noteOrder = noteOrder,
                    notes = it
                )
            }
            .launchIn(viewModelScope)
    }
}
package com.example.noteapp.notes.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.theme.*
import java.lang.Exception

@Entity
data class Note(
    val title: String,
    val description: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)
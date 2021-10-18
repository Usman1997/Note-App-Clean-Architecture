package com.example.noteapp.notes.data.data_source

import androidx.room.*
import com.example.noteapp.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * from note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from note WHERE id = :id")
    suspend fun getNote(id:Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note): Long

    @Delete
    suspend fun deleteNote(note: Note)

}
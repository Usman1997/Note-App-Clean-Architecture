package com.example.noteapp.notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.notes.data.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract val notesDao: NoteDao

    companion object{
        const val DATABASE_NAME = "com.example.noteapp.db"
   }
}
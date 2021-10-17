package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.notes.data.data_source.NoteDatabase
import com.example.noteapp.notes.domain.repository.NotesRepository
import com.example.noteapp.notes.domain.repository.NotesRepositoryImpl
import com.example.noteapp.notes.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): NoteDatabase =
        Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideNotesRepository(notesDatabase: NoteDatabase): NotesRepository =
        NotesRepositoryImpl(notesDatabase.notesDao)

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NotesRepository): NotesUseCases =
        NotesUseCases(
            AddNote(repository),
            DeleteNote(repository),
            GetNote(repository),
            GetNotes(repository)
        )
}
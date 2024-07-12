package com.example.jetnotes.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.jetnotes.data.NoteDatabaseDAO
import com.example.jetnotes.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDAO(notesDatabase: NotesDatabase): NoteDatabaseDAO = notesDatabase.noteDAO()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NotesDatabase = Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        "notes_DP").fallbackToDestructiveMigration().build()
}
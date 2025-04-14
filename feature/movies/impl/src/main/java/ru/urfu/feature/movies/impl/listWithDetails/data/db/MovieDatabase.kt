package ru.urfu.feature.movies.impl.listWithDetails.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.urfu.feature.movies.impl.listWithDetails.data.dao.MovieDao
import ru.urfu.feature.movies.impl.listWithDetails.data.model.MovieDbEntity

@Database(entities = [MovieDbEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
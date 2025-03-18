package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.dao.MovieDao
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model.MovieDbEntity

@Database(entities = [MovieDbEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
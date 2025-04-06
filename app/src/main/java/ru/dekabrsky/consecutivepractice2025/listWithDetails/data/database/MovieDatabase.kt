package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.dao.MovieDao
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.entity.MovieDbEntity

@Database(entities = [MovieDbEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
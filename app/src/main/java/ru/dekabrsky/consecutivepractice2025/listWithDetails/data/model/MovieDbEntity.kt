package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "movieName") val name: String?,
    @ColumnInfo(name = "movieYear") val year: String?,
    @ColumnInfo(name = "movieType") val type: String?,
    @ColumnInfo(name = "movieUrl") val url: String?,
)
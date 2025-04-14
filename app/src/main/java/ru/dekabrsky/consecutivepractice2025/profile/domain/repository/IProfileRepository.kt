package ru.dekabrsky.consecutivepractice2025.profile.domain.repository

import ru.dekabrsky.consecutivepractice2025.profile.domain.model.ProfileEntity

interface IProfileRepository {
    fun getProfile(): ProfileEntity
}
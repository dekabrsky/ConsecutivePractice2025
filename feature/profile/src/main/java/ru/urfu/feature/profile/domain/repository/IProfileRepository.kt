package ru.urfu.feature.profile.domain.repository

import ru.urfu.feature.profile.domain.model.ProfileEntity

interface IProfileRepository {
    fun getProfile(): ProfileEntity
}
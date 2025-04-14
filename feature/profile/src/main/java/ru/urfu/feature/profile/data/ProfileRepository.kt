package ru.urfu.feature.profile.data

import ru.urfu.feature.profile.domain.model.ProfileEntity
import ru.urfu.feature.profile.domain.repository.IProfileRepository

// сделано на моках, пример полноценной реализации https://github.com/dekabrsky/ConsecutivePractice
class ProfileRepository: IProfileRepository {
    override fun getProfile() =
        ProfileEntity(
            name = "Captain Smolett",
            photoUrl = "https://corsairslegacy.com/mauris/uploads/articles/1667398066-5d181bfd9dcb939aa7a05bb63b75f486_crop.webp"
        )

}
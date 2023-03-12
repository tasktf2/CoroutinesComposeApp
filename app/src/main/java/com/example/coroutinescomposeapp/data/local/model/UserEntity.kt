package com.example.coroutinescomposeapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coroutinescomposeapp.data.base.EntityMapper
import com.example.coroutinescomposeapp.data.base.ModelEntity
import com.example.coroutinescomposeapp.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "second_name") val secondName: String,
    @ColumnInfo(name = "password") val password: String,

    ) : ModelEntity

class UserEntityMapper : EntityMapper<UserEntity, User> {
    override fun mapToDomain(entity: UserEntity): User = User(
        email = entity.email,
        firstName = entity.firstName,
        secondName = entity.secondName,
        password = entity.password
    )

    override fun mapToEntity(domain: User): UserEntity = UserEntity(
        email = domain.email,
        firstName = domain.firstName,
        secondName = domain.secondName,
        password = domain.password
    )
}
package com.example.coroutinescomposeapp.domain.model

import com.example.coroutinescomposeapp.domain.base.DomainMapper
import com.example.coroutinescomposeapp.domain.base.Model
import com.example.coroutinescomposeapp.ui.model.UserUI

data class User(
    val email: String,
    val firstName: String,
    val secondName: String,
    val password: String
) : Model

class UserDomainMapper : DomainMapper<User, UserUI> {
    override fun mapToUI(domain: User): UserUI =
        UserUI(
            email = domain.email,
            firstName = domain.firstName,
            secondName = domain.secondName,
            password = domain.password
        )

    override fun mapToDomain(item: UserUI): User =
        User(
            email = item.email,
            firstName = item.firstName,
            secondName = item.secondName,
            password = item.password
        )
}
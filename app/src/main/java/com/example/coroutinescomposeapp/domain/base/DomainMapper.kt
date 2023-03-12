package com.example.coroutinescomposeapp.domain.base

import com.example.coroutinescomposeapp.ui.base.Item

interface DomainMapper<M : Model, I : Item> {

    fun mapToUI(domain: M): I

    fun mapToDomain(item: I): M
}
package com.example.coroutinescomposeapp.data.base

import com.example.coroutinescomposeapp.domain.base.Model

interface EntityMapper<ME : ModelEntity, M : Model> {

    fun mapToDomain(entity: ME): M

    fun mapToEntity(domain: M): ME
}
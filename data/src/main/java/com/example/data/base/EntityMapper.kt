package com.example.data.base

import com.example.domain.base.Model

interface EntityMapper<ME : ModelEntity, M : Model> {

    fun mapToDomain(entity: ME): M

    fun mapToEntity(domain: M): ME
}
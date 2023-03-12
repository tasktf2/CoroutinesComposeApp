package com.example.coroutinescomposeapp.data.base

import com.example.coroutinescomposeapp.domain.base.Model

interface RemoteMapper<MR : ModelRemote, M : Model> {

    fun mapToDomain(remote: MR): M
}
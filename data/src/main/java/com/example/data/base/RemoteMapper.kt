package com.example.data.base

import com.example.domain.base.Model

interface RemoteMapper<MR : ModelRemote, M : Model> {

    fun mapToDomain(remote: MR): M
}
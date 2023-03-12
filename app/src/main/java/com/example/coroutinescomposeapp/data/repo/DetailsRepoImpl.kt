package com.example.coroutinescomposeapp.data.repo

import com.example.coroutinescomposeapp.data.base.RemoteMapper
import com.example.coroutinescomposeapp.data.remote.api.DetailsApi
import com.example.coroutinescomposeapp.data.remote.response.DetailsResponse
import com.example.coroutinescomposeapp.domain.model.Details
import com.example.coroutinescomposeapp.domain.repo.DetailsRepo

class DetailsRepoImpl(
    private val api: DetailsApi,
    private val detailsEntityMapper: RemoteMapper<DetailsResponse, Details>
) :
    DetailsRepo {
    override suspend fun getDetails(): Details = detailsEntityMapper.mapToDomain(api.getDetails())
}
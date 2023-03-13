package com.example.data.repo

import com.example.data.base.RemoteMapper
import com.example.data.remote.api.DetailsApi
import com.example.data.remote.response.DetailsResponse
import com.example.domain.model.Details
import com.example.domain.repo.DetailsRepo
import javax.inject.Inject

class DetailsRepoImpl @Inject constructor(
    private val api: DetailsApi,
    private val remoteMapper: RemoteMapper<DetailsResponse, Details>
) :
    DetailsRepo {
    override suspend fun getDetails(): Details = remoteMapper.mapToDomain(api.getDetails())
}
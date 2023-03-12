package com.example.coroutinescomposeapp.domain.repo

import com.example.coroutinescomposeapp.domain.model.Details

interface DetailsRepo {
   suspend fun getDetails(): Details
}
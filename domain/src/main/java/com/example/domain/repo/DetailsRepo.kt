package com.example.domain.repo

import com.example.domain.model.Details

interface DetailsRepo {
   suspend fun getDetails(): Details
}
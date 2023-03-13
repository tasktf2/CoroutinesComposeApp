package com.example.domain.base

interface UseCase<in Params, out T> {

    suspend fun execute(params: Params): T
}
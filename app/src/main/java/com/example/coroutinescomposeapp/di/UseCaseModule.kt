package com.example.coroutinescomposeapp.di

import com.example.domain.base.UseCase
import com.example.domain.model.Details
import com.example.domain.model.Product
import com.example.domain.model.User
import com.example.domain.repo.DetailsRepo
import com.example.domain.repo.ProductRepo
import com.example.domain.repo.UserRepo
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module(includes = [])
@InstallIn(SingletonComponent::class)
object ProvideUseCaseModule {
    @Provides
    @Named("flash")
    fun provideGetFlashSaleProductsUseCase(productRepo: ProductRepo): UseCase<Unit, List<Product>> =
        GetFlashSaleProductsUseCase(productRepo)

    @Provides
    @Named("latest")
    fun provideGetLatestProductsUseCase(productRepo: ProductRepo): UseCase<Unit, List<Product>> =
        GetLatestProductsUseCase(productRepo)

    @Provides
    fun bindCheckIfUserExistsByEmailUseCase(userRepo: UserRepo): UseCase<CheckIfUserExistsByEmailUseCase.Params, Boolean> =
        CheckIfUserExistsByEmailUseCase(userRepo)

    @Provides
    fun bindDeleteUserByEmailUseCase(userRepo: UserRepo): UseCase<DeleteUserByEmailUseCase.Params, Unit> =
        DeleteUserByEmailUseCase(userRepo)

    @Provides
    fun bindGetDetailsUseCase(detailsRepo: DetailsRepo): UseCase<Unit, Details> =
        GetDetailsUseCase(detailsRepo)


    @Provides
    fun bindGetOwnUserEmailUseCase(userRepo: UserRepo): UseCase<Unit, String> =
        GetOwnUserEmailUseCase(userRepo)

    @Provides
    fun bindGetUserByEmailUseCase(userRepo: UserRepo): UseCase<GetUserByEmailUseCase.Params, User> =
        GetUserByEmailUseCase(userRepo)

    @Provides
    fun bindInsertOwnUserEmailUseCase(userRepo: UserRepo): UseCase<InsertOwnUserEmailUseCase.Params, Unit> =
        InsertOwnUserEmailUseCase(userRepo)

    @Provides
    fun bindInsertUserUseCase(userRepo: UserRepo): UseCase<InsertUserUseCase.Params, Unit> =
        InsertUserUseCase(userRepo)

}
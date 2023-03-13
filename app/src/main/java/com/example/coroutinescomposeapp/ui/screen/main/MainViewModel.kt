package com.example.coroutinescomposeapp.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinescomposeapp.ui.model.CardUI
import com.example.coroutinescomposeapp.ui.model.toUi
import com.example.domain.base.UseCase
import com.example.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

sealed class MainState {

    data class Success(
        val latestProducts: List<CardUI>,
        val flashSaleProducts: List<CardUI>
    ) : MainState()

    object Loading : MainState()
    object Error : MainState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named("latest") private val getLatestProductsUseCase: @JvmSuppressWildcards UseCase<Unit, List<Product>>,
    @Named("flash") private val getFlashSaleProductsUseCase: @JvmSuppressWildcards UseCase<Unit, List<Product>>
) : ViewModel() {

    var uiState: MainState by mutableStateOf(MainState.Loading)
        private set

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            uiState = try {
                val latestProducts = getLatestProductsUseCase.execute(Unit).map(Product::toUi)
                val flashSaleProducts = getFlashSaleProductsUseCase.execute(Unit).map(Product::toUi)
                MainState.Success(latestProducts, flashSaleProducts)
            } catch (e: IOException) {
                MainState.Error
            } catch (e: HttpException) {
                MainState.Error
            }
        }
    }
}

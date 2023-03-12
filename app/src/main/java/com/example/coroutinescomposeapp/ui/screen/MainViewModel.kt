package com.example.coroutinescomposeapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coroutinescomposeapp.di.TempData
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.ui.model.CardUI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class MainState {

    data class Success(
        val latestProducts: List<CardUI>,
        val flashSaleProducts: List<CardUI>
    ) : MainState()

    object Loading : MainState()
    object Error : MainState()
}

class MainViewModel(
    private val getLatestProductsUseCase: UseCase<Unit, List<CardUI>>,
    private val getFlashSaleProductsUseCase: UseCase<Unit, List<CardUI>>
) : ViewModel() {

    var uiState: MainState by mutableStateOf(MainState.Loading)
        private set

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            uiState = try {
                val latestProducts = getLatestProductsUseCase.execute(Unit)
                val flashSaleProducts = getFlashSaleProductsUseCase.execute(Unit)
                MainState.Success(latestProducts, flashSaleProducts)
            } catch (e: IOException) {
                MainState.Error
            } catch (e: HttpException) {
                MainState.Error
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    TempData.getLatestProductsUseCase,
                    TempData.getFlashSaleProductsUseCase
                )
            }

        }
    }
}

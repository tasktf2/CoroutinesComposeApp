package com.example.coroutinescomposeapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinescomposeapp.data.remote.response.ProductRemote
import com.example.coroutinescomposeapp.di.NetworkService
import com.example.coroutinescomposeapp.ui.model.CardItemUI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class MainState {

    data class Success(
        val latestProducts: List<CardItemUI>,
        val flashSaleProducts: List<CardItemUI>
    ) : MainState()

    object Loading : MainState()
    object Error : MainState()
}

class MainViewModel : ViewModel() {

    var uiState: MainState by mutableStateOf(MainState.Loading)
        private set

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            uiState = try {
                val latestProducts =
                    NetworkService.productApi.getLatestItems().products.map(::mapToUI)
                val flashSaleProducts =
                    NetworkService.productApi.getFlashSaleItems().products.map(::mapToUI)
                MainState.Success(latestProducts, flashSaleProducts)
            } catch (e: IOException) {
                MainState.Error
            } catch (e: HttpException) {
                MainState.Error
            }
        }
    }

    private fun mapToUI(remote: ProductRemote): CardItemUI =
        CardItemUI(
            itemImageUrl = remote.imageUrl,
            itemName = remote.name,
            itemPrice = remote.price,
            itemCategory = remote.category,
            itemDiscount = remote.discount
        )
}

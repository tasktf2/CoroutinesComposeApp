package com.example.coroutinescomposeapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinescomposeapp.di.NetworkService
import com.example.coroutinescomposeapp.ui.model.DetailsItemUI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailsState {
    data class Success(val details: DetailsItemUI) : DetailsState()
    object Loading : DetailsState()
    object Error : DetailsState()
}

class DetailsViewModel : ViewModel() {

    var uiState: DetailsState by mutableStateOf(DetailsState.Loading)
        private set

    init {
        getDetails()
    }

    private fun getDetails() {

        viewModelScope.launch {
            uiState = try {

                val response = NetworkService.detailsApi.getDetails()
                val detailsUI = DetailsItemUI(
                    itemName = response.name,
                    itemDescription = response.description,
                    itemRating = response.rating,
                    itemCountOfReviews = response.numberOfReviews,
                    itemPrice = response.price,
                    colors = response.colors,
                    itemImages = response.listOfImageUrls
                )
                DetailsState.Success(detailsUI)
            } catch (e: IOException) {
                DetailsState.Error
            } catch (e: HttpException) {
                DetailsState.Error
            }
        }
    }
}

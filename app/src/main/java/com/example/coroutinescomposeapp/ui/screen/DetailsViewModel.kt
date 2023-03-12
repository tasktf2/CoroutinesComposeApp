package com.example.coroutinescomposeapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coroutinescomposeapp.di.TempData
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.ui.model.DetailsUI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailsState {
    data class Success(val details: DetailsUI) : DetailsState()
    object Loading : DetailsState()
    object Error : DetailsState()
}

class DetailsViewModel(private val getDetailsUseCase: UseCase<Unit, DetailsUI>) : ViewModel() {

    var uiState: DetailsState by mutableStateOf(DetailsState.Loading)
        private set

    init {
        getDetails()
    }

    private fun getDetails() {

        viewModelScope.launch {
            uiState = try {
                DetailsState.Success(getDetailsUseCase.execute(Unit))
            } catch (e: IOException) {
                DetailsState.Error
            } catch (e: HttpException) {
                DetailsState.Error
            }
        }
    }

    companion object {
        val factory = viewModelFactory {
            initializer {
                DetailsViewModel(TempData.GetDetailsUseCase)
            }
        }
    }
}

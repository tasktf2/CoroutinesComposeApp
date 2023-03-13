package com.example.coroutinescomposeapp.ui.screen.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinescomposeapp.ui.model.DetailsUI
import com.example.coroutinescomposeapp.ui.model.toUi
import com.example.domain.base.UseCase
import com.example.domain.model.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed class DetailsState {
    data class Success(val details: DetailsUI) : DetailsState()
    object Loading : DetailsState()
    object Error : DetailsState()
}

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getDetailsUseCase: @JvmSuppressWildcards UseCase<Unit, Details>) :
    ViewModel() {

    var uiState: DetailsState by mutableStateOf(DetailsState.Loading)
        private set

    init {
        getDetails()
    }

    private fun getDetails() {

        viewModelScope.launch {
            uiState = try {
                DetailsState.Success(getDetailsUseCase.execute(Unit).toUi())
            } catch (e: IOException) {
                DetailsState.Error
            } catch (e: HttpException) {
                DetailsState.Error
            }
        }
    }
}

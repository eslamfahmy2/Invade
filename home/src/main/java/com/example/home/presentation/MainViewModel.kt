package com.example.home.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.R
import com.example.home.data.dto.UniversityDto
import com.example.home.data.utils.ApplicationContextSingleton
import com.example.home.domain.models.AppOperationState
import com.example.home.domain.models.University
import com.example.home.domain.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject


const val COUNTRY_KEY = "COUNTRY_KEY"

data class ListState(
    val country: String = "",
    val isLoading: Boolean = false,
    val msg: String? = null,
    val carList: List<UniversityDto> = emptyList()
)

sealed class ListFragmentState {
    object Loading : ListFragmentState()
    data class Error(val error: String?) : ListFragmentState()
    data class Success(val data: List<University>) : ListFragmentState()
}

@HiltViewModel
class MainViewModel2 @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val universityRepository: UniversityRepository,
) : ViewModel() {

    //Mutable data
    private val _country = SavableMutableSaveStateFlow(savedStateHandle, COUNTRY_KEY, "Egypt")

    private var _universitiesList = universityRepository
        .getUniversitiesByCountry(country = _country.value)


    private val safeLaunchFlow = MutableStateFlow(AppOperationState())

    //Immutable data
    // The UI collects from this StateFlow to get its state updates
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ListFragmentState> =
        safeLaunchFlow.flatMapLatest { operationState: AppOperationState ->
            //Handle Loading, Error and other screen states before accessing the combined screen data
            combineScreenData(operationState)
        }//.flowOn(Dispatchers.Main)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = ListFragmentState.Loading
            )

    //combine data flows to construct screen state
    private fun combineScreenData(operationState: AppOperationState) = combine(
        _country.asStateFlow(), _universitiesList
    ) { _, universitiesList ->
        when (operationState.status) {
            AppOperationState.DataStatus.LOADING -> ListFragmentState.Loading
            AppOperationState.DataStatus.ERROR -> {
                ListFragmentState.Error(operationState.error?.message)
            }
            else -> {
                if (universitiesList.isEmpty()){
                    ListFragmentState.Error(ApplicationContextSingleton.getString(R.string.no_matching_search))
                }else
                    ListFragmentState.Success(universitiesList)
            }
        }
    }

    //Input Events- Actions
    fun onUpdateCountry(newValue: String) {
        _country.value = newValue
    }

    fun onSearch() {
        viewModelScope.safeLaunchWithFlow(safeLaunchFlow) {
            universityRepository.loadUniversitiesByCountry(_country.value)
            _universitiesList =
                universityRepository.getUniversitiesByCountry(country = _country.value)

        }
    }
}



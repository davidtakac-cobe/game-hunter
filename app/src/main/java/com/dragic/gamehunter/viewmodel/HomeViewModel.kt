package com.dragic.gamehunter.viewmodel

import androidx.lifecycle.ViewModel
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.utils.toDealViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DealRepository,
) : ViewModel() {
    val dealData = repository.dealData().sortedByDescending { it.dealRating }.map { it.toDealViewState() }
}

package com.example.jakalacodechallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakalacodechallenge.data.repository.SwapiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonViewModel(
    private val repository: SwapiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PersonViewState())
    val state: StateFlow<PersonViewState> = _state

    fun handleIntent(intent: PersonIntent) {
        viewModelScope.launch {
            when (intent) {
                is PersonIntent.LoadPerson -> fetchPerson()
            }
        }
    }

    private suspend fun fetchPerson() {
        _state.value = _state.value.copy(
            loading = true
        )

        try {
            val data = repository.getPeople()
            _state.value = PersonViewState(
                loading = false,
                count = data.count,
                nextUrl = data.next,
                previousUrl = data.previous,
                people = data.results
            )
        } catch (e: Exception) {
            _state.value = PersonViewState(
                loading = false,
                error = e.message
            )
        }
    }
}
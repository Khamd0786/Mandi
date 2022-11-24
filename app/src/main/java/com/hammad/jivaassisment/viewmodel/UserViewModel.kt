package com.hammad.jivaassisment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hammad.jivaassisment.model.User
import com.hammad.jivaassisment.repository.CoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val KEY_MULTIPLIER = 1.2

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: CoreRepository) : ViewModel() {

    private val _userShareFlow = MutableStateFlow<User?>(null)
    val userShareFlow = _userShareFlow.asStateFlow()

    fun register(userName: String) {
        val id = UUID.randomUUID().toString().take(4)
        repository.registerUser(userName, id, KEY_MULTIPLIER)
    }

    fun getUserByUsername(userName: String) {
        viewModelScope.launch {
            repository.getUserByName(userName).collectLatest {
                _userShareFlow.emit(it)
            }
        }
    }

    fun getUserByLoyaltyId(id: String) {
        viewModelScope.launch {
            repository.getUserById(id).collectLatest {
                _userShareFlow.emit(it)
            }
        }
    }



}
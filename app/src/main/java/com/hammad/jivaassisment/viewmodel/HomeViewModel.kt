package com.hammad.jivaassisment.viewmodel

import androidx.lifecycle.ViewModel
import com.hammad.jivaassisment.repository.CoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: CoreRepository): ViewModel() {

    var mSelectedVillage = getVillages()[0]

    fun getVillages() = repository.getVillages()

    fun getPrice() = repository.getPrice(mSelectedVillage)

}
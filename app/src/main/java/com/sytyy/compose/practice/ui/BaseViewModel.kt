package com.sytyy.compose.practice.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sytyy.compose.practice.domain.InterestingPhotoModel
import com.sytyy.compose.practice.domain.InterestingPhotoRepository
import com.sytyy.compose.practice.util.ListElement
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val interestingPhotoRepository: InterestingPhotoRepository) :
    ViewModel() {

    private val _interestingPhotos = mutableStateListOf<InterestingPhotoModel>()
    val interestingPhotos: List<InterestingPhotoModel> = _interestingPhotos

    var interestingPhotoDetail by mutableStateOf<InterestingPhotoModel?>(null)
        private set

    var listElement by mutableStateOf<ListElement?>(null)
        private set

    fun getInterestingPhotos() {
        viewModelScope.launch {
            interestingPhotoRepository.getInterestingPhotos().collectLatest { photos ->
                _interestingPhotos.clear()
                _interestingPhotos.addAll(photos)
            }
        }
    }

    fun setCurrentPhotoId(photoID: Long) {
        viewModelScope.launch {
            interestingPhotoDetail = interestingPhotoRepository.getInterestingPhotoDetail(photoID)
            checkItemIndex()
        }
    }

    private fun checkItemIndex(): Int {
        val currentIndex: Int = _interestingPhotos.indexOf(interestingPhotoDetail)
        viewModelScope.launch {
            listElement = when (currentIndex) {
                0 -> {
                    ListElement.FIRST_ELEMENT
                }

                _interestingPhotos.lastIndex -> {
                    ListElement.LAST_ELEMENT
                }

                else -> ListElement.ANOTHER_ELEMENT
            }
        }
        return currentIndex
    }

    fun setNextItem() {
        viewModelScope.launch {
            val currentIndex = checkItemIndex()
            interestingPhotoDetail =
                interestingPhotoRepository.getInterestingPhotoDetail(_interestingPhotos[currentIndex + 1].photoId)
        }
    }

    fun setPreviousItem() {
        val currentIndex = checkItemIndex()
        viewModelScope.launch {
            interestingPhotoDetail =
                interestingPhotoRepository.getInterestingPhotoDetail(_interestingPhotos[currentIndex - 1].photoId)
        }
    }

}
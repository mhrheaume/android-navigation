package com.example.android.codelabs.navigation.deeplink

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeepLinkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val argument = savedStateHandle.getStateFlow("myarg", "")
}
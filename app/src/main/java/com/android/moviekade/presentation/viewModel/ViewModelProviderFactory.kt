package com.android.moviekade.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelProviderFactory: ViewModelProvider.Factory {

    private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>? = null

    @Inject
    fun ViewModelProviderFactory(creators: Map<Class<out ViewModel>, Provider<ViewModel>>?) {
        this.creators = creators
    }
}
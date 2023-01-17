package com.example.android.codelabs.navigation.di

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DeepLinkFragmentModule {
    @Provides
    fun provideNavController(fragment: Fragment): NavController =
        fragment.findNavController()
}
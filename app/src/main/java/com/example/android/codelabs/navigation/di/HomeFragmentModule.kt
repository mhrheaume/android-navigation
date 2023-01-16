package com.example.android.codelabs.navigation.di

import android.app.Activity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import com.example.android.codelabs.navigation.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object HomeFragmentModule {
    @Provides
    fun provideMenuProvider(): MenuProvider =
        object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }

    @Provides
    fun provideMenuHost(activity: Activity): MenuHost = (activity as AppCompatActivity)
}
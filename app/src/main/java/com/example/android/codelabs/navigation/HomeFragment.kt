/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment used to show how to navigate to another destination
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject lateinit var menuHost: MenuHost
    @Inject lateinit var menuProvider: MenuProvider

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        val destinationButton = view.findViewById<Button>(R.id.navigate_destination_button)
        destinationButton?.setOnClickListener {
            findNavController().navigate(R.id.flow_step_one_dest, null, options)
        }

        val actionButton = view.findViewById<Button>(R.id.navigate_action_button)
        actionButton?.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.nextAction(), options)
        }
    }
}

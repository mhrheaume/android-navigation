package com.example.android.codelabs.navigation.deeplink

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import com.example.android.codelabs.navigation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class DeepLinkNotifier @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val notificationManager: NotificationManager,
    private val navController: NavController,
) {
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            )
        }
    }

    fun notify(arg: String) {
        val bundle = Bundle()
        bundle.putString("myarg", arg)

        val intent = navController.createDeepLink()
            .setDestination(R.id.deeplink_dest)
            .setArguments(bundle)
            .createPendingIntent()

        val notificationBuilder = NotificationCompat.Builder(appContext, CHANNEL_ID)
            .setContentTitle("Navigation")
            .setContentText("Deep link to Android")
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(intent)
            .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {
        private const val CHANNEL_ID = "deeplink"
        private const val CHANNEL_NAME = "Deep Links"
    }
}
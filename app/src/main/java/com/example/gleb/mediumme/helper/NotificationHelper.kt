package com.example.gleb.mediumme.helper

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationHelper {
    val LOG_TAG: String = this.javaClass.canonicalName
    val PENDING_INTENT_ID = 0
    var stackBuilder: TaskStackBuilder? = null
    var notification: Notification? = null

    init {
        Log.d(LOG_TAG, "Init string helper")
    }

    private object HOLDER {
        val INSTANCE = NotificationHelper()
    }

    companion object {
        val instance: NotificationHelper by lazy { HOLDER.INSTANCE }
    }

    fun buildNotification(nm: NotificationManager, context: Context, title: String, text: String,
                          initIntent: () -> Intent){
        var notificationBuilder = Notification.Builder(context)
        notificationBuilder.setSmallIcon(android.R.drawable.sym_def_app_icon)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setContentText(text)

        val intent = initIntent()
        var pendingIntent = PendingIntent.getActivity(context, PENDING_INTENT_ID, intent, 0)
        notificationBuilder.setContentIntent(pendingIntent)

        var versionApi = VersionHelper.checkVersion()
        if (versionApi) {
            notification = notificationBuilder.build()
        }
        else{
            notification = notificationBuilder.notification
        }

        nm.notify(0, notification)
    }
}
package com.example.toaster

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val channelId = "notification_channel"
const val channelName = "com.example.maverick"
class PushNotificationSDK(private val context: Context)  {


    companion object {
        @Volatile
        private var instance: PushNotificationSDK? = null

        fun getInstance(context: Context): PushNotificationSDK {
            return instance ?: synchronized(this) {
                instance ?: PushNotificationSDK(context.applicationContext).also { instance = it }
            }
        }
    }



    fun configure(context: Context) {
        // Initialize Firebase
        // You should initialize Firebase in the consumer app
    }

    fun <T> handleRemoteMessage(notification: Notification, data: Map<String, String>, classValue: AppCompatActivity) {
        // Check if message contains a notification payload.

        if (notification != null) {
            var dataMap: Map<String, String> = HashMap()
            var noteType: String? = ""
            dataMap["asdad:dsadasd"]
            if (data.isNotEmpty()) {
                noteType = data["type"]
                dataMap = data
                println(dataMap.map { "${it.key}: ${it.value}" }.joinToString(", "))
            }
            when (noteType) {
                "Text" -> generateNotificationText(dataMap,classValue)
//                "Image" -> generateNotificationWithImage(dataMap)
//                "Expandable" -> generateNotificationExpandable(dataMap)
//                "HeadsUp" -> generateNotificationHeadsUp(dataMap)
//                "Sticky" -> generateNotificationSticky(dataMap)
//                "Action" -> generateNotificationActionButton(dataMap)
            }        }

        // TODO: Handle other types of payloads if needed.
    }

    @SuppressLint("MissingPermission")
    fun generateNotificationText(dataMap: Map<String, String>, classValue: AppCompatActivity) {

        println("Text Notifcation")

        val title = dataMap["title"]
        val message = dataMap["message"]

        val mainIntent = Intent(context, classValue::class.java)
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val date = Date()
        val notificationId = SimpleDateFormat("ddHHmmss", Locale.US).format(date).toInt()

        val homeIntent = PendingIntent.getActivity(
            context, 1, mainIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(androidx.core.R.drawable.notification_bg)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
                .setOnlyAlertOnce(true)
                .setContentIntent(homeIntent)


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            println("In If")
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.description = message
            notificationManager!!.createNotificationChannel(notificationChannel)
        } else {
            println("In Else")
        }

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(notificationId, builder.build())
    }




}
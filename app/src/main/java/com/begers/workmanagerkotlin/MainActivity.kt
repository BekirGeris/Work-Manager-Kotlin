package com.begers.workmanagerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = Data.Builder().putInt("intKey", 1).build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            //.setRequiredNetworkType(NetworkType.METERED)
            .setRequiresCharging(false)
            .build()

        /*bir kez yapılan işlemler
        val workRequest : WorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            //.setInitialDelay(5, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
        */

        //periyodik olraka yapılan işlemler
        val workRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<RefreshDatabase>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.id).observe(this, Observer {
            if (it.state == WorkInfo.State.RUNNING){
                println("RUNNING")
            }else if (it.state == WorkInfo.State.FAILED){
                println("FAILED")
            }else if (it.state == WorkInfo.State.SUCCEEDED){
                println("SUCCEEDED")
            }
        })

        //şin iptal edilmesi
        //WorkManager.getInstance(this).cancelAllWork()


    }
}
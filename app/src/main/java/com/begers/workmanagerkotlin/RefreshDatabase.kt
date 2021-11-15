package com.begers.workmanagerkotlin

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RefreshDatabase(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        refreshDatabase()
        return Result.success()
    }

    private fun refreshDatabase(){
        val sharedPreferences = context.getSharedPreferences("com.begers.workmanagerkotlin", Context.MODE_PRIVATE)
        var mySvaedNumber = sharedPreferences.getInt("öyNumber", 0)
        mySvaedNumber += mySvaedNumber
        sharedPreferences.edit().putInt("myNumber", mySvaedNumber)
    }
}
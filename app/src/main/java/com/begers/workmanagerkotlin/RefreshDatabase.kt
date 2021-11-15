package com.begers.workmanagerkotlin

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RefreshDatabase(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val data = inputData
        val myNumber = data.getInt("intKey", 0)
        refreshDatabase(myNumber)
        return Result.success()
    }

    private fun refreshDatabase(myNumber: Int){
        val sharedPreferences = context.getSharedPreferences("com.begers.workmanagerkotlin", Context.MODE_PRIVATE)
        var mySvaedNumber = sharedPreferences.getInt("myNumber", 0)
        mySvaedNumber += myNumber
        println(mySvaedNumber)
        sharedPreferences.edit().putInt("myNumber", mySvaedNumber).apply()
    }
}
package com.example.kouichihonda.realmverification

import android.app.IntentService
import android.content.Intent
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

class WorkerIntentService : IntentService(WorkerIntentService::class.java.simpleName) {
    override fun onHandleIntent(intent: Intent?) {
        Realm.getDefaultInstance()?.let {
            Timber.tag(this::class.java.simpleName).d("WorkerThread")
            SampleRealmInDiskLocalDataSource(realmInDisk = it).save(sampleString = "WorkerThread")
            it.close()
        }
        Realm.getInstance(
            RealmConfiguration.Builder()
                .name("realmVerificationRealmInMemory.realm")
                .inMemory()
                .build()
        )?.let {
            SampleRealmInMemoryLocalDataSource(realmInMemory = it)
                .save(sampleString = "WorkerThreadInMemory")
            it.close()
        }
    }
}
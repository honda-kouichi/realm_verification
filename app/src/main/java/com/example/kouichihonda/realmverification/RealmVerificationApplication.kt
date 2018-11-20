package com.example.kouichihonda.realmverification

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.annotations.RealmModule
import timber.log.Timber

/**
 * Created by kouichihonda on 2018/08/16.
 *
 */
class RealmVerificationApplication : Application() {

    lateinit var realmInDisk: Realm
    lateinit var realmInMemory: Realm

    companion object {
        lateinit var instance: RealmVerificationApplication
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        Timber.tag(this::class.java.simpleName).d("onCreate")

        Realm.init(this.applicationContext)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
//                .schemaVersion(1)
                .schemaVersion(2)
                .modules(RealmInDiskModule())
                .migration(RealmVerificationRealmMigration())
                .build()
        )
        realmInDisk = Realm.getDefaultInstance()
        realmInMemory = Realm.getInstance(
            RealmConfiguration.Builder()
                .name("realmVerificationRealmInMemory.realm")
                .inMemory()
                .build()
        )
    }
}
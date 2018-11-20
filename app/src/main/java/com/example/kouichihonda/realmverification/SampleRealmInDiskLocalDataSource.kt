package com.example.kouichihonda.realmverification

import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by kouichihonda on 2018/08/17.
 *
 */
class SampleRealmInDiskLocalDataSource(
    val realmInDisk: Realm = RealmVerificationApplication.instance.realmInDisk
) {

    fun save(sampleString: String) {
        this.find().let { sampleRealmInDiskEntity ->
            this.realmInDisk.executeTransaction { realm ->
                sampleRealmInDiskEntity.firstOrNull()?.let {
                    it.sampleString = sampleString
                } ?: run {
                    realm.copyToRealmOrUpdate(SampleRealmInDiskEntity(sampleString = sampleString))
                }
            }
        }
    }

    fun find(): RealmResults<SampleRealmInDiskEntity?> {
        return this.realmInDisk.where(SampleRealmInDiskEntity::class.java).findAll()
    }
}
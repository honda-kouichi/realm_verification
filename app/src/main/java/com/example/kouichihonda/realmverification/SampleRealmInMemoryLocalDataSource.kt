package com.example.kouichihonda.realmverification

import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by kouichihonda on 2018/08/17.
 *
 */
class SampleRealmInMemoryLocalDataSource(
    val realmInMemory: Realm = RealmVerificationApplication.instance.realmInMemory
) {

    fun save(sampleString: String) {
        this.find().let { sampleRealmInMemoryEntity ->
            this.realmInMemory.executeTransaction { realm ->
                sampleRealmInMemoryEntity.firstOrNull()?.let {
                    it.sampleString = sampleString
                } ?: run {
                    realm.copyToRealmOrUpdate(
                        SampleRealmInMemoryEntity(sampleString = sampleString)
                    )
                }
            }
        }
    }

    fun find(): RealmResults<SampleRealmInMemoryEntity?> {
        return this.realmInMemory.where(SampleRealmInMemoryEntity::class.java).findAll()
    }
}
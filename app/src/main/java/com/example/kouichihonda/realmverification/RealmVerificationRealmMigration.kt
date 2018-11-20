package com.example.kouichihonda.realmverification

import io.realm.DynamicRealm
import io.realm.RealmMigration

/**
 * Created by kouichihonda on 2018/08/16.
 *
 */
class RealmVerificationRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        realm?.let {
            if ((oldVersion == 1L).and(newVersion == 2L)) {
                it.schema
                    .get(SampleRealmInDiskEntity::class.java.simpleName)
                    ?.addField(SampleRealmInDiskEntity::sampleString2.name, String::class.java)
                    ?.setRequired(SampleRealmInDiskEntity::sampleString2.name, true)
            }
        }
    }
}
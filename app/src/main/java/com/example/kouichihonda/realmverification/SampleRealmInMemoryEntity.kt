package com.example.kouichihonda.realmverification

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by kouichihonda on 2018/08/17.
 *
 */
@RealmClass
class SampleRealmInMemoryEntity(
    @PrimaryKey var id: Int = 0,
    var sampleString: String = ""
) : RealmModel
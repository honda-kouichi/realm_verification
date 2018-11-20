package com.example.kouichihonda.realmverification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class SubActivity : AppCompatActivity() {

    private var sampleRealmInDiskLocalDataSource: SampleRealmInDiskLocalDataSource? = null
    private var sampleRealmInMemoryLocalDataSource: SampleRealmInMemoryLocalDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        this.sampleRealmInDiskLocalDataSource = SampleRealmInDiskLocalDataSource()
        this.sampleRealmInMemoryLocalDataSource = SampleRealmInMemoryLocalDataSource()

        Timber.tag(this::class.java.simpleName).d("SubActivity")
        this.sampleRealmInDiskLocalDataSource?.save(sampleString = "SubActivity")
        this.sampleRealmInMemoryLocalDataSource?.save(sampleString = "SubActivityInMemory")
    }
}

package com.example.kouichihonda.realmverification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var sampleRealmInDiskLocalDataSource: SampleRealmInDiskLocalDataSource? = null
    private var sampleRealmInMemoryLocalDataSource: SampleRealmInMemoryLocalDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.sampleRealmInDiskLocalDataSource = SampleRealmInDiskLocalDataSource()
        this.sampleRealmInMemoryLocalDataSource = SampleRealmInMemoryLocalDataSource()

        this.sampleRealmInDiskLocalDataSource?.find()?.addChangeListener { t, _ ->
            t.firstOrNull()?.let {
                Timber.tag(this::class.java.simpleName).d(it.sampleString)
                this.activityMainTextView.text = it.sampleString
            }
        }
        this.sampleRealmInMemoryLocalDataSource?.find()?.addChangeListener { t, _ ->
            t.firstOrNull()?.let {
                this.activityMain2TextView.text = it.sampleString
            }
        }

        Timber.tag(this::class.java.simpleName).d("MainActivity")
        this.sampleRealmInDiskLocalDataSource?.save(sampleString = "MainActivity")
        this.sampleRealmInMemoryLocalDataSource?.save(sampleString = "MainActivityInMemory")

        this.activityMainSubButton.setOnClickListener {
            this.startActivity(Intent(this, SubActivity::class.java))
        }

        this.activityMainWorkerThreadButton.setOnClickListener {
            this.startService(Intent(this, WorkerIntentService::class.java))
        }
    }


}

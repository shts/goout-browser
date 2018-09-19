package jp.shts.android.gooutbrowser

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class GoOutBrowserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())
    }
}
package com.mrigankpawagi.rush.app

import android.app.Application
import com.mrigankpawagi.rush.billing.BillingInitializerImpl
import com.mrigankpawagi.rush.core.data.listener.MediaListenerImpl
import com.mrigankpawagi.rush.di.rushModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RushApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RushApplication)
            modules(rushModules)
        }

        BillingInitializerImpl().initialize(this)
        MediaListenerImpl.startListening(this)
    }

}
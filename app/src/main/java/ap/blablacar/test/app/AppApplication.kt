package ap.blablacar.test.app

import android.app.Application
import ap.blablacar.test.app.di.appModule
import ap.blablacar.test.app.di.dataModule
import ap.blablacar.test.app.di.domainModule
import ap.blablacar.test.app.di.networkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppSchedulers.init(mainThread = AndroidSchedulers.mainThread())

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(listOf(networkModule, dataModule, domainModule, appModule))
        }
    }
}
package ap.blablacar.test.app.di

import ap.blablacar.test.data.AndroidSharedPreferencesStorage
import ap.blablacar.test.data.oauth.OAuthNetworkRepository
import ap.blablacar.test.data.trip.TripNetworkRepository
import ap.blablacar.test.domain.PreferencesStorage
import ap.blablacar.test.domain.oauth.OAuthRepository
import ap.blablacar.test.domain.trip.TripRepository
import ap.blablacar.test.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchViewModel(get()) }

    single<PreferencesStorage> { AndroidSharedPreferencesStorage(androidContext()) }
}

val dataModule = module {

    single<OAuthRepository> { OAuthNetworkRepository(get(), get()) }
    single<TripRepository> { TripNetworkRepository(get()) }
}


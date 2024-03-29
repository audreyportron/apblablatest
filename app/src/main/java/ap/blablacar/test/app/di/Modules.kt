package ap.blablacar.test.app.di

import ap.blablacar.test.data.AndroidSharedPreferencesStorage
import ap.blablacar.test.data.oauth.OAuthNetworkRepository
import ap.blablacar.test.data.trip.TripNetworkRepository
import ap.blablacar.test.domain.PreferencesStorage
import ap.blablacar.test.domain.oauth.OAuthRepository
import ap.blablacar.test.domain.trip.TripRepository
import ap.blablacar.test.domain.trip.TripService
import ap.blablacar.test.ui.search.SearchViewModel
import ap.blablacar.test.ui.trip.TripsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchViewModel(getProperty(SearchViewModel.CALLBACK_PROPERTY)) }
    viewModel {
        TripsViewModel(
            get(),
            getProperty(TripsViewModel.FROM_ID),
            getProperty(TripsViewModel.TO_ID)
        )
    }

    single<PreferencesStorage> { AndroidSharedPreferencesStorage(androidContext()) }
}

val dataModule = module {

    single<OAuthRepository> { OAuthNetworkRepository(get(), get()) }
    single<TripRepository> { TripNetworkRepository(get()) }
}

val domainModule = module {

    single { TripService(get()) }
}


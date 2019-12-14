package ap.blablacar.test.app.di

import ap.blablacar.test.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchViewModel() }
}
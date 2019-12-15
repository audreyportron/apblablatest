package ap.blablacar.test.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ap.blablacar.test.R
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun should_go_launch_search_when_from_and_to_are_filled() {
        //Given
        val searchViewModel = SearchViewModel(mock())
        searchViewModel.from.value = "paris"
        searchViewModel.to.value = "angers"

        //When
        searchViewModel.find()

        //Then
        verify(searchViewModel.callBack)?.searchForNewTrip(any(), any())

    }

    @Test
    fun should_display_error_whent_from_is_not_filled() {
        //Given

        val searchViewModel = SearchViewModel(mock())


        //When
        searchViewModel.find()

        //Then
        assert(searchViewModel.error.get() == R.string.search_error_from)

    }

    @Test
    fun should_display_error_whent_to_is_not_filled() {
        //Given

        val searchViewModel = SearchViewModel(mock())
        searchViewModel.from.value = "paris"


        //When
        searchViewModel.find()

        //Then
        assert(searchViewModel.error.get() == R.string.search_error_to)

    }

}
package com.escmobile.hsm_hospitals

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.escmobile.hsm_hospitals.data.model.Hospital
import com.escmobile.hsm_hospitals.data.model.SubType
import com.escmobile.hsm_hospitals.managers.NavigationManager
import com.escmobile.hsm_hospitals.presentation.viewmodels.SearchViewModel
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchViewModelTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var searchViewModel: SearchViewModel

    @Mock
    lateinit var mockNavigationManager: NavigationManager

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        searchViewModel = SearchViewModel(mockNavigationManager)
    }

    @Test
    fun `search method returns hospital data when found`() = runBlocking {
        // Arrange
        val searchText = "Kingston"

        // Act
        searchViewModel.search(searchText, testData)

        // Assert
        searchViewModel.searchResults.observeForever {
            assertTrue(it.size == 1)
        }
    }

    @Test
    fun `search method does not return data no when found`() = runBlockingTest {
        // Arrange
        val searchText = "A hospital name that does not exits"

        // Act
        searchViewModel.search(searchText, testData)

        // Assert
        searchViewModel.searchResults.observeForever {
            assertTrue(it.isEmpty())
        }
    }

    @Test
    fun `on hospital click invokes the navigation manager`() = runBlockingTest {

        // Act
        searchViewModel.onHospitalClick(testData[0])

        // Assert
        Mockito.verify(mockNavigationManager, Mockito.times(1)).navigateTo(any())
    }

    private val testData = listOf<Hospital>(
        Hospital(
            "1",
            "ABC",
            "",
            SubType.MENTAL_HEALTH_HOSPITAL,
            "",
            "",
            "",
            "Kingston Hospital",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        ),
        Hospital(
            "2",
            "XYZ",
            "",
            SubType.UNKNOWN,
            "",
            "",
            "",
            "Surrey Hospital",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    )
}
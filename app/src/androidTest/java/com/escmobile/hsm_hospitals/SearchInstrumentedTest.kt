package com.escmobile.hsm_hospitals

import android.widget.EditText
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.escmobile.hsm_hospitals.presentation.views.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.escmobile.hsm_hospitals", appContext.packageName)
    }

    @Test
    fun searchNotFound() {

        Espresso.onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(
            ViewActions.typeText("xxx no such hospital xzx"),
            ViewActions.pressImeActionButton()
        )

        Espresso.onView(withId(R.id.no_results_found))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchResultFound() {

        Espresso.onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(
            ViewActions.typeText("NHS"),
            ViewActions.pressImeActionButton()
        )

        Espresso.onView(withId(R.id.recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchResultFoundAndNoResultViewIsNotDisplayed() {

        Espresso.onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(
            ViewActions.typeText("NHS"),
            ViewActions.pressImeActionButton()
        )

        Espresso.onView(withId(R.id.no_results_found))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

}
package com.example.krzysztof.alotto2;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Monika on 03/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class MyFirstTest {

    @Rule
    public ActivityTestRule<MainActivity> myFirstTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkLottoValue () throws Exception{
        onView(withId(R.id.mySpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Lotto"))).perform(click());
        onView(withId(R.id.numView))
                .check(matches(isDisplayed()));
        onView(withId(R.id.saveBtn))
                .perform(click());
        onView(allOf(withId(R.string.select), isDisplayed()));
        onView(withId(R.id.clearBtn))
                .perform(click());
        onView(withId(R.id.numView))
                .check(matches(withText(containsString(""))));
    }
/*
    @Test
    public void checkToastMsg () throws Exception{
        onView(withId(R.id.saveBtn))
                .perform(click());
        onView(allOf(withId(R.string.select), isDisplayed()));


    }
   @Test
    public void checkClearButton () throws Exception{
        onView(withId(R.id.clearBtn))
                .perform(click());
        onView(withId(R.id.numView))
                .check(matches(withText(containsString(""))));


    }*/
}

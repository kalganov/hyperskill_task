package com.example.myapplication;

import android.graphics.Color;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.Matchers.ColorTimerMatcher;
import com.example.myapplication.Matchers.TextTimerMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SettingButtonInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void settingButtonExist() {
        onView(withText("Setting")).perform(ViewActions.click());
    }

    @Test
    public void openDialog() {
        onView(withText("Settings")).perform(ViewActions.click());
        onView(withText("OK")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
    }

    @Test
    public void setWorkTime() throws InterruptedException {
        onView(withText("Settings")).perform(ViewActions.click());
        onView(withId(R.id.workTime)).perform(typeText("6"));
        onView(withText("OK")).perform(ViewActions.click());
        onView(withId(R.id.timerView)).check(matches(TextTimerMatcher.withText("00:06")));
        onView(withText("Start")).perform(ViewActions.click());
        Thread.sleep(6000);
        onView(withId(R.id.timerView)).check(matches(ColorTimerMatcher.withColor(Color.GREEN)));
    }

    @Test
    public void setRestTime() throws InterruptedException {
        onView(withText("Settings")).perform(ViewActions.click());
        onView(withId(R.id.restTime)).perform(typeText("5"));
        onView(withText("OK")).perform(ViewActions.click());
        onView(withText("Start")).perform(ViewActions.click());
        Thread.sleep(5000);
        onView(withId(R.id.timerView)).check(matches(TextTimerMatcher.withText("00:05")));
        onView(withId(R.id.timerView)).check(matches(ColorTimerMatcher.withColor(Color.GREEN)));
    }
}

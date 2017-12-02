package com.alexeyosadchy.android.converter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.alexeyosadchy.android.converter.View.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.alexeyosadchy.android.converter", appContext.getPackageName());
    }

    @Test
    public void onChangeEditTextTest() throws Exception {
        MainActivity mainActivity = new MainActivity();

        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.alexeyosadchy.android.converter", appContext.getPackageName());
    }
}

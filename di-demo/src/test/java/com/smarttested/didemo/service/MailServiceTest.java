package com.smarttested.didemo.service;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MailServiceTest {

    @Test
    public void testMailService() {
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();

        // 1
        map1.equals(map2);

        // 2
        MapDifference<String, String> diff = Maps.difference(map1, map2);

        Matcher<String> myMatcher = new Matcher<String>() {

            public void describeTo(Description description) {

            }

            public boolean matches(Object item) {
                return "Andrei Varabyeu".equals(item);
            }

            public void describeMismatch(Object item, Description mismatchDescription) {
                mismatchDescription.appendText("������!");
            }

            @Deprecated
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
                // TODO Auto-generated method stub

            }
        };

        Assert.assertThat("Andrei Varabyeu1", myMatcher);
        Assert.assertThat("Andrei Varabyeu1", myMatcher);
        Assert.assertThat("Andrei Varabyeu1", myMatcher);
        Assert.assertThat("Andrei Varabyeu1", myMatcher);
    }
}

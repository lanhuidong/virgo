package com.nexusy.virgo.web;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @author lan
 * @since 2013-11-28
 */
public class PathMatcherTest {

    @Test
    public void test() {
        PathMatcher matcher = new AntPathMatcher();
        Assert.assertTrue(matcher.match("/signup/**", "/signup"));

    }
}

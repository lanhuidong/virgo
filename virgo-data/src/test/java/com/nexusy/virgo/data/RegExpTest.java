package com.nexusy.virgo.data;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lan
 * @since 2014-01-13
 */
public class RegExpTest {

    @Test
    public void test() {
        Pattern pattern = Pattern.compile("^((\\s*)|((\\+86)|(86))?((15)|(13)|(18))\\d{9})$");
        Matcher matcher = pattern.matcher("+8614158889037");
        if (matcher.find()) {
            System.out.println(matcher.group()+"--");
        }
    }

}

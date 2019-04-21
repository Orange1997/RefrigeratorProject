package com.example.dc.refrigeratorproject.util;

import java.util.regex.Pattern;

/**
 * Created by DC on 2019/3/5.
 */

public class InputUtils {
    public static boolean isPhoneNumber(String input) {
        String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
        Pattern p = Pattern.compile (regex);
        return p.matches (regex, input);
    }

}

package com.vinschool.smarttime.ulti;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    public static String hashPass(String plantext) {
        return BCrypt.hashpw(plantext, BCrypt.gensalt(10));
    }

    public static boolean checkPass(String planText, String check) {
        return BCrypt.checkpw(planText, check);
    }
}

package com.vinschool.smarttime.ulti;

import org.mindrot.jbcrypt.BCrypt;

public  class HashPassword {
    public static String HashPass(String plantext){
        return BCrypt.hashpw(plantext, BCrypt.gensalt(12));
    }

    public static  boolean CheckPass(String planText,String check) {
        return BCrypt.checkpw(planText, check);
    }
}

package com.example.user_service.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {


    //  hash a password
    public static String hashpassword(String plainTestPassword)
    {return BCrypt.hashpw(plainTestPassword,BCrypt.gensalt());}


    //  hash a password
    public static boolean checkpw(String plainTestPassword,String hashedPassword)
    {return BCrypt.checkpw(plainTestPassword,hashedPassword);}
}

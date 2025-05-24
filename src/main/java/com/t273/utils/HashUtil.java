package com.t273.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class HashUtil {
    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword.toCharArray());
        return result.verified;
    }
}

package com.fiap.frameworks.clothes.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String generateHash(String s) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            byte[] digest = m.digest();
            String hexaDecimal = new BigInteger(1, digest).toString(16);
            return hexaDecimal;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static String formatCPF(String cpf) {
        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(cpf);
        if (matcher.matches())
            cpf = matcher.replaceAll("$1.$2.$3-$4");
        return cpf;
    }

    public static String formatHash(String h) {
        Pattern pattern = Pattern.compile("(.{5})(.{5})(.{5})(.{5})(.{5})(.{5})(.{2})");
        Matcher matcher = pattern.matcher(h);
        if (matcher.matches())
            h = matcher.replaceAll("$1		$2		$3		$4		$5		$6		$7");
        return h;
    }

}

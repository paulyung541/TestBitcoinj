package com.paul.bitcoinj.utils;

public class Log {
    public static boolean DEBUG = true;

    public static void p(String s) {
        if (DEBUG)
            System.out.println(s);
    }
}

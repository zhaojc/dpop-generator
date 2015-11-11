package org.mybatis.newgenerator.utils;

public abstract class Logger {

    public static void writeLine(String message) {
        System.out.println(message);
    }

    public static void writeLine() {
        System.out.println();
    }
}

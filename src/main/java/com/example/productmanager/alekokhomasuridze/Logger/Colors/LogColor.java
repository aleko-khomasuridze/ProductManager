package com.example.productmanager.alekokhomasuridze.Logger.Colors;

public enum LogColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    // Custom colors in 256-color mode
    BRIGHT_RED("\u001B[38;2;255;0;89m"),
    BRIGHT_GREEN("\u001B[38;2;0;255;100m"),
    BRIGHT_YELLOW("\u001B[38;2;255;205;0m"),
    BRIGHT_BLUE("\u001B[38;2;0;131;255m");
//    CUSTOM_COLOR("\u001B[38;2;255;165;0m");  // Orange

    private final String code;

    LogColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

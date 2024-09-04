package com.example.productmanager.alekokhomasuridze.Logger.Utils;


import com.example.productmanager.alekokhomasuridze.Logger.Colors.LogColor;
import com.example.productmanager.alekokhomasuridze.Logger.Module.LogTime.LogTime;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    public static String formatLogMessage(String logLevel, String msg, Class<?> clazz, String methodName) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, String.class);
            if (method.isAnnotationPresent(LogTime.class)) {
                LogTime logTime = method.getAnnotation(LogTime.class);
                String currentTime = getFormattedDate(logTime.format());
                String color = logTime.color().toString();

                // Apply color to the prefix and reset color for the message body
                return String.format("%s[%s]~[%s]:%s %s", color, logLevel, currentTime, LogColor.RESET, msg);
            }
        } catch (NoSuchMethodException e) {
            System.out.print(e.getMessage());
        }
        return String.format("[%s]~[]: %s", logLevel, msg);
    }

    private static String getFormattedDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
}

package com.example.productmanager.alekokhomasuridze.Logger.Module.LogTime;

import com.example.productmanager.alekokhomasuridze.Logger.Colors.LogColor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogTime {
    String format() default "yyyy-MM-dd HH:mm:ss";
    LogColor color() default LogColor.RESET;
}

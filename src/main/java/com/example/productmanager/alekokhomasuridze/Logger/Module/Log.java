package com.example.productmanager.alekokhomasuridze.Logger.Module;


import com.example.productmanager.alekokhomasuridze.Logger.Colors.LogColor;
import com.example.productmanager.alekokhomasuridze.Logger.Module.LogTime.LogTime;
import com.example.productmanager.alekokhomasuridze.Logger.Utils.LogUtils;

public class Log {

    @LogTime(color = LogColor.BRIGHT_RED)
    public void Error(String msg) {
        String formattedMessage = LogUtils.formatLogMessage("Error", msg, Log.class, "Error");
        System.out.println(formattedMessage);
    }

    @LogTime(color = LogColor.BRIGHT_GREEN)
    public void Success(String msg) {
        String formattedMessage = LogUtils.formatLogMessage("Success", msg, Log.class, "Success");
        System.out.println(formattedMessage);
    }

    @LogTime(color = LogColor.BRIGHT_YELLOW)
    public void Warning(String msg) {
        String formattedMessage = LogUtils.formatLogMessage("Warning", msg, Log.class, "Warning");
        System.out.println(formattedMessage);
    }

    @LogTime(color = LogColor.BRIGHT_BLUE)
    public void Info(String msg) {
        String formattedMessage = LogUtils.formatLogMessage("Info", msg, Log.class, "Info");
        System.out.println(formattedMessage);
    }
}

package com.lida.autotests.utils;

import lombok.extern.log4j.Log4j2;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class LoggerUtils {

    public static String getNestedCallerClassMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        List<StackTraceElement> stackTraceList = Arrays.asList(stackTrace);

        if (stackTraceList.size() > 3) { // Ensure there are enough elements
            return stackTraceList.get(3).getClassName() + "." + stackTraceList.get(3).getMethodName();
        } else {
            return "UnknownMethod";
        }
    }
}

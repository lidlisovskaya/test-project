package com.lida.autotests.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class LoggerUtils {
    public static String getNestedCallerClassMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement nestedCallerClass =
                Arrays.stream(stackTrace)
                        .dropWhile(e -> !e.getClassName().contains("autotests.apps.digitalheroes.model.pages"))
                        .toList().getFirst();

        String className = nestedCallerClass.getClassName();
        String methodName = nestedCallerClass.getMethodName();

        String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
        return simpleClassName + ": " + methodName;
    }


    public static String formatErrorMessage(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String[] stackTraceLines = sw.toString().split("\n");
        String[] first20Lines = Arrays.copyOfRange(stackTraceLines, 0, Math.min(stackTraceLines.length, 20));

        StringBuilder formattedError = new StringBuilder();
        formattedError.append("Error:\n");
        for (int i = 0; i < first20Lines.length; i++) {
            formattedError.append("\t");
            if (i > 0) formattedError.append("\t");
            formattedError.append(first20Lines[i].trim()).append("\n");
        }

        return formattedError.toString();
    }
}

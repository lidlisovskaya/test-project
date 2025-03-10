package com.lida.autotests.utils;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.nio.file.FileSystems;

import static java.lang.String.format;

@Log4j2
public class FileUtils {

    public static final String FILE_DELIMITER = FileSystems.getDefault().getSeparator();
    private static File file;

    public static String getDownloadedFilePath() {
        String[] pathNames = {"src", "test", "downloads"};
        String pathSecondPart = String.join(FILE_DELIMITER, pathNames);
        return format("%s%s%s", System.getProperty("user.dir"),FILE_DELIMITER, pathSecondPart);
    }

    public static void cleanOldReports() {
        deleteAllFilesInFolder("allure-results");
        deleteAllFilesInFolder("target/logs");
    }

    public static void deleteAllFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.delete()) {
                        log.info("Deleted file: {}", file.getName());
                    } else {
                        log.info("Failed to delete file: {}", file.getName());
                    }
                }
            }
        }
    }
}

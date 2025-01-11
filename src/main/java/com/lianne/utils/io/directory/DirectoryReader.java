package com.lianne.utils.io.directory;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class DirectoryReader {

    /**
     * @param directoryName is a given name of directory
     * @return an array of files in the given directory
     */
    public static File[] getFileObjectList(
            String directoryName
    ) {
        return getFileObjectList(directoryName, "");
    }

    /**
     *
     * @param directoryName is a given name of directory
     * @param fileType specifies a type of files to return
     * @return an array of files  of a given type in the given directory
     */
    public static File[] getFileObjectList(
            String directoryName, @NotNull String fileType
    ) {
        switch (fileType) {
            case "directory" -> {
                return new File(directoryName).listFiles(File::isDirectory);
            }
            case "file" -> {
                return new File(directoryName).listFiles(File::isFile);
            }
            default -> {
                return new File(directoryName).listFiles();
            }
        }
    }

}

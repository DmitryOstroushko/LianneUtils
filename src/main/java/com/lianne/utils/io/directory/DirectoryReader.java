package com.lianne.utils.io.directory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Objects;

/**
 * Utility class for reading the contents of a directory.
 * This class provides methods to retrieve files and directories within a given directory.
 *
 * <p>Usage Example:</p>
 * <pre>
 *     File[] allFiles = DirectoryReader.getFileObjectList("/path/to/directory");
 *     File[] directories = DirectoryReader.getFileObjectList("/path/to/directory", "directory");
 *     File[] files = DirectoryReader.getFileObjectList("/path/to/directory", "file");
 * </pre>
 */
public class DirectoryReader {

    /**
     * Retrieves an array of files and directories inside the given directory.
     *
     * @param directoryName The path of the directory to read.
     * @return An array of {@link File} objects representing the contents of the directory.
     *         Returns {@code null} if the directory does not exist or is not accessible.
     *
     * @throws NullPointerException If {@code directoryName} is null.
     * @throws SecurityException If read access to the directory is denied.
     */
    public static File[] getFileObjectList(String directoryName) {
        return getFileObjectList(directoryName, "");
    }

    /**
     * Retrieves an array of files and directories of a specified type inside the given directory.
     *
     * @param directoryName The path of the directory to read.
     * @param fileType      The type of files to retrieve. Possible values:
     *                      <ul>
     *                          <li>"directory" - Returns only directories</li>
     *                          <li>"file" - Returns only regular files</li>
     *                          <li>Any other value (including an empty string) - Returns both files and directories</li>
     *                      </ul>
     * @return An array of {@link File} objects representing the filtered contents of the directory.
     *         Returns {@code null} if the directory does not exist or is not accessible.
     *
     * @throws NullPointerException If {@code directoryName} or {@code fileType} is null.
     * @throws SecurityException If read access to the directory is denied.
     */
    public static File @Nullable [] getFileObjectList(String directoryName, @NotNull String fileType) {
        File directory = new File(directoryName);
        if (!directory.exists() || !directory.isDirectory()) {
            return null;
        }

        return switch (fileType.toLowerCase()) {
            case "directory" -> Objects.requireNonNull(directory.listFiles(File::isDirectory));
            case "file" -> Objects.requireNonNull(directory.listFiles(File::isFile));
            default -> directory.listFiles();
        };
    }

}

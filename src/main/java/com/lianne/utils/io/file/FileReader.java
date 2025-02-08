package com.lianne.utils.io.file;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class that provides different methods for reading files with various character encoding options.
 * The class offers the ability to read files using different readers:
 * <ul>
 *     <li><strong>InputStreamReader</strong> - converts byte streams into character streams</li>
 *     <li><strong>BufferedReader</strong> - buffers the character stream for efficient reading of large files</li>
 *     <li><strong>LineNumberReader</strong> - extends BufferedReader, keeping track of line numbers</li>
 * </ul>
 * This class is useful when reading files and performing operations such as line-by-line reading, buffering for performance, and tracking line numbers.
 *
 * <h3>Method Table</h3>
 * <table border="1">
 *     <thead>
 *         <tr><th>Class</th><th>Buffering</th><th>Reading Lines</th><th>Line Numbering</th></tr>
 *     </thead>
 *     <tbody>
 *         <tr><td>InputStreamReader</td><td>No</td><td>No</td><td>No</td></tr>
 *         <tr><td>BufferedReader</td><td>Yes</td><td>Yes</td><td>No</td></tr>
 *         <tr><td>LineNumberReader</td><td>Yes</td><td>Yes</td><td>Yes</td></tr>
 *     </tbody>
 * </table>
 */
public class FileReader {

    /**
     * Returns an InputStreamReader for the specified file, converting byte streams to character streams.
     *
     * <h4>Usage:</h4>
     * <pre>
     * InputStreamReader reader = FileReader.getInputStreamReader("file.txt");
     * int character;
     * while ((character = reader.read()) != -1) {
     *     System.out.print((char) character);
     * }
     * </pre>
     *
     * @param fileName the name of the file to read
     * @return an InputStreamReader for the specified file
     * @throws FileNotFoundException if the file does not exist
     */
    @Contract("_ -> new")
    public static @NotNull InputStreamReader getInputStreamReader(String fileName) throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(fileName));
    }

    /**
     * Returns an InputStreamReader for the specified file with a specific character encoding.
     *
     * <h4>Usage:</h4>
     * <pre>
     * InputStreamReader reader = FileReader.getInputStreamReader("file.txt", "UTF-8");
     * int character;
     * while ((character = reader.read()) != -1) {
     *     System.out.print((char) character);
     * }
     * </pre>
     *
     * @param fileName the name of the file to read
     * @param charsetName the name of the character encoding (e.g., "UTF-8")
     * @return an InputStreamReader for the specified file with the given charset
     * @throws FileNotFoundException if the file does not exist
     * @throws UnsupportedEncodingException if the charset is not supported
     */
    public static @NotNull InputStreamReader getInputStreamReader(
            String fileName, String charsetName
    ) throws FileNotFoundException, UnsupportedEncodingException {
        return new InputStreamReader(new FileInputStream(fileName), charsetName);
    }

    /**
     * Returns an InputStreamReader for the specified file using a specific Charset.
     *
     * <h4>Usage:</h4>
     * <pre>
     * InputStreamReader reader = FileReader.getInputStreamReader("file.txt", Charset.forName("UTF-8"));
     * int character;
     * while ((character = reader.read()) != -1) {
     *     System.out.print((char) character);
     * }
     * </pre>
     *
     * @param fileName the name of the file to read
     * @param charset the Charset to use for decoding
     * @return an InputStreamReader for the specified file with the given charset
     * @throws FileNotFoundException if the file does not exist
     */
    public static @NotNull InputStreamReader getInputStreamReader(
            String fileName, Charset charset
    ) throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(fileName), charset);
    }

    /**
     * Returns an InputStreamReader for the specified file using a CharsetDecoder.
     *
     * <h4>Usage:</h4>
     * <pre>
     * InputStreamReader reader = FileReader.getInputStreamReader("file.txt", Charset.forName("UTF-8").newDecoder());
     * int character;
     * while ((character = reader.read()) != -1) {
     *     System.out.print((char) character);
     * }
     * </pre>
     *
     * @param fileName the name of the file to read
     * @param charset the CharsetDecoder to use for decoding
     * @return an InputStreamReader for the specified file with the given CharsetDecoder
     * @throws FileNotFoundException if the file does not exist
     */
    public static @NotNull InputStreamReader getInputStreamReader(
            String fileName, CharsetDecoder charset
    ) throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(fileName), charset);
    }

    /**
     * Returns a BufferedReader for the specified file, which reads text more efficiently using an internal buffer.
     *
     * <h4>Usage:</h4>
     * <pre>
     * BufferedReader reader = FileReader.getBufferedReader("file.txt");
     * String line;
     * while ((line = reader.readLine()) != null) {
     *     System.out.println(line);
     * }
     * </pre>
     *
     * @param fileName the name of the file to read
     * @return a BufferedReader for the specified file
     * @throws FileNotFoundException if the file does not exist
     */
    @Contract("_ -> new")
    public static @NotNull BufferedReader getBufferedReader(String fileName) throws FileNotFoundException {
        return new BufferedReader(getInputStreamReader(fileName));
    }

    /**
     * Returns a LineNumberReader for the specified file, which extends BufferedReader and tracks the current line number.
     *
     * <h4>Usage:</h4>
     * <pre>
     * LineNumberReader reader = FileReader.getLineNumberReader("file.txt");
     * String line;
     * while ((line = reader.readLine()) != null) {
     *     System.out.println("Line " + reader.getLineNumber() + ": " + line);
     * }
     * </pre>
     *
     * @param fileName the name of the file to read
     * @return a LineNumberReader for the specified file
     * @throws FileNotFoundException if the file does not exist
     */
    @Contract("_ -> new")
    public static @NotNull LineNumberReader getLineNumberReader(String fileName) throws FileNotFoundException {
        return new LineNumberReader(getInputStreamReader(fileName));
    }

    /**
     * Reads the specified file and returns a map where keys are line numbers and values are the corresponding lines.
     *
     * <h4>Usage:</h4>
     * <pre>
     * Map<Integer, String> lines = FileReader.readFileNumberLines("file.txt");
     * lines.forEach((lineNumber, line) -> {
     *     System.out.println("Line " + lineNumber + ": " + line);
     * });
     * </pre>
     *
     * @param fileName the name of the file to read
     * @return a Map where keys are line numbers and values are the corresponding lines
     * @throws FileNotFoundException if the file does not exist
     */
    public static @NotNull Map<Integer, String> readFileNumberLines(String fileName) throws FileNotFoundException {
        Map<Integer, String> lines = new HashMap<>();
        LineNumberReader reader = getLineNumberReader(fileName);
        reader.lines().forEach(line -> lines.put(reader.getLineNumber(), line));
        return lines;
    }

    /**
     * Reads the specified file and returns a list of lines as strings.
     *
     * <h4>Usage:</h4>
     * <pre>
     * List<String> lines = FileReader.readFileLines("file.txt");
     * lines.forEach(System.out::println);
     * </pre>
     *
     * @param fileName the name of the file to read
     * @return a List of lines from the file
     * @throws FileNotFoundException if the file does not exist
     */
    public static @NotNull List<String> readFileLines(String fileName) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = getBufferedReader(fileName);
        reader.lines().forEach(lines::add);
        return lines;
    }
}

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
 * | Class             | Buffering   | Reading of lines | Lines numbering |
 * |-------------------|-------------|------------------|-----------------|
 * | InputStreamReader | No          | No               | No              |
 * | BufferedReader    | Yes         | Yes              | No              |
 * | LineNumberReader  | Yes         | Yes              | Yes             |
 */

public class FileReader {

    /**
     * A method returns InputStreamReader object for a file with a given file name.
     * Purpose: to convert a stream of bytes (InputStream) to a stream of characters (Reader).
     * When to use: to read data from a byte stream (such as a file, network, or socket) and
     * convert it to characters.
     * It allows to set the encoding.

     * @param fileName is a file name for processing
     * @return InputStreamReader object: a stream of bytes
     * @throws FileNotFoundException if a file processing exception occurs

     * The example of using:
     * InputStreamReader reader = new getInputStreamReader("file.txt");
     * int character;
     * while ((character = reader.read()) != -1) {
     *     System.out.print((char) character);
     * }

     */
    @Contract("_ -> new")
    public static @NotNull InputStreamReader getInputStreamReader(
            String fileName
    ) throws FileNotFoundException {
        // InputStream gives bytes
        return new InputStreamReader(new FileInputStream(fileName));
    }

    public static @NotNull InputStreamReader getInputStreamReader(
            String fileName, String charsetName
    ) throws FileNotFoundException, UnsupportedEncodingException {
        // InputStream gives bytes
        return new InputStreamReader(new FileInputStream(fileName), charsetName);
    }

    public static @NotNull InputStreamReader getInputStreamReader(
            String fileName, Charset charset
    ) throws FileNotFoundException {
        // InputStream gives bytes
        return new InputStreamReader(new FileInputStream(fileName), charset);
    }

    public static @NotNull InputStreamReader getInputStreamReader(
            String fileName, CharsetDecoder charset
    ) throws FileNotFoundException {
        // InputStream gives bytes
        return new InputStreamReader(new FileInputStream(fileName), charset);
    }

    /**
     * A method returns BufferedReader object for a file with a given file name
     * Purpose: to buffer stream of characters for more efficient reading.
     * Features:
     *  • allows to read text line by line using the readLine() method.
     * 	• uses an internal buffer to minimize the number of I/O operations (reducing overhead).
     * When to use:
     *  • to read text line by line or in large blocks.
     *  • when performance is important when reading data.

     * @param fileName is a file name for processing
     * @return BufferedReader object: a list of lines
     * @throws FileNotFoundException if a file processing exception occurs

     * The example of using:
     * BufferedReader bufferedReader = new getBufferedReader("file.txt");
     * String line;
     * while ((line = bufferedReader.readLine()) != null) {
     *     System.out.println(line);
     * }
     */
    @Contract("_ -> new")
    public static @NotNull BufferedReader getBufferedReader(
            String fileName
    ) throws FileNotFoundException {
        // Reader gives lines
        return new BufferedReader(getInputStreamReader(fileName));
    }

    /**
     * A method returns LineNumberReader object for a file with a given file name.
     * Purpose: Extends the functionality of BufferedReader by adding the ability to track line numbers.
     * Stores the current line number, accessible through
     * the getLineNumber() and setLineNumber(int lineNumber) methods.

     * @param fileName is a file name for processing
     * @return LineNumberReader object: a stream of characters
     * @throws FileNotFoundException if a file processing exception occurs

     * The example of using:
     * LineNumberReader lineNumberReader = new getLineNumberReader("file.txt");
     * String line;
     * while ((line = lineNumberReader.readLine()) != null) {
     *     System.out.println("Line " + lineNumberReader.getLineNumber() + ": " + line);
     * }
     */
    @Contract("_ -> new")
    public static @NotNull LineNumberReader getLineNumberReader(String fileName) throws FileNotFoundException {
        // Reader gives characters
        return new LineNumberReader(getInputStreamReader(fileName));
    }

    /**
     * A method returns a Map object for a file with a given file name
     * @param fileName is a file name for processing
     * @return Map<Integer, String> object: keys are line numbers, values are lines
     * @throws FileNotFoundException if a file processing exception occurs
     */
    public static @NotNull Map<Integer, String> readFileNumberLines(
            String fileName
    ) throws FileNotFoundException {
        Map<Integer, String> lines = new HashMap<>();
        LineNumberReader lineNumberReader = getLineNumberReader(fileName);
        lineNumberReader.lines().forEach(line -> lines.put(lineNumberReader.getLineNumber(), line));
        return lines;
    }

    /**
     * A method returns a List object for a file with a given file name
     * @param fileName is a file name for processing
     * @return List<String> object
     * @throws FileNotFoundException if a file processing exception occurs
     */
    public static @NotNull List<String> readFileLines(
            String fileName
    ) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        (getBufferedReader(fileName)).lines().forEach(lines::add);
        return lines;
    }

}

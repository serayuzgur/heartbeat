package com.heartbeat.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A class for helper methods about FileStreams
 *
 * @see java.io.FileInputStream
 * @see java.io.FileOutputStream
 */
public class FileStreams {


    /**
     * A fast and primitive file reading method for small contents.
     *
     * @param file source
     * @return
     * @throws IOException
     */
    public static final String readFile(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        is.close();
        return new String(bytes);
    }

    /**
     * A fast and primitive file writing method for small contents.
     *
     * @param file destination
     * @param data to write
     * @return
     * @throws IOException
     */
    public static final void writeFile(File file, String data) throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        byte[] bytes = data.getBytes();
        os.write(bytes);
        os.close();
    }

}

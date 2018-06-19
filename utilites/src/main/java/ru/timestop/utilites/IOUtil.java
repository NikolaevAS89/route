package ru.timestop.utilites;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * @author NikolaevAS89
 * @version 1.0.0
 * @since 12.10.2017
 */
public class IOUtil {

    public static void copy(InputStream is, OutputStream out) throws IOException {
        byte[] buff = new byte[255];
        int readed=0;
        while((readed = is.read(buff))>0){
            out.write(buff, 0, readed);
        }
        out.flush();
        out.close();
    }

    public static void closeQuiet(Closeable io) {
        try {
            io.close();
        } catch (Exception e) {
            //SKIP
        }
    }


    public static void closeQuiet(HttpURLConnection connection) {
        try {
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Exception e) {
            //SKIP
        }
    }
}

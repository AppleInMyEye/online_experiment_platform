package com.ustc.oep.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author YuJianhua
 * @create 2023-03-22 11:25
 */
public class Utils {
    public static byte[] readNBytes(InputStream is, int n) throws IOException {
        byte[] buffer = new byte[n];
        int bytesRead = 0;
        int count;
        while (bytesRead < n && (count = is.read(buffer, bytesRead, n - bytesRead)) != -1) {
            bytesRead += count;
        }
        if (bytesRead != n) {
            throw new IOException("Could not read requested number of bytes");
        }
        return buffer;
    }
}

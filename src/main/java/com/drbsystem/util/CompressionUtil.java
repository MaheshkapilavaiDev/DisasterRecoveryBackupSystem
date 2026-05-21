package com.drbsystem.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class CompressionUtil {

    public static String compress(String filePath) throws IOException {

        String gzipPath = filePath + ".gz";

        try (GZIPOutputStream gos =
                     new GZIPOutputStream(new FileOutputStream(gzipPath))) {

            Files.copy(Paths.get(filePath), gos);
        }

        return gzipPath;
    }
}

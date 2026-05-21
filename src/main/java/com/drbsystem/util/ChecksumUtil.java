package com.drbsystem.util;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;

public class ChecksumUtil {

    public static String generateChecksum(File file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] bytes = Files.readAllBytes(file.toPath());
        byte[] digest = md.digest(bytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}

package com.drbsystem.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

	private final String SECRET_KEY = "1234567890123456";

	public String encryptFile(String sourceFile) throws Exception {

		String encryptedFile = "C:/backups/encrypted/encrypted-" + System.currentTimeMillis() + ".aes";

		SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(Cipher.ENCRYPT_MODE, key);

		FileInputStream fis = new FileInputStream(sourceFile);

		FileOutputStream fos = new FileOutputStream(encryptedFile);

		CipherOutputStream cos = new CipherOutputStream(fos, cipher);

		byte[] buffer = new byte[1024];

		int read;

		while ((read = fis.read(buffer)) != -1) {

			cos.write(buffer, 0, read);
		}

		fis.close();
		cos.close();

		return encryptedFile;
	}
}

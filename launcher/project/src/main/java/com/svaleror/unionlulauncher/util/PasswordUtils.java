package com.svaleror.unionlulauncher.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author S.Valeror
 */
public class PasswordUtils {

	private static final char[] PASSWORD = "soadhaishfsdaiufhasdiufh".toCharArray();
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
			(byte) 0x10, (byte) 0x12, };

	private static byte[] base64Decode(String property) throws IOException {
		return Base64.getDecoder().decode(property);
	}

	private static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static String decrypt(String property) throws GeneralSecurityException, IOException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
	}
}

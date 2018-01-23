/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.modpackupdater.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author S.Valeror
 */
public class NetworkUtils {

	public static boolean isInternetOn() {
		boolean result = false;
		try {
			URL url = new URL("http://www.google.com");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			if (con.getResponseCode() == 200) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;

	}

	public static void unzipFromUrl(String url, String outputFolder) throws MalformedURLException, IOException {

		File output = new File(outputFolder);
		if (output.exists()) {
			FileUtils.forceDelete(output);
		}
		output.mkdir();
		ZipInputStream zis = null;
		try {

			zis = new ZipInputStream(new URL(url).openStream());
			ZipEntry entry;

			while ((entry = zis.getNextEntry()) != null) {

				File entryFile = new File(output, entry.getName());
				if (entry.isDirectory()) {

					if (entryFile.exists()) {
						System.out.println("Directory" + entryFile + " already exists!");
					} else {
						entryFile.mkdirs();
					}

				} else {

					if (entryFile.getParentFile() != null && !entryFile.getParentFile().exists()) {
						entryFile.getParentFile().mkdirs();
					}

					if (!entryFile.exists()) {
						entryFile.createNewFile();
					}

					OutputStream os = null;
					try {
						os = new FileOutputStream(entryFile);
						IOUtils.copy(zis, os);
					} finally {
						IOUtils.closeQuietly(os);
					}
				}
			}
		} finally {
			IOUtils.closeQuietly(zis);
		}
	}
}

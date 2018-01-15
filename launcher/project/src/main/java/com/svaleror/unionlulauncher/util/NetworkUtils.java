/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import javax.net.SocketFactory;

/**
 *
 * @author S.Valeror
 */
public class NetworkUtils {

	private static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

	private static String sendGet(String url) throws IOException {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}

	public static boolean isInternetOn() {
		boolean result = false;
		try {
			sendGet("http://www.google.com");
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean isServerOn() {
		boolean open = true;
		try {
			Socket socket = SocketFactory.getDefault().createSocket();
			socket.setSoTimeout(5000);
			socket.connect(new InetSocketAddress("unionlu.duckdns.org", 25565));
			socket.close();
		} catch (Exception e) {
			open = false;
		}
		return open;
	}

	public static int makeLogin(String user, String password) {
		int result = 0;
		try {
			String loginUrl = "http://unionlu.duckdns.org/login_launcher.php?user=#{username}&password=#{password}";
			String loginOk = "loginWasOK";
			String loginBad = "loginWasBAD";
			loginUrl = loginUrl.replace("#{username}", user);
			loginUrl = loginUrl.replace("#{password}", password);
			String page = sendGet(loginUrl);
			if (!page.contains(loginBad) && page.contains(loginOk)) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getStackTrace()[0]);
			result = -1;
		}
		return result;
	}

	public static String getLastVersion() {
		String result = "-1";
		try {
			String versionUrl = "https://raw.githubusercontent.com/VSeryi/mcunionlu/master/version.txt";
			result = sendGet(versionUrl);
		} catch (Exception e) {
			e.printStackTrace();
			result = "-1";
		}
		return result;
	}
}

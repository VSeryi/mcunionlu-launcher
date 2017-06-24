/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.util;

import java.io.BufferedReader;
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

    public static boolean isInternetOn() {
        boolean result = false;
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                result = true;
            }
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
            socket.connect(new InetSocketAddress("http://unionlu.duckdns.org", 25565));
            socket.close();
        } catch (Exception e) {
            open = false;
        }
        return open;
    }

    public static boolean makeLogin(String user, String password) {
        boolean result = false;
        try {
            String loginUrl = "http://unionlu-svalero.rhcloud.com/login.php?user=#{username}&password=#{password}";
            String loginOk = "loginWasOK";
            String loginBad = "loginWasBAD";
            loginUrl = loginUrl.replace("#{username}", user);
            loginUrl = loginUrl.replace("#{password}", password);
            URL url = new URL(loginUrl);
            HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                if (!sb.toString().contains(loginBad) && sb.toString().contains(loginOk)) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getStackTrace()[0]);
            result = false;
        }
        return result;
    }

    public static String getLastVersion() {
        String result = "-1";
        try {
            String versionUrl = "https://raw.githubusercontent.com/VSeryi/mcunionlu/master/version.txt";
            URL url = new URL(versionUrl);
            HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }
}

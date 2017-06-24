/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.updater.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

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


    public static String getLastVersion() {
        String result = "-1";
        try {
            String versionUrl = "https://raw.githubusercontent.com/VSeryi/mcunionlu-launcher/master/version-launcher.txt";
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

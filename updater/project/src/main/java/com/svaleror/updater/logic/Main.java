/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.updater.logic;

import com.svaleror.updater.util.NetworkUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 *
 * @author S.Valeror
 */
public class Main {

    public static void main(String[] args) {
        try{
        System.out.println("Iniciando La Union - Launcher...");
        if (!NetworkUtils.isInternetOn()) {
            System.out.println("No se ha detectado internet. Iniciando directamente...");
            runLauncher();
        }
        String actualVersion = NetworkUtils.getLastVersion();
        if (!actualVersion.equals(getVersion())) {
            System.out.println("Encontrado una actualización, actualizando...");
            URL website = new URL("https://raw.githubusercontent.com/VSeryi/mcunionlu-launcher/master/launcher/bin/UnionLuLauncher.exe");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("UnionLu Launcher.exe");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            writeVersion(actualVersion);
            System.out.println("ACTUALIZACIÖN COMPLETA");
        } else {
        	System.out.println("No se ha detectado ninguna actualización. Iniciando directamente...");
        }
        runLauncher();
        }catch(Exception e) {
            e.printStackTrace();
            System.err.println("ERROR: Consulte al administrador mostrando todo el log.");
        }
    }

    public static void runLauncher() {
    	try {
    		new ProcessBuilder("UnionLu Launcher.exe").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.exit(0);
    }

    public static String getVersion() {
        String result = "-1";
        try {
            File settings = new File("./settings");
            if (!settings.exists()) {
                settings.mkdir();
            }
            File txt = new File("./settings/version-launcher.txt");
            if (!txt.exists()) {
                txt.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(txt));
            result = br.readLine();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
        public static void writeVersion(String version) {
        try {
            File settings = new File("./settings");
            if (!settings.exists()) {
                settings.mkdir();
            }
            File txt = new File("./settings/version-launcher.txt");
            if (txt.exists()) {
                txt.delete();
            }
            txt.createNewFile();
            BufferedWriter br = new BufferedWriter(new FileWriter(txt));
            br.write(version);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

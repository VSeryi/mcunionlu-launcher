/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.logic;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.svaleror.unionlulauncher.util.PasswordUtils;

import uk.co.rx14.jmclaunchlib.util.OS;

/**
 *
 * @author S.Valeror
 */
public class Settings {

	private String user = "Steve";
	@JsonIgnore
	private String password = "";
	private String hashPassword = "";
	private boolean remember = false;
	private String version = "-1";
	private int ram = 4;
	@JsonIgnore
	private String ramString = "";
	private String javaPath = Paths
			.get(System.getProperty("java.home"), "bin", OS.getCURRENT() == OS.WINDOWS ? "javaw.exe" : "java")
			.toAbsolutePath().toString();

	public String getHashPassword() {
		return hashPassword;
	}

	public String getJavaPath() {
		return javaPath;
	}

	public String getPassword() {
		if (password.isEmpty()) {
			try {
				password = PasswordUtils.decrypt(hashPassword);
			} catch (GeneralSecurityException | IOException ex) {
				Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return password;
	}

	public int getRam() {
		return ram;
	}

	public String getRamString() {
		return "-Xmx" + ram + "G";
	}

	public String getUser() {
		return user;
	}

	public String getVersion() {
		return version;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public void setJavaPath(String javaPath) {
		this.javaPath = javaPath;
	}

	public void setPassword(String password) {
		try {
			hashPassword = PasswordUtils.encrypt(password);
		} catch (GeneralSecurityException | IOException ex) {
			Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
		}

		this.password = password;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}

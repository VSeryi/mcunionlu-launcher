package com.svaleror.modpackupdater.util;

public class OSUtils {
	public static boolean isWindows() {
		   return System.getProperty("os.name").startsWith("Windows");
	}

}

package com.svaleror.modpackupdater.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.svaleror.modpackupdater.util.NetworkUtils;
import com.svaleror.modpackupdater.util.OSUtils;

/**
 *
 * @author S.Valeror
 */
public class Main {

	private static String BASE_URL = "http://downloads.gtnewhorizons.com/";
	private static String CLIENT_URL = "#ClientPacks";
	private static String SERVER_URL = "#ServerPacks";

	private static String CLIENT_FOLDER = "." + System.getProperty("file.separator") + "client"
			+ System.getProperty("file.separator");
	private static String SERVER_FOLDER = "." + System.getProperty("file.separator") + "server"
			+ System.getProperty("file.separator");

	private static String MODS_FOLDER = "." + System.getProperty("file.separator") + "mods"
			+ System.getProperty("file.separator");

	private static String LOCAL_SERVER_FOLDER = "/var/games/minecraft/servers/UnionLU/";
	private static String LOCAL_CLIENT_FOLDER = "." + System.getProperty("file.separator") + "client-local"
			+ System.getProperty("file.separator");

	private static List<String> EDITABLE_SERVER_FOLDERS = Arrays.asList("mods", "config", "libraries", "scripts");
	private static List<String> EDITABLE_CLIENT_FOLDERS = Arrays.asList("mods", "config", "resourcepacks", "scripts",
			"resources");

	private static List<String> MODS_SERVER = Arrays.asList("fastcraft-1.23.jar", "BetterFps-1.0.1.jar");
	private static List<String> MODS_CLIENT = Arrays.asList("BetterFps-1.0.1.jar", "CustomSkinLoader_1.7.10-14.6a.jar",
			"OptiFine_1.7.10_HD_U_D6.jar");

	public static void deleteReauthOnClient() {
		try {
			File cfg = new File(LOCAL_CLIENT_FOLDER + System.getProperty("file.separator") + "config"
					+ System.getProperty("file.separator") + "ReAuth.cfg");
			if (cfg.exists()) {
				FileUtils.forceDelete(cfg);
			}

			File mods = new File(LOCAL_CLIENT_FOLDER + System.getProperty("file.separator") + "mods");

			PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:ReAuth*.jar");

			for (File value : mods.listFiles()) {
				if (matcher.matches(value.toPath())) {
					FileUtils.forceDelete(value);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getLastVersionLink(String url) {
		JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.AMERICA_NEWYORK).build());
		driver.get(url);
		driver.findElementByXPath("//*[@id=\"table\"]/thead/tr/th[3]").click();
		String link = driver.findElementByXPath("//*[@id=\"list\"]/tr[1]/td[1]/a").getAttribute("href");
		driver.quit();
		return link;
	}

	public static String getVersion() {
		String result = "-1";
		try {
			File settings = new File("./settings");
			if (!settings.exists()) {
				settings.mkdir();
			}
			File txt = new File("./settings/version-modpack.txt");
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

	public static void main(String[] args)
			throws MalformedURLException, IOException, NoFilepatternException, GitAPIException {
		String lastServerVersionLink = getLastVersionLink(BASE_URL + SERVER_URL);
		String lastClientVersionLink = getLastVersionLink(BASE_URL + CLIENT_URL);
		String[] splittedServerLink = lastServerVersionLink.split("/");
		String[] splittedClientLink = lastClientVersionLink.split("/");
		String lastServerVersion = splittedServerLink[splittedServerLink.length - 1]
				.replaceAll("GTNewHorizonsServer-1.7.10-", "").replaceAll(".zip", "")
				.replaceAll("GTNewHorizonsServer1.7.10-", "");
		String lastClientVersion = splittedClientLink[splittedClientLink.length - 1]
				.replaceAll("GTNewHorizonsClient-1.7.10-", "").replaceAll(".zip", "")
				.replaceAll("GTNewHorizonsClient1.7.10-", "");

		if (!lastServerVersion.equalsIgnoreCase(lastClientVersion)
				|| lastServerVersion.equalsIgnoreCase(getVersion())) {
			return;
		}

		NetworkUtils.unzipFromUrl(lastServerVersionLink, SERVER_FOLDER);
		NetworkUtils.unzipFromUrl(lastClientVersionLink, CLIENT_FOLDER);

		for (String folder : EDITABLE_SERVER_FOLDERS) {
			File fileToDelete = new File(LOCAL_SERVER_FOLDER + folder);
			if (fileToDelete.exists()) {
				FileUtils.forceDelete(fileToDelete);
			}

			File fileToMove = new File(SERVER_FOLDER + folder);
			FileUtils.moveDirectoryToDirectory(fileToMove, new File(LOCAL_SERVER_FOLDER), true);

		}

		for (String mod : MODS_SERVER) {
			File fileToMove = new File(MODS_FOLDER + mod);
			File target = new File(LOCAL_SERVER_FOLDER + System.getProperty("file.separator") + "mods");
			FileUtils.copyFileToDirectory(fileToMove, target);
		}

		File clientLocalFolder = new File(LOCAL_CLIENT_FOLDER);
		Git git = null;
		if (OSUtils.isWindows()) {
			git = Git.cloneRepository().setFs(new FS_Win32_NoGit()).setURI("https://github.com/VSeryi/mcunionlu.git")
					.setDirectory(clientLocalFolder).call();
		} else {
			git = Git.cloneRepository().setURI("https://github.com/VSeryi/mcunionlu.git")
					.setDirectory(clientLocalFolder).call();
		}

		for (String folder : EDITABLE_CLIENT_FOLDERS) {
			File fileToDelete = new File(LOCAL_CLIENT_FOLDER + folder);
			if (fileToDelete.exists()) {
				FileUtils.forceDelete(fileToDelete);
			}

			File fileToMove = new File(CLIENT_FOLDER + System.getProperty("file.separator") + "minecraft"
					+ System.getProperty("file.separator") + folder);

			FileUtils.moveDirectoryToDirectory(fileToMove, new File(LOCAL_CLIENT_FOLDER), true);

		}

		for (String mod : MODS_CLIENT) {
			File fileToMove = new File(MODS_FOLDER + mod);
			File target = new File(LOCAL_CLIENT_FOLDER + System.getProperty("file.separator") + "mods");
			FileUtils.copyFileToDirectory(fileToMove, target);
		}

		deleteReauthOnClient();
		writeVersionOnClient(lastServerVersion);

		git.add().setUpdate(true).addFilepattern(".").call();
		git.add().addFilepattern(".").call();
		git.commit().setMessage(lastServerVersion + " updated.").call();

		CredentialsProvider cp = new UsernamePasswordCredentialsProvider("VSeryi", "kesitos13");
		git.push().setCredentialsProvider(cp).call();

		writeVersion(lastServerVersion);

		FileUtils.forceDelete(new File(CLIENT_FOLDER));
		FileUtils.forceDelete(new File(SERVER_FOLDER));
		FileUtils.forceDelete(new File(LOCAL_CLIENT_FOLDER));
	}

	public static void writeVersion(String version) {
		try {
			File settings = new File("./settings");
			if (!settings.exists()) {
				settings.mkdir();
			}
			File txt = new File("./settings/version-modpack.txt");
			if (txt.exists()) {
				FileUtils.forceDelete(txt);
			}
			txt.createNewFile();
			BufferedWriter br = new BufferedWriter(new FileWriter(txt));
			br.write(version);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeVersionOnClient(String version) {
		try {
			File txt = new File(LOCAL_CLIENT_FOLDER + System.getProperty("file.separator") + "version.txt");
			if (txt.exists()) {
				FileUtils.forceDelete(txt);
			}
			txt.createNewFile();
			BufferedWriter br = new BufferedWriter(new FileWriter(txt));
			br.write("0.0.1-" + version);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

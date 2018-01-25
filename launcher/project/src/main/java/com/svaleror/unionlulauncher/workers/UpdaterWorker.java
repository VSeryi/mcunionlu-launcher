/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.workers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.svaleror.unionlulauncher.gui.MainUi;
import com.svaleror.unionlulauncher.logic.FS_Win32_NoGit;
import com.svaleror.unionlulauncher.util.NetworkUtils;
import com.svaleror.unionlulauncher.util.OSUtils;

/**
 *
 * @author S.Valeror
 */
public class UpdaterWorker extends SwingWorker<Object, Object> {

	private MainUi parent;
	private JProgressBar progressBar;
	File gitignore = new File("./.gitignore");
	String gitignoreContent = "/server.dat\n/options.txt\n/optionsof.txt\n/resourcepacks/*\n/journeymap/*\n/saves/*";

	public UpdaterWorker(MainUi parent, JProgressBar progressBar) {
		super();
		this.parent = parent;
		this.progressBar = progressBar;
	}

	@Override
	protected Object doInBackground() throws Exception {
		Git git = null;
		File minecraftFolder = new File("./minecraft/");
		System.out.println();

		if (minecraftFolder.exists()) {
			try {
				progressBar.setString("Inicializando mods...");
				git = Git.open(minecraftFolder);
			} catch (IOException ex) {
				progressBar.setString("Borrando basura...");
				for (File f : minecraftFolder.listFiles()) {
					f.delete();
				}
			}
		}

		try {
			if (git == null) {
				progressBar.setString("Descargando mods...");
				if (OSUtils.isWindows()) {
					git = Git.cloneRepository().setFs(new FS_Win32_NoGit())
							.setURI("https://github.com/VSeryi/mcunionlu.git").setDirectory(minecraftFolder).call();
				} else {
					git = Git.cloneRepository().setURI("https://github.com/VSeryi/mcunionlu.git")
							.setDirectory(minecraftFolder).call();
				}
				writeGitIgnore();

			} else {
				String actualVersion = NetworkUtils.getLastVersion();
				if (!actualVersion.equals(parent.settings.getVersion())) {

					progressBar.setString("Actualizando mods...");
					git.reset().call();
					git.checkout().setAllPaths(true).call();
					git.clean().setCleanDirectories(true).setForce(true).setIgnore(true).call();
					git.pull().call();
					parent.settings.setVersion(actualVersion);
				}
			}
		} catch (GitAPIException ex) {
			progressBar.setIndeterminate(false);
			progressBar.setString("ERROR AL ACTUALIZAR");
		}
		return 0;
	}

	@Override
	protected void done() {
		parent.runMinecraft(true);
	}

	public void writeGitIgnore() {
		try {
			if (gitignore.exists()) {
				gitignore.delete();
			}
			gitignore.createNewFile();
			BufferedWriter br = new BufferedWriter(new FileWriter(gitignore));
			br.write(gitignoreContent);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

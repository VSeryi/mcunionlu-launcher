/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.workers;

import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import com.svaleror.unionlulauncher.gui.MainUi;
import com.svaleror.unionlulauncher.logic.Settings;

import uk.co.rx14.jmclaunchlib.LaunchSpec;
import uk.co.rx14.jmclaunchlib.LaunchTask;
import uk.co.rx14.jmclaunchlib.LaunchTaskBuilder;

/**
 *
 * @author S.Valeror
 */
public class DataerWorker extends SwingWorker<Object, Object> {

	private MainUi parent;
	private JProgressBar progressBar;
	private LaunchTaskBuilder task = null;
	private LaunchTask launchTask = null;
	private Settings settings = new Settings();

	public DataerWorker(MainUi parent, JProgressBar progressBar, Settings settings, boolean online) {
		super();
		this.parent = parent;
		this.progressBar = progressBar;
		this.task = new LaunchTaskBuilder().setCachesDir("./data/")
				.setForgeVersion("1.7.10", "1.7.10-10.13.4.1614-1.7.10").setInstanceDir("./minecraft/")
				.setUsername(settings.getUser()).setOffline();
		this.settings = settings;
		if (!online) {
			this.task.setNetOffline();
		}
	}

	@Override
	protected Object doInBackground() throws Exception {
		launchTask = task.build();
		LaunchSpec spec = launchTask.getSpec();
		spec.getJvmArgs().add(settings.getRamString());
		progressBar.setString("Iniciando Minecraft...");
		spec.run(new File(settings.getJavaPath()).toPath());
		return 0;
	}

	@Override
	protected void done() {
		parent.closeLauncher();
	}

}

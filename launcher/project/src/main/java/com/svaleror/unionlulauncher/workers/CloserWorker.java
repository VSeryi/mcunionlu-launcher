/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.workers;

import static java.lang.Thread.sleep;

import javax.swing.SwingWorker;

import com.svaleror.unionlulauncher.gui.MainUi;

/**
 *
 * @author S.Valeror
 */
public class CloserWorker extends SwingWorker<Object, Object> {

	private MainUi parent;

	public CloserWorker(MainUi parent) {
		super();
		this.parent = parent;
	}

	@Override
	protected Object doInBackground() throws Exception {
		sleep(30000);
		return 0;
	}

	@Override
	protected void done() {
		parent.closeAll();
	}
}

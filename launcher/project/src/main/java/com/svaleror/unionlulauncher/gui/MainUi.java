/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.eclipse.jgit.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svaleror.unionlulauncher.logic.Settings;
import com.svaleror.unionlulauncher.util.NetworkUtils;
import com.svaleror.unionlulauncher.workers.CloserWorker;
import com.svaleror.unionlulauncher.workers.DataerWorker;
import com.svaleror.unionlulauncher.workers.UpdaterWorker;

/**
 *
 * @author S.Valeror
 */
public class MainUi extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1968606692259924036L;

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new MainUi().setVisible(true);
		});
	}

	private Integer lastValueRam = 4;
	public Settings settings = new Settings();
	ObjectMapper mapper = new ObjectMapper();

	File settingsFile = new File("./settings/settings.json");

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JCheckBox checkRemember;

	private javax.swing.JPasswordField fieldPassword;

	private javax.swing.JTextField fieldUser;

	private javax.swing.JFileChooser fileChooser;

	private javax.swing.JButton javaPath;

	private javax.swing.JLabel labelPassword;

	private javax.swing.JLabel labelRam;

	private javax.swing.JLabel labelUser;

	private javax.swing.JButton play;

	private javax.swing.JProgressBar progressBar;

	private javax.swing.JButton reset;

	private javax.swing.JSpinner spinnerRam;

	private javax.swing.JRadioButton statusServer;

	private org.jdesktop.beansbinding.BindingGroup bindingGroup;

	// public boolean makeUpdate(){
	// int choice = JOptionPane.showOptionDialog(this, "Se ha detectado una
	// actualización.Si continuas perderas todos los datos guardados de Minecraft,
	// del Launcher y se descargara todo de 0.", "AVISO IMPORTANTE",
	// JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
	// return choice == JOptionPane.YES_OPTION;
	// }

	public MainUi() {
		this.setLocationRelativeTo(null);
		initComponents();
		mainUiInit();
	}

	public void closeAll() {
		System.exit(0);
	}

	public void closeLauncher() {
		try {
			mapper.writeValue(settingsFile, settings);
		} catch (IOException ex) {
			Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		CloserWorker closer = new CloserWorker(this);
		closer.execute();
	}

	public void disableComponents() {
		fieldUser.setEnabled(false);
		fieldPassword.setEnabled(false);
		checkRemember.setEnabled(false);
		spinnerRam.setEnabled(false);
		play.setEnabled(false);
		javaPath.setEnabled(false);
		reset.setEnabled(false);
	}

	public void enableComponents() {
		fieldUser.setEnabled(true);
		fieldPassword.setEnabled(true);
		checkRemember.setEnabled(true);
		spinnerRam.setEnabled(true);
		play.setEnabled(true);
		javaPath.setEnabled(true);
		reset.setEnabled(true);
	}

	private void fieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fieldPasswordActionPerformed
		playActionPerformed(evt);
		play.requestFocus();
	}// GEN-LAST:event_fieldPasswordActionPerformed

	private void fieldUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fieldUserActionPerformed
		playActionPerformed(evt);
		play.requestFocus();
	}// GEN-LAST:event_fieldUserActionPerformed

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

		fileChooser = new javax.swing.JFileChooser();
		statusServer = new javax.swing.JRadioButton();
		labelUser = new javax.swing.JLabel();
		javaPath = new javax.swing.JButton();
		fieldUser = new javax.swing.JTextField();
		labelPassword = new javax.swing.JLabel();
		labelPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				labelPasswordMouseClicked(evt);
			}
		});
		fieldPassword = new javax.swing.JPasswordField();
		checkRemember = new javax.swing.JCheckBox();
		play = new javax.swing.JButton();
		progressBar = new javax.swing.JProgressBar();
		labelRam = new javax.swing.JLabel();
		spinnerRam = new javax.swing.JSpinner();
		reset = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Unionlu - Launcher");
		setResizable(false);

		statusServer.setForeground(new java.awt.Color(240, 240, 240));
		statusServer.setToolTipText("Server Offline");
		statusServer.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				statusServerActionPerformed(evt);
			}
		});

		labelUser.setText("Usuario:");

		javaPath.setText("Java");
		javaPath.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				javaPathActionPerformed(evt);
			}
		});

		fieldUser.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fieldUserActionPerformed(evt);
			}
		});

		labelPassword.setText("Contraseña (?):");

		@SuppressWarnings("rawtypes")
		org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
				org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, checkRemember,
				org.jdesktop.beansbinding.ObjectProperty.create(), fieldPassword,
				org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
		bindingGroup.addBinding(binding);

		fieldPassword.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fieldPasswordActionPerformed(evt);
			}
		});

		checkRemember.setText("Recordarme");

		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
				org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, play,
				org.jdesktop.beansbinding.ObjectProperty.create(), checkRemember,
				org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
		bindingGroup.addBinding(binding);

		play.setText("Jugar");

		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
				org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, fieldUser,
				org.jdesktop.beansbinding.ObjectProperty.create(), play,
				org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
		bindingGroup.addBinding(binding);

		play.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playActionPerformed(evt);
			}
		});

		labelRam.setText("RAM:");

		spinnerRam.setModel(new javax.swing.SpinnerNumberModel(4, 1, null, 1));
		spinnerRam.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinnerRamStateChanged(evt);
			}
		});

		reset.setText("Reset");
		reset.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				resetActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(play, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(checkRemember))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addComponent(javaPath, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(labelRam).addGap(12, 12, 12)
												.addComponent(spinnerRam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(
														reset, javax.swing.GroupLayout.PREFERRED_SIZE, 74,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup().addComponent(statusServer)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(labelUser))
										.addComponent(labelPassword))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(fieldUser).addComponent(fieldPassword))))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(11, 11, 11)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelUser).addComponent(fieldUser, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(statusServer))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(labelPassword).addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(play)
						.addComponent(checkRemember))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(reset)
						.addComponent(spinnerRam, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(labelRam).addComponent(javaPath))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		bindingGroup.bind();
		
		ImageIcon img = new ImageIcon(Base64.decode("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"));
	    this.setIconImage(img.getImage());

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void javaPathActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_javaPathActionPerformed
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			settings.setJavaPath(file.getAbsolutePath());
		}
	}// GEN-LAST:event_javaPathActionPerformed

	private void labelPasswordMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_fieldPasswordActionPerformed
		try {
			java.awt.Desktop.getDesktop().browse(new URI("http://unionlu.duckdns.org/"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// GEN-LAST:event_labelPasswordMouseClicked

	/**
	 * Creates new form login
	 */
	public void mainUiInit() {

		File settingsFolder = new File("./settings/");
		if (!settingsFolder.exists()) {
			settingsFolder.mkdir();
		}
		if (settingsFile.exists()) {
			try {
				settings = mapper.readValue(settingsFile, Settings.class);
			} catch (IOException ex) {
				Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
				JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		} else {
			try {
				mapper.writeValue(settingsFile, settings);
			} catch (IOException ex) {
				Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
				JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}

		fieldUser.setText(settings.getUser());
		fieldPassword.setText(settings.getPassword());
		checkRemember.setSelected(settings.isRemember());
		spinnerRam.setValue(new Integer(settings.getRam()));
		fileChooser.setSelectedFile(new File(settings.getJavaPath()));
		statusServerActionPerformed(null);
	}

	private void playActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_playActionPerformed
		disableComponents();
		if (fieldUser.getText().isEmpty() || fieldPassword.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "Debes rellenar usuario y contraseña.", "Error de Formulario",
					JOptionPane.ERROR_MESSAGE);
			enableComponents();
			return;
		}

		if (!NetworkUtils.isInternetOn()) {
			if (settings.getVersion().equals("-1")) {
				JOptionPane.showMessageDialog(this, "Debes tener internet en la primera ejecucion.",
						"Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
				enableComponents();
				return;
			} else {
				int choice = JOptionPane.showOptionDialog(this,
						"No se ha establecido conexión con el servidor. Se iniciará el Minecraft en modo Offline.",
						"AVISO IMPORTANTE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				if (choice == JOptionPane.YES_OPTION) {
					this.runMinecraft(false);
				} else {
					enableComponents();
					return;
				}
			}
		}

		int loginResult = NetworkUtils.makeLogin(fieldUser.getText(), String.valueOf(fieldPassword.getPassword()));
		if (loginResult != 1) {

			if (loginResult == 0) {
				JOptionPane.showMessageDialog(this,
						"La relacion usuario-contraseña es incorrecta. Vuelva a intentarlo.", "Error de Login",
						JOptionPane.ERROR_MESSAGE);
				enableComponents();
				return;
			}

			if (settings.getVersion().equals("-1")) {
				JOptionPane.showMessageDialog(this,
						"No se ha establecido conexión con el servidor para poder realizar la primera descarga. Por favor inténtelo más tarde.",
						"Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
				enableComponents();
				return;
			} else {
				int choice = JOptionPane.showOptionDialog(this,
						"No se ha establecido conexión con el servidor. Se iniciará el Minecraft en modo Offline.",
						"AVISO IMPORTANTE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				if (choice == JOptionPane.YES_OPTION) {
					this.runMinecraft(false);
				} else {
					enableComponents();
					return;
				}
			}
		}

		if (checkRemember.isSelected()) {
			settings.setRemember(true);
			settings.setUser(fieldUser.getText());
			settings.setPassword(String.valueOf(fieldPassword.getPassword()));
		} else {
			settings.setRemember(false);
			settings.setUser("");
			settings.setPassword("");
		}
		try {
			mapper.writeValue(settingsFile, settings);
		} catch (IOException ex) {
			Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		progressBar.setIndeterminate(true);
		progressBar.setString("Revisando version...");
		progressBar.setStringPainted(true);

		UpdaterWorker updater = new UpdaterWorker(this, progressBar);

		updater.execute();
	}// GEN-LAST:event_playActionPerformed

	private void resetActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_resetActionPerformed
		int choice = JOptionPane.showOptionDialog(this,
				"Si continuas perderas todos los datos guardados de Minecraft, del Launcher y se descargara todo de 0.",
				"AVISO IMPORTANTE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if (choice == JOptionPane.YES_OPTION) {
			settings = new Settings();
			try {
				mapper.writeValue(settingsFile, settings);
			} catch (IOException ex) {
				Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
				JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			mainUiInit();
		}
	}// GEN-LAST:event_resetActionPerformed

	public void runMinecraft(boolean online) {
		if (progressBar.isIndeterminate()) {
			progressBar.setString("Descargando datos...");
			DataerWorker dataer = new DataerWorker(this, progressBar, settings, online);
			dataer.execute();
		} else {
			enableComponents();
		}
	}

	private void spinnerRamStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinnerRamStateChanged
		if (lastValueRam != null && !spinnerRam.getValue().equals(lastValueRam)) {
			settings.setRam((int) spinnerRam.getValue());
		}
		lastValueRam = (int) spinnerRam.getValue();
	}// GEN-LAST:event_spinnerRamStateChanged

	private void statusServerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_statusServerActionPerformed
		if (NetworkUtils.isServerOn()) {
			statusServer.setSelected(true);
			statusServer.setToolTipText("Server Online");
		} else {
			statusServer.setSelected(false);
			statusServer.setToolTipText("Server Offline");
		}
	}// GEN-LAST:event_statusServerActionPerformed
}

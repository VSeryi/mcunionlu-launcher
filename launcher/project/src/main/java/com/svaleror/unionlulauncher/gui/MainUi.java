/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svaleror.unionlulauncher.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.svaleror.unionlulauncher.logic.Settings;
import com.svaleror.unionlulauncher.workers.DataerWorker;
import com.svaleror.unionlulauncher.util.NetworkUtils;
import com.svaleror.unionlulauncher.workers.CloserWorker;
import com.svaleror.unionlulauncher.workers.UpdaterWorker;
import javax.swing.JFileChooser;
import uk.co.rx14.jmclaunchlib.LaunchSpec;
import uk.co.rx14.jmclaunchlib.LaunchTask;
import uk.co.rx14.jmclaunchlib.LaunchTaskBuilder;

/**
 *
 * @author S.Valeror
 */
public class MainUi extends javax.swing.JFrame {

    private Integer lastValueRam = 4;
    public Settings settings = new Settings();
    ObjectMapper mapper = new ObjectMapper();
    File settingsFile = new File("./settings/settings.json");

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
                JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        } else {
            try {
                mapper.writeValue(settingsFile, settings);
            } catch (IOException ex) {
                Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }

        fieldUser.setText(settings.getUser());
        fieldPassword.setText(settings.getPassword());
        checkRemember.setSelected(settings.isRemember());
        spinnerRam.setValue(new Integer(settings.getRam()));
        fileChooser.setSelectedFile(new File(settings.getJavaPath()));
    }

    public MainUi() {
        this.setLocationRelativeTo(null);
        initComponents();
        mainUiInit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        fileChooser = new javax.swing.JFileChooser();
        statusServer = new javax.swing.JRadioButton();
        labelUser = new javax.swing.JLabel();
        javaPath = new javax.swing.JButton();
        fieldUser = new javax.swing.JTextField();
        labelPassword = new javax.swing.JLabel();
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
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusServerActionPerformed(evt);
            }
        });

        labelUser.setText("Usuario:");

        javaPath.setText("Java");
        javaPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javaPathActionPerformed(evt);
            }
        });

        fieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldUserActionPerformed(evt);
            }
        });

        labelPassword.setText("Contraseña:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, checkRemember, org.jdesktop.beansbinding.ObjectProperty.create(), fieldPassword, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        fieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPasswordActionPerformed(evt);
            }
        });

        checkRemember.setText("Recordarme");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, play, org.jdesktop.beansbinding.ObjectProperty.create(), checkRemember, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        play.setText("Jugar");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, fieldUser, org.jdesktop.beansbinding.ObjectProperty.create(), play, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        labelRam.setText("RAM:");

        spinnerRam.setModel(new javax.swing.SpinnerNumberModel(4, 1, null, 1));
        spinnerRam.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerRamStateChanged(evt);
            }
        });

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(play, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkRemember))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(javaPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelRam)
                        .addGap(12, 12, 12)
                        .addComponent(spinnerRam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(statusServer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelUser))
                            .addComponent(labelPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldUser)
                            .addComponent(fieldPassword))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelUser)
                        .addComponent(fieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statusServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPassword)
                    .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(play)
                    .addComponent(checkRemember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset)
                    .addComponent(spinnerRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRam)
                    .addComponent(javaPath))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldUserActionPerformed
        playActionPerformed(evt);
        play.requestFocus();
    }//GEN-LAST:event_fieldUserActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        disableComponents();
        if (fieldUser.getText().isEmpty() || fieldPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Debes rellenar usuario y contraseña.", "Error de Formulario", JOptionPane.ERROR_MESSAGE);
            enableComponents();
            return;
        }

        if (!NetworkUtils.isInternetOn()) {
            if (settings.getVersion().equals("-1")) {
                JOptionPane.showMessageDialog(this, "Debes tener internet en la primera ejecucion.", "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
                enableComponents();
                return;
            } else {
                int choice = JOptionPane.showOptionDialog(this, "No se ha establecido conexión con el servidor. Se iniciará el Minecraft en modo Offline.", "AVISO IMPORTANTE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                if (choice == JOptionPane.YES_OPTION) {
                    this.runMinecraft(true);
                } else {
                    enableComponents();
                    return;
                }
            }
        }
        
        int loginResult = NetworkUtils.makeLogin(fieldUser.getText(), fieldPassword.getText());
        if (loginResult != 1) {
            String message = "La relacion usuario-contraseña es incorrecta. Vuelva a intentarlo.";
            if(loginResult == -1) {
                message = "Ha habido un error en el servidor. Puede que estuviera dormido. Vuelva a intentarlo.";
            }
            JOptionPane.showMessageDialog(this, message, "Error de Login", JOptionPane.ERROR_MESSAGE);
            enableComponents();
            return;
        }

        statusServerActionPerformed(null);

        if (checkRemember.isSelected()) {
            settings.setRemember(true);
            settings.setUser(fieldUser.getText());
            settings.setPassword(fieldPassword.getText());
        } else {
            settings.setRemember(false);
            settings.setUser("");
            settings.setPassword("");
        }
        try {
            mapper.writeValue(settingsFile, settings);
        } catch (IOException ex) {
            Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        progressBar.setIndeterminate(true);
        progressBar.setString("Revisando version...");
        progressBar.setStringPainted(true);

        UpdaterWorker updater = new UpdaterWorker(this, progressBar);

        updater.execute();
    }//GEN-LAST:event_playActionPerformed

    private void fieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPasswordActionPerformed
        playActionPerformed(evt);
        play.requestFocus();
    }//GEN-LAST:event_fieldPasswordActionPerformed

    private void javaPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_javaPathActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            settings.setJavaPath(file.getAbsolutePath());
        }
    }//GEN-LAST:event_javaPathActionPerformed

    private void statusServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusServerActionPerformed
        if (NetworkUtils.isServerOn()) {
            statusServer.setSelected(true);
        } else {
            statusServer.setSelected(false);
        }
    }//GEN-LAST:event_statusServerActionPerformed

    private void spinnerRamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerRamStateChanged
        if (lastValueRam != null && !spinnerRam.getValue().equals(lastValueRam)) {
            settings.setRam((int) spinnerRam.getValue());
        }
        lastValueRam = (int) spinnerRam.getValue();
    }//GEN-LAST:event_spinnerRamStateChanged

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        int choice = JOptionPane.showOptionDialog(this, "Si continuas perderas todos los datos guardados de Minecraft, del Launcher y se descargara todo de 0.", "AVISO IMPORTANTE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        if (choice == JOptionPane.YES_OPTION) {
            settings = new Settings();
            try {
                mapper.writeValue(settingsFile, settings);
            } catch (IOException ex) {
                Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            mainUiInit();
        }
    }//GEN-LAST:event_resetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainUi().setVisible(true);
        });
    }

    public void runMinecraft(boolean online) {
        if (progressBar.isIndeterminate()) {
            progressBar.setString("Descargando datos...");
            DataerWorker dataer = new DataerWorker(this, progressBar, settings, online);
            dataer.execute();
        } else {
            enableComponents();
        }
    }

    public void closeLauncher() {
        try {
            mapper.writeValue(settingsFile, settings);
        } catch (IOException ex) {
            Logger.getLogger(MainUi.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Por favor ejecuta como administrador.", "Error de Ejecucion", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        CloserWorker closer = new CloserWorker(this);
        closer.execute();
    }
    
    public void closeAll() {
        System.exit(0);
    }
    
    
    
//    public boolean makeUpdate(){
//        int choice = JOptionPane.showOptionDialog(this, "Se ha detectado una actualización.Si continuas perderas todos los datos guardados de Minecraft, del Launcher y se descargara todo de 0.", "AVISO IMPORTANTE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
//        return choice == JOptionPane.YES_OPTION;
//    }

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
    // End of variables declaration//GEN-END:variables
}

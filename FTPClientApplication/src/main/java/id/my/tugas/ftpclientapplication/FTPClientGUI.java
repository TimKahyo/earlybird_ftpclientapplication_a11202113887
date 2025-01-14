/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package id.my.tugas.ftpclientapplication;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Mulya Cahyana
 */
public class FTPClientGUI extends javax.swing.JFrame {

    /**
     * Creates new form FTPClientGUI
     */
    private FTPConnectionManager connectionManager;

    // Variables declaration - do not modify
    
    // End of variables declaration

    public FTPClientGUI() {
        connectionManager = new FTPConnectionManager();
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true); // Set JFrame visible
        logArea.append("FTP Client GUI initialized.\n"); // Initial log message
        updateLocalTree(System.getProperty("user.home"), new DefaultMutableTreeNode(new File(System.getProperty("user.home")).getName()));
        updateServerTree("/", new DefaultMutableTreeNode("/"));
    }

    private void updateServerTree(String path, DefaultMutableTreeNode root) {
        String[] files = connectionManager.listFiles(path);
        root.removeAllChildren();
        for (String file : files) {
            root.add(new DefaultMutableTreeNode(file));
        }
        ((DefaultTreeModel) serverTree.getModel()).setRoot(root);
        ((DefaultTreeModel) serverTree.getModel()).reload();
    }

    private void updateLocalTree(String path, DefaultMutableTreeNode root) {
        File file = new File(path);
        File[] files = file.listFiles();
        root.removeAllChildren();
        if (files != null) {
            for (File f : files) {
                root.add(new DefaultMutableTreeNode(f.getName()));
            }
        }
        ((DefaultTreeModel) localTree.getModel()).setRoot(root);
        ((DefaultTreeModel) localTree.getModel()).reload();
    }

    private void downloadFile(String remotePath) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            boolean success = connectionManager.downloadFile(remotePath, fileToSave.getAbsolutePath());
            if (success) {
                logArea.append("File downloaded: " + fileToSave.getAbsolutePath() + "\n");
            } else {
                logArea.append("Failed to download file: " + remotePath + "\n");
            }
        }
    }

    private void uploadFile(String localPath) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Upload File");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedDir = fileChooser.getSelectedFile();
            String remotePath = selectedDir.getAbsolutePath() + "/" + new File(localPath).getName();
            boolean success = connectionManager.uploadFile(localPath, remotePath);
            if (success) {
                logArea.append("File uploaded: " + remotePath + "\n");
            } else {
                logArea.append("Failed to upload file: " + localPath + "\n");
            }
        }
    }

    private String getNodePath(DefaultMutableTreeNode node) {
        Object[] nodes = node.getPath();
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            path.append(nodes[i].toString());
            if (i < nodes.length - 1) {
                path.append("/");
            }
        }
        return path.toString();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        serverTextArea = new javax.swing.JLabel();
        serverField = new javax.swing.JTextField();
        portTextArea = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        userTextArea = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passTextArea = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        connectButton = new javax.swing.JToggleButton();
        splitPane = new javax.swing.JSplitPane();
        localTreeScrollPane = new javax.swing.JScrollPane();
        localTree = new javax.swing.JTree();
        serverTreeScrollPane = new javax.swing.JScrollPane();
        serverTree = new javax.swing.JTree();
        logScrollPane = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout(25, 25));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel2.setVerifyInputWhenFocusTarget(false);
        jPanel2.setLayout(new java.awt.GridLayout(5, 2));

        serverTextArea.setText("Server:");
        jPanel2.add(serverTextArea);

        serverField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverFieldActionPerformed(evt);
            }
        });
        jPanel2.add(serverField);

        portTextArea.setText("Port:");
        jPanel2.add(portTextArea);

        portField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portFieldActionPerformed(evt);
            }
        });
        jPanel2.add(portField);

        userTextArea.setText("Username:");
        jPanel2.add(userTextArea);
        jPanel2.add(userField);

        passTextArea.setText("Password:");
        jPanel2.add(passTextArea);
        jPanel2.add(passField);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPanel3);

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });
        jPanel2.add(connectButton);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        splitPane.setMinimumSize(new java.awt.Dimension(200, 100));
        splitPane.setPreferredSize(new java.awt.Dimension(205, 100));

        localTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                localTreeMouseClicked(evt);
            }
        });
        localTreeScrollPane.setViewportView(localTree);

        splitPane.setRightComponent(localTreeScrollPane);

        serverTree.setPreferredSize(new java.awt.Dimension(100, 74));
        serverTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serverTreeMouseClicked(evt);
            }
        });
        serverTreeScrollPane.setViewportView(serverTree);

        splitPane.setLeftComponent(serverTreeScrollPane);

        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        logScrollPane.setMinimumSize(new java.awt.Dimension(64, 64));
        logScrollPane.setPreferredSize(new java.awt.Dimension(200, 100));

        logArea.setColumns(20);
        logArea.setRows(5);
        logScrollPane.setViewportView(logArea);

        getContentPane().add(logScrollPane, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void portFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portFieldActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        final String server = serverField.getText();
        final int port;
        try {
            port = Integer.parseInt(portField.getText());
        } catch (NumberFormatException e) {
            logArea.append("Invalid port number: " + portField.getText() + "\n");
            return; // Exit method if port number is invalid
        }
        final String user = userField.getText();
        final String pass = new String(passField.getPassword());

        // Use SwingWorker to handle connection in a background thread
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return connectionManager.connect(server, port, user, pass);
            }

            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        logArea.append("Connected to " + server + "\n");
                        updateServerTree("/", new DefaultMutableTreeNode("/"));
                        updateLocalTree(System.getProperty("user.home"), new DefaultMutableTreeNode(new File(System.getProperty("user.home")).getName()));
                    } else {
                        logArea.append("Failed to connect to " + server + "\n");
                    }
                } catch (Exception e) {
                    logArea.append("An error occurred while connecting: " + e.getMessage() + "\n");
                }
            }
        }.execute();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void serverFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverFieldActionPerformed

    private void serverTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serverTreeMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }
            String selectedPath = getNodePath(node);
            if (node.isLeaf()) {
                downloadFile(selectedPath);
            } else {
                updateServerTree(selectedPath, node);
            }
        }
    }//GEN-LAST:event_serverTreeMouseClicked

    private void localTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_localTreeMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) localTree.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }
            String selectedPath = getNodePath(node);
            if (node.isLeaf()) {
                uploadFile(selectedPath);
            } else {
                updateLocalTree(selectedPath, node);
            }
        }
    }//GEN-LAST:event_localTreeMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton connectButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree localTree;
    private javax.swing.JScrollPane localTreeScrollPane;
    private javax.swing.JTextArea logArea;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel passTextArea;
    private javax.swing.JTextField portField;
    private javax.swing.JLabel portTextArea;
    private javax.swing.JTextField serverField;
    private javax.swing.JLabel serverTextArea;
    private javax.swing.JTree serverTree;
    private javax.swing.JScrollPane serverTreeScrollPane;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userTextArea;
    // End of variables declaration//GEN-END:variables
}

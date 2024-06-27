/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.my.tugas.ftpclientapplication;

/**
 *
 * @author Mulya Cahyana
 */
import java.io.FileInputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(FTPConnectionManager.class.getName());
    private FTPClient ftpClient; // remove final if necessary

    public FTPConnectionManager() {
        this.ftpClient = new FTPClient();
    }

    public boolean connect(String server, int port, String user, String pass) {
        try {
            ftpClient.connect(server, port);
            boolean login = ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return login;
        } catch (IOException ex) {
            System.err.println("Error connecting to FTP server:");
            System.err.println("Server: " + server);
            System.err.println("Port: " + port);
            System.err.println("User: " + user);
            ex.printStackTrace();
            return false;
        }
    }

    public String[] listFiles(String path) {
        try {
            return ftpClient.listNames(path);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error listing files", ex);
            return null;
        }
    }

    public boolean downloadFile(String remoteFilePath, String localFilePath) {
        try (OutputStream outputStream = new FileOutputStream(localFilePath)) {
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error downloading file", ex);
            return false;
        }
    }

    public boolean uploadFile(String localFilePath, String remoteFilePath) {
        try (FileInputStream inputStream = new FileInputStream(localFilePath)) {
            return ftpClient.storeFile(remoteFilePath, inputStream);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error uploading file", ex);
            return false;
        }
    }

    public void disconnect() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error disconnecting from FTP server", ex);
        }
    }
}

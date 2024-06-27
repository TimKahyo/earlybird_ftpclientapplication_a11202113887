/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package id.my.tugas.ftpclientapplication;

/**
 *
 * @author Mulya Cahyana
 */
public class FTPClientApplication {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FTPClientGUI();
            }
        });
    }
}

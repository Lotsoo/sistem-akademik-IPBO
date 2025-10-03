/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author lotso
 */
public class Database {

  static Properties myProperties;
  static String driver, database, user, pass;
  static Connection connection;

  public static Connection KoneksiDB() {
    if (connection == null) {
      try {
//        myProperties = new Properties();
//        myProperties.load(new FileInputStream("lib/database.ini"));
//        driver = myProperties.getProperty("DBDriver");
//        database = myProperties.getProperty("DBDatabase");
//        user = myProperties.getProperty("DBUsername");
//        pass = myProperties.getProperty("DBPassword");

        driver = "com.mysql.cj.jdbc.Driver";
        database = "jdbc:mysql://localhost/db_akademik";
        user = "lotso";
        pass = "123";

        Class.forName(driver);
        connection = DriverManager.getConnection(database, user, pass);
        JOptionPane.showMessageDialog(null, "Koneksi Berhasil!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Koneksi Tidak Berhasil!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Error:" + e.getMessage());
        System.out.println("Password dari file: " + pass);

      }
    }
    return connection;
  }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.varMahasiswa;
import java.util.List;
import javax.swing.JOptionPane;
import koneksi.Database;

/**
 *
 * @author Mahasiswa
 */
public class DAO_Mahasiswa implements DAO_Interface<varMahasiswa> {

    Connection connection;

    public DAO_Mahasiswa() {
        connection = Database.KoneksiDB();
    }

    String INSERT = "INSERT INTO mahasiswa(NIM, nama, alamat) VALUES(?,?,?)";
    String UPDATE = "UPDATE mahasiswa set nama=?, alamat=? WHERE NIM=?";
    String DELETE = "DELETE FROM mahasiswa WHERE NIM=?";
    String SELECT = "SELECT * FROM mahasiswa";
    String CARI = "SELECT * FROM mahasiwa WHERE NIM=?";

    @Override
    public void insert(varMahasiswa Object) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(CARI);
            preparedStatement.setString(1, Object.getvNIM());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            } else {
                preparedStatement = null;
                preparedStatement = connection.prepareStatement(INSERT);
                preparedStatement.setString(1, Object.getvNIM());
                preparedStatement.setString(2, Object.getvNama());
                preparedStatement.setString(3, Object.getvAlamat());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varMahasiswa Object) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, Object.getvNama());
            preparedStatement.setString(2, Object.getvAlamat());
            preparedStatement.setString(3, Object.getvNIM());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String nim) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, nim);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<varMahasiswa> getAll() {
        List<varMahasiswa> list = null;
        PreparedStatement preparedStatement = null;
        try {
            list = new ArrayList<varMahasiswa>();
            preparedStatement = connection.prepareStatement(SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                varMahasiswa m = new varMahasiswa();
                m.setvNIM(resultSet.getString("NIM"));
                m.setvNama(resultSet.getString("Nama"));
                m.setvAlamat(resultSet.getString("Alamat"));
                list.add(m);
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varMahasiswa> getCari(String key) {
        List<varMahasiswa> list = null;
        PreparedStatement preparedStatement = null;
        try {
            list = new ArrayList<varMahasiswa>();
            preparedStatement = connection.prepareStatement("SELECT");
            preparedStatement.setString(1, "%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                varMahasiswa m = new varMahasiswa();
                m.setvNIM(resultSet.getString("NIM"));
                m.setvNama(resultSet.getString("Nama"));
                m.setvAlamat(resultSet.getString("Alamat"));
                list.add(m);
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

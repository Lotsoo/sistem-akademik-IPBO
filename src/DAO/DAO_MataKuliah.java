/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import koneksi.Database;
import model.varMahasiswa;
import model.varMataKuliah;

/**
 *
 * @author Mahasiswa
 */
public class DAO_MataKuliah implements DAO_Interface<varMataKuliah> {

    Connection connection;

    public DAO_MataKuliah() {
        connection = Database.KoneksiDB();
    }

    String INSERT = "INSERT INTO matakuliah(KodeMTK, NamaMTK, SKS, KodePrasyarat) VALUES(?,?,?,?)";
    String UPDATE = "UPDATE matakuliah SET NamaMTK=?, SKS=?, KodePrasyarat=? WHERE KodeMTK=?";
    String DELETE = "DELETE FROM matakuliah WHERE KodeMTK=?";
    String SELECT = "SELECT * FROM matakuliah";
    String CARI = "SELECT * FROM matakuliah WHERE KodeMTK LIKE ? OR NamaMTK LIKE ?";

    @Override
    public void insert(varMataKuliah Object) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(CARI);
            preparedStatement.setString(1, Object.getKodeMTK());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            } else {
                preparedStatement = null;
                preparedStatement = connection.prepareStatement(INSERT);
                preparedStatement.setString(1, Object.getKodeMTK());
                preparedStatement.setString(2, Object.getNamaMTK());
                preparedStatement.setInt(3, Object.getSks());
                preparedStatement.setString(4, Object.getKodePrasyarat());

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            }

            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varMataKuliah Object) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, Object.getNamaMTK());
            preparedStatement.setInt(2, Object.getSks());
            preparedStatement.setString(3, Object.getKodePrasyarat());
            preparedStatement.setString(4, Object.getKodeMTK());

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, key);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<varMataKuliah> getAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        List<varMataKuliah> list = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                varMataKuliah mataKuliah = new varMataKuliah();

                mataKuliah.setKodeMTK(resultSet.getString("KodeMTK"));
                mataKuliah.setNamaMTK(resultSet.getString("NamaMTK"));
                mataKuliah.setSks(resultSet.getInt("SKS"));
                mataKuliah.setKodePrasyarat(resultSet.getString("KodePrasyarat"));

                list.add(mataKuliah);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public List<varMataKuliah> getCari(String key) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<varMataKuliah> list = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(CARI);
            String keyword = "%" + key + "%";
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                varMataKuliah mataKuliah = new varMataKuliah();
                mataKuliah.setKodeMTK(resultSet.getString("KodeMTK"));
                mataKuliah.setNamaMTK(resultSet.getString("NamaMTK"));
                mataKuliah.setSks(resultSet.getInt("SKS"));
                mataKuliah.setKodePrasyarat(resultSet.getString("KodePrasyarat"));

                list.add(mataKuliah);
            }

            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

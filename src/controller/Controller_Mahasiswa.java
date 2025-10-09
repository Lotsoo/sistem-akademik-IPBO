/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DAO_Interface;
import DAO.DAO_Mahasiswa;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.varMahasiswa;
import view.FrmMahasiswa;

/**
 *
 * @author Mahasiswa
 */
public class Controller_Mahasiswa {
    
    FrmMahasiswa form;
    DAO_Interface<varMahasiswa> model;
    List<varMahasiswa> list;
    String[] header;
    
    public Controller_Mahasiswa(FrmMahasiswa form) {
        this.form = form;
        model = new DAO_Mahasiswa();
        list = model.getAll();
        header = new String[]{"NIM", "Nama", "Alamat"};
        form.getTblMahasiswa().setShowGrid(true);
        form.getTblMahasiswa().setShowVerticalLines(true);
        form.getTblMahasiswa().setGridColor(Color.blue);
    }
    
    public void reset() {
        form.getTxtNIM().setText("");
        form.getTxtNama().setText("");
        form.getTxtAlamat().setText("");
        isiTabel();
    }
    
    public void isiTabel() {
        list = model.getAll();
        
        DefaultTableModel tableModel = new DefaultTableModel(null, header) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        
        Object[] data = new Object[header.length];
        for (varMahasiswa mahasiswa : list) {
            data[0] = mahasiswa.getvNIM();
            data[1] = mahasiswa.getvNama();
            data[2] = mahasiswa.getvAlamat();
            tableModel.addRow(data);
        }
        
        form.getTblMahasiswa().setModel(tableModel);
    }
    
    public void isiField(int row) {
        form.getTxtNIM().setText(list.get(row).getvNIM());
        form.getTxtNama().setText(list.get(row).getvNama());
        form.getTxtAlamat().setText(list.get(row).getvAlamat());
    }
    
    public void insert() {
        varMahasiswa m = new varMahasiswa();
        
        m.setvNIM(form.getTxtNIM().getText());
        m.setvNIM(form.getTxtNama().getText());
        m.setvNIM(form.getTxtAlamat().getText());
        
        model.insert(m);
    }
    
    public void update() {
        varMahasiswa m = new varMahasiswa();
        
        m.setvNIM(form.getTxtNIM().getText());
        m.setvNIM(form.getTxtNama().getText());
        m.setvNIM(form.getTxtAlamat().getText());
        
        model.update(m);
    }
    
    public void delete() {
        if (!form.getTxtNIM().getText().trim().isEmpty()) {
            String nim = form.getTxtNIM().getText();
            
            model.delete(nim);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus!");
        }
    }
    
    public void isiTabelCari() {
        list = model.getCari(form.getTxtNIM().getText().trim());
        DefaultTableModel tableModel = new DefaultTableModel(null, header);
        Object[] data = new Object[header.length];
        for (varMahasiswa m : list) {
            data[0] = m.getvNIM();
            data[1] = m.getvNama();
            data[2] = m.getvAlamat();
            tableModel.addRow(data);
        }
        
        form.getTblMahasiswa().setModel(tableModel);
    }
}

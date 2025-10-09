/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author Mahasiswa
 */
public interface DAO_Interface<A> {

    public void insert(A Object);

    public void update(A Object);

    public void delete(String key);

    public List<A> getAll();

    public List<A> getCari(String key);
}

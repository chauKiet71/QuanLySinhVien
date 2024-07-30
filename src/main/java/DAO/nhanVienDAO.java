/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.jdbcHeplper;
import ENTITY.nhanVien;
import java.util.List;
import ENTITY.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class nhanVienDAO extends EdusysDAO<nhanVien, String> {
    String INSERT = "INSERT INTO NHAN_VIEN (MA_NHAN_VIEN, MAT_KHAU, HO_VA_TEN, VAI_TRO) VALUES (?, ?, ?, ?)";
    String UPDATE = "UPDATE NHAN_VIEN SET MAT_KHAU = ?, HO_VA_TEN = ?, VAI_TRO = ? WHERE MA_NHAN_VIEN = ?";
    String DELETE = "DELETE FROM NHAN_VIEN WHERE MA_NHAN_VIEN = ?";
    String SELECT_BY_ID = "SELECT * FROM NHAN_VIEN WHERE MA_NHAN_VIEN = ?";
    String SELECT_ALL = "SELECT * FROM NHAN_VIEN";

    @Override
    public void insert(nhanVien entity) {
        jdbcHeplper.update(INSERT, entity.getMaNV(), entity.getHoTen(), entity.getMatKhau(), entity.getVaiTro());
    }

    @Override
    public void update(nhanVien entity) {
        jdbcHeplper.update(UPDATE, entity.getHoTen(), entity.getMatKhau(), entity.getVaiTro(), entity.getMaNV());
    }

    @Override
    public void delete(String Key) {
        jdbcHeplper.update(DELETE, Key);
    }

    @Override
    public List<nhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<nhanVien> selectBySql(String sql, Object... args) {
        List<nhanVien> list = new ArrayList<nhanVien>();
        try {
            ResultSet rs = jdbcHeplper.query(sql, args);
            while (rs.next()) {
                nhanVien entity = new nhanVien();
                entity.setMaNV(rs.getString("MA_NHAN_VIEN"));
                entity.setHoTen(rs.getString("HO_VA_TEN"));
                entity.setMatKhau("MAT_KHAU");
                entity.setVaiTro(rs.getBoolean("VAI_TRO"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public nhanVien selectById(String Key) {
        List<nhanVien> list = this.selectBySql(SELECT_BY_ID, Key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}

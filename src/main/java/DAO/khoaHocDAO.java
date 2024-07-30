/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.jdbcHeplper;
import ENTITY.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author PC
 */
public class khoaHocDAO extends EdusysDAO<khoaHoc, String> {

    String INSERT = "INSERT INTO KHOA_HOC (MA_KHOA_HOC, MA_CHUYEN_DE, MA_NHAN_VIEN, HOC_PHI, THOI_LUONG, NGAY_KHAI_GIANG, GHI_CHU, NGAY_TAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE = "UPDATE NGUOI_HOC SET MA_CHUYEN_DE = ?, MA_NHAN_VIEN = ?, HOC_PHI = ?, THOI_LUONG = ?, NGAY_KHAI_GIANG = ?, GHI_CHU = ?, NGAY_TAO = ? WHERE MA_KHOA_HOC = ?";
    String DELETE = "DELETE FROM KHOA_HOC WHERE MA_KHOA_HOC = ?";
    String SELECT_BY_ID = "SELECT * FROM KHOA_HOC WHERE MA_KHOA_HOC = ?";
    String SELECT_ALL = "SELECT * FROM KHOA_HOC";

    @Override
    public void insert(khoaHoc entity) {
        jdbcHeplper.update(INSERT, entity.getMaKhoaHoc(), entity.getMaChuyenDe(), entity.getMaNhanVien(), entity.getHocPhi(), entity.getNgayKhaiGiang(), entity.getGhiChu(), entity.getNgayTao());
    }

    @Override
    public void update(khoaHoc entity) {
       jdbcHeplper.update(UPDATE, entity.getMaChuyenDe(), entity.getMaNhanVien(), entity.getHocPhi(), entity.getNgayKhaiGiang(), entity.getGhiChu(), entity.getNgayTao(), entity.getMaKhoaHoc());
    }

    @Override
    public void delete(String Key) {
        jdbcHeplper.update(DELETE, Key);
    }

    @Override
    public khoaHoc selectById(String Key) {
        List<khoaHoc> list = this.selectBySql(SELECT_BY_ID,Key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<khoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<khoaHoc> selectBySql(String sql, Object... args) {
        List<khoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHeplper.query(sql, args);
            while (rs.next()) {                
                khoaHoc kh = new khoaHoc();
                kh.setMaKhoaHoc(rs.getInt("MA_KHOA_KHOA"));
                kh.setMaChuyenDe(rs.getString("MA_CHUYEN_DE"));
                kh.setMaNhanVien("MA_NHAN_VIEN");
                kh.setHocPhi(rs.getFloat("HOC_PHI"));
                kh.setThoiLuong(rs.getInt("THOI_LUONG"));
                kh.setNgayKhaiGiang(rs.getDate("NGAY_KHAI_GIANG"));
                kh.setGhiChu(rs.getString("GHI_CHU"));
                kh.setNgayTao(rs.getDate("NGAY_TAO"));
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

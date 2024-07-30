/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.jdbcHeplper;
import java.util.List;
import ENTITY.*;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author PC
 */
public class nguoiHocDAO extends EdusysDAO<nguoiHoc, String> {

    String INSERT = "IINSERT INTO NGUOI_HOC (MA_NGUOI_HOC, MA_NHAN_VIEN, HO_VA_TEN, NGAY_SINH, GIOI_TINH, SO_DIEN_THOAI, EMAIL, GHI_CHU, NGAY_DANG_KY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE = "UPDATE NGUOI_HOC SET MA_NHAN_VIEN = ?, HO_VA_TEN = ?, NGAY_SINH = ?, GIOI_TINH = ?, SO_DIEN_THOAI = ?, EMAIL = ?, GHI_CHU = ?, NGAY_DANG_KY WHERE MA_NGUOI_HOC = ?";
    String DELETE = "DELETE FROM NHAN_VIEN WHERE MA_NGUOI_HOC = ?";
    String SELECT_BY_ID = "SELECT * FROM NHAN_VIEN WHERE MA_NGUOI_HOC = ?";
    String SELECT_ALL = "SELECT * FROM NGUOI_HOC";

    @Override
    public void insert(nguoiHoc entity) {
        jdbcHeplper.update(INSERT, entity.getMaNguoiHoc(), entity.getMaNhanVien(), entity.getHoVaTen(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getSoDT(), entity.getEmail(), entity.getGhiChu(), entity.getNgayDangKy());
    }

    @Override
    public void update(nguoiHoc entity) {
        jdbcHeplper.update(UPDATE, entity.getMaNhanVien(), entity.getHoVaTen(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getSoDT(), entity.getEmail(), entity.getGhiChu(), entity.getNgayDangKy(), entity.getMaNguoiHoc());
    }

    @Override
    public void delete(String Key) {
        jdbcHeplper.update(DELETE, Key);
    }

    @Override
    public nguoiHoc selectById(String Key) {
        List<nguoiHoc> list = this.selectBySql(SELECT_BY_ID, Key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<nguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<nguoiHoc> selectBySql(String sql, Object... args) {
        List<nguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHeplper.query(sql, args);
            while (rs.next()) {                
                nguoiHoc nh = new nguoiHoc();
                nh.setMaNguoiHoc(rs.getString("MA_NGUOI_HOC"));
                nh.setMaNhanVien(rs.getString("MA_NHAN_VIEN"));
                nh.setHoVaTen(rs.getString("HO_VA_TEN"));
                nh.setNgaySinh(rs.getDate("NGAY_SINH"));
                nh.setGioiTinh(rs.getBoolean("GIOI_TINH"));
                nh.setSoDT(rs.getString("SO_DIEN_THOAI"));
                nh.setEmail(rs.getString("EMAIL"));
                nh.setGhiChu(rs.getString("GHI_CHU"));
                nh.setNgayDangKy(rs.getDate("NGAY_DANG_KY"));
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

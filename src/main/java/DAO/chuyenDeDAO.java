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
public class chuyenDeDAO extends EdusysDAO<chuyenDe, String> {

    String INSERT = "INSERT INTO CHUYEN_DE (MA_CHUYEN_DE, TEN_CHUYEN_DE, THOI_LUONG, HINH_LOGO, MO_TA_CHUYEN_DE) VALUES (?, ?, ?, ?, ?)";
    String UPDATE = "UPDATE CHUYEN_DE SET TEN_CHUYEN_DE = ?, THOI_LUONG = ?, HINH_LOGO = ?, MO_TA_CHUYEN_DE WHERE MA_CHUYEN_DE = ?";
    String DELETE = "DELETE FROM NHAN_VIEN WHERE MA_CHUYEN_DE = ?";
    String SELECT_BY_ID = "SELECT * FROM NHAN_VIEN WHERE MA_CHUYEN_DE = ?";
    String SELECT_ALL = "SELECT * FROM CHUYEN_DE";

    @Override
    public void insert(chuyenDe entity) {
        jdbcHeplper.update(INSERT, entity.getMaChuyenDe(), entity.getTenChuyenDe(), entity.getThoiLuong(), entity.getHinhLogo(), entity.getMoTa());
    }

    @Override
    public void update(chuyenDe entity) {
        jdbcHeplper.update(UPDATE, entity.getTenChuyenDe(), entity.getThoiLuong(), entity.getHinhLogo(), entity.getMoTa(), entity.getMaChuyenDe());
    }

    @Override
    public void delete(String Key) {
        jdbcHeplper.update(DELETE, Key);
    }

    @Override
    public chuyenDe selectById(String Key) {
        List<chuyenDe> list = this.selectBySql(SELECT_BY_ID, Key);
        if (list.isEmpty())  {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<chuyenDe> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<chuyenDe> selectBySql(String sql, Object... args) {
        List<chuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHeplper.query(sql, args);
            while (rs.next()) {
                chuyenDe cd = new chuyenDe();
                cd.setMaChuyenDe(rs.getString("MA_CHUYEN_DE"));
                cd.setTenChuyenDe(rs.getString("TEN_CHUYEN_DE"));
                cd.setThoiLuong(rs.getInt("THOI_LUONG"));
                cd.setHinhLogo(rs.getString("HINH_LOGO"));
                cd.setMoTa(rs.getString("MO_TA_CHUYEN_DE"));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

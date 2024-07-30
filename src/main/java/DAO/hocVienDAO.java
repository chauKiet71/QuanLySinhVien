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
public class hocVienDAO extends EdusysDAO<hocVien, String>{
    String INSERT = "INSERT INTO HOC_VIEN(MA_HOC_VIEN, MA_KHOA_HOC, MA_NGUOI_HOC, DIEM_TRUNG_BINH) VALUES (?, ?, ?, ?)";
    String UPDATE = "UPDATE NGUOI_HOC SET MA_KHOA_HOC = ?, MA_NGUOI_HOC = ?, DIEM_TRUNG_BINH = ? WHERE MA_HOC_VIEN = ?";
    String DELETE = "DELETE FROM HOC_VIEN WHERE MA_HOC_VIEN = ?";
    String SELECT_BY_ID = "SELECT * FROM HOC_VIEN WHERE MA_HOC_VIEN = ?";
    String SELECT_ALL = "SELECT * FROM HOC_VIEN";

    @Override
    public void insert(hocVien entity) {
        jdbcHeplper.update(INSERT, entity.getMaHocVien(), entity.getMaKhoaHoc(), entity.getMaNguoiHoc(), entity.getDiemTrungBinh());
    }

    @Override
    public void update(hocVien entity) {
        jdbcHeplper.update(UPDATE, entity.getMaKhoaHoc(), entity.getMaNguoiHoc(), entity.getDiemTrungBinh(), entity.getMaHocVien());
    }

    @Override
    public void delete(String Key) {
        jdbcHeplper.update(DELETE, Key);
    }

    @Override
    public hocVien selectById(String Key) {
        List<hocVien> list = this.selectBySql(SELECT_BY_ID, Key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<hocVien> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<hocVien> selectBySql(String sql, Object... args) {
        List<hocVien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHeplper.query(sql, args);
            while (rs.next()) {                
                hocVien hv = new hocVien();
                hv.setMaHocVien(rs.getInt("MA_HOC_VIEN"));
                hv.setMaKhoaHoc(rs.getInt("MA_KHOA_HOC"));
                hv.setMaNguoiHoc(rs.getString("MA_NGUOI_HOC"));
                hv.setDiemTrungBinh(rs.getFloat("DIEM_TRUNG_BINH"));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

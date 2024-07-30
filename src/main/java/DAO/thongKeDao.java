/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.jdbcHeplper;
import java.util.*;
import java.sql.*;

/**
 *
 * @author PC
 */
public class thongKeDao {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = jdbcHeplper.query(sql, args);
            while (rs.next()) {                
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getBangDiem(Integer makh) {
        String sql = "{CALL SP_BANG_DIEM}";
        String[] clos = {"MA_NGUOI_HOC", "HO_TEN", "DIEM_TRUNG_BINH"};
        return this.getListOfArray(sql, clos, makh);
    }

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALL SP_BANG_DIEM}";
        String[] clos = {"Nam", "soLuong", "dauTien", "cuoiCung"};
        return this.getListOfArray(sql, clos);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL SP_BANG_DIEM}";
        String[] clos = {"tenChuyenDe", "tongHocVien", "diemThapNhat", "diemCaoNhat", "diemTrungBinh"};
        return this.getListOfArray(sql, clos);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL SP_DOANH_THU}";
        String[] clos = {"tenChuyenDe", "soHocVien", "soKhoaHoc", "doanhThu", "thapNhat", "caoNhat", "trungBinh"};
        return this.getListOfArray(sql, clos, nam);
    }
}

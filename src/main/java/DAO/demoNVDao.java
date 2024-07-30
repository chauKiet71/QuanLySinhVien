/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ENTITY.nhanVien;

/**
 *
 * @author PC
 */
public class demoNVDao {
    public static void main(String[] args) {
        // Tạo đối tượng NhanVien
        nhanVien nhanVien = new nhanVien("NV001", "123456", "John Doe", true);
        
        // Tạo đối tượng NhanVienDAO
        nhanVienDAO nhanVienDAO = new nhanVienDAO();
        
        // Thêm nhân viên vào cơ sở dữ liệu
        nhanVienDAO.insert(nhanVien);
        
        // Lấy danh sách tất cả nhân viên từ cơ sở dữ liệu
//        List<NhanVien> danhSachNhanVien = nhanVienDAO.selectAll();
        
        // In ra danh sách nhân viên
        System.out.println("them thanh cong");
    }
}

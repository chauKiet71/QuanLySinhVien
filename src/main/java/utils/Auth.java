/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import ENTITY.nhanVien;

/**
 *
 * @author PC
 */
public class Auth {
    public static nhanVien user = null;
    
    public static void clenr() {
        Auth.user = null;
    }
    
    public static boolean islogin() {
        return Auth.user != null;
    }
    
    public static boolean isMamager() {
        return Auth.islogin() && user.getVaiTro();
    }
}

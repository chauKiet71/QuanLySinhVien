package duan_lab1;


import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kle99
 */
public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/img/fpt.png");
        return new ImageIcon(url).getImage();
    }
}

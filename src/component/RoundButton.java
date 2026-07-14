/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Cursor;
import javax.swing.JButton;

/**
 *
 * @author PIYUMAL
 */
public class RoundButton extends JButton{
    public RoundButton(){
        init();
    }
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:999;");
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author cucchiarini.cesare
 */
public class FormAtleti extends JFrame{
    
    public FormAtleti(){
        GestoreAtleti ga = new GestoreAtleti();
        GestoreGrafica gg = new GestoreGrafica(ga.getAtleti());
        
        this.setLayout(new GridLayout(4, 4));
        for(JProgressBar barra : gg.getBars()){
            this.add(barra);
            this.add(new JLabel("Atleta"));
        }
        
        this.setVisible(true);
        this.setSize(new java.awt.Dimension(500, 500));
        
        ga.start();
    }
}

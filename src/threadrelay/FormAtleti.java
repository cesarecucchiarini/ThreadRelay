/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author cucchiarini.cesare
 */
public class FormAtleti extends JFrame{
    
    public FormAtleti(){
        GestoreAtleti ga = new GestoreAtleti();
        GestoreGrafica gg = new GestoreGrafica(ga.getAtleti());
        
        JPanel panelBarre = new JPanel();
        panelBarre.setLayout(new GridLayout(4, 4));
        for(JProgressBar barra : gg.getBars()){
            panelBarre.add(barra);
            panelBarre.add(barra);
            panelBarre.add(barra);
            panelBarre.add(new JLabel("Atleta"));
        }
        this.add(panelBarre, BorderLayout.CENTER);
        
        JButton bottoneAvvio = new JButton("Start");
        bottoneAvvio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new Thread(ga).start();
                gg.startThreads();
                new Thread(()->{
                    bottoneAvvio.setEnabled(false);
                synchronized(ga){
                    try {
                        ga.wait();
                    } catch (InterruptedException ex) {
                        System.getLogger(FormAtleti.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
                bottoneAvvio.setEnabled(true);
                }).start();                
            }
        });
        this.add(bottoneAvvio, BorderLayout.SOUTH);
        
        this.setVisible(true);
        this.setSize(new java.awt.Dimension(500, 500));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.util.LinkedHashMap;
import javax.swing.*;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreGrafica {
    LinkedHashMap<Atleta, JProgressBar> bars = new LinkedHashMap<>();
    
    public GestoreGrafica(Atleta[] atleti){
        for(Atleta a : atleti){
            JProgressBar barra = new JProgressBar();
            bars.put(a, barra);
        }
    }
    
    public JProgressBar[] getBars(){
        return bars.values().toArray(JProgressBar[]::new);
    }
    
    public void startThreads(){
        for(Atleta a : bars.keySet()){
            JProgressBar barra = bars.get(a);
            barra.setValue(0);
            new Thread(() -> {               
                    while(a.getPercorso() != 100){
                        try {
                            synchronized(a){
                                a.wait();
                            }
                            SwingUtilities.invokeLater(() -> {
                                barra.setValue(a.getPercorso());
                            });
                        } 
                        catch (InterruptedException ex) {
                            System.getLogger(GestoreGrafica.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    }
            }).start();
        }
    }
}

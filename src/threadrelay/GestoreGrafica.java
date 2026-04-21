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
    private LinkedHashMap<Atleta, JProgressBar> bars = new LinkedHashMap<>();
    private BoxGara gara;
    
    public GestoreGrafica(Atleta[] atleti, BoxGara gara){
        for(Atleta a : atleti){
            JProgressBar barra = new JProgressBar();
            bars.put(a, barra);
        }
        this.gara = gara;
    }
    
    public JProgressBar[] getBars(){
        return bars.values().toArray(JProgressBar[]::new);
    }
    
    public void startThreads(){
        for(Atleta a : bars.keySet()){
            JProgressBar barra = bars.get(a);
            barra.setValue(0);
            new Thread(() -> {              
                    while(a.getPercorso() != 100 && gara.getGara()){
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
    
    public void pulisciBarre(){
        for(JProgressBar barra : bars.values()){
            barra.setValue(0);
        }
    }
}

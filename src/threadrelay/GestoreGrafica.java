/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author sergi
 */
public class GestoreGrafica implements Observer{
    private ArrayList<Atleta> atleti = new ArrayList<>();
    
    public GestoreGrafica(ArrayList<Atleta> atleti){
        for(Atleta a : atleti){
            a.addObserver(this);
            this.atleti.add(a);
        }
    }
    
    @Override
    public synchronized void update(Atleta atleta){
        SwingUtilities.invokeLater(()->{
            System.out.println("Atleta "+ atleti.indexOf(atleta) + " ha percorso " + atleta.getDistanzaPercorsa());
        });
    }
}

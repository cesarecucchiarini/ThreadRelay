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
    private FormAtleti formAtleti;
    
    public GestoreGrafica(ArrayList<Atleta> atleti, GestoreAtleti gestoreAtleti){
        for(Atleta a : atleti){
            a.addObserver(this);
            this.atleti.add(a);
        }
        formAtleti = new FormAtleti(atleti.size(), gestoreAtleti);
    }
    
    @Override
    public synchronized void update(Atleta atleta){
        SwingUtilities.invokeLater(()->{
            formAtleti.aggiornaBarra(atleti.indexOf(atleta), atleta.getDistanzaPercorsa());
            if(atleta.equals(atleti.getLast()) && atleta.getDistanzaPercorsa() == 100)
                formAtleti.garaFinita();
        });
    }
}

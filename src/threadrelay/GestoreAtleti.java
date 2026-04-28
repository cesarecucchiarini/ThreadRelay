/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.util.ArrayList;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreAtleti implements Observer{
    private ArrayList<Atleta> atleti = new ArrayList<>();
    private GestoreGrafica gestoreGrafica;
    
    public GestoreAtleti(int numeroAtleti, int velocita){       
        for(; numeroAtleti > 0; numeroAtleti--){
            Atleta a = new Atleta(velocita);
            a.addObserver(this);
            atleti.add(a);
        }
        gestoreGrafica = new GestoreGrafica(atleti);
    }
    
    public void cominciaGara(){
        new Thread(atleti.get(0)).start();
    }
    
    @Override
    public synchronized void update(Atleta atleta){
        if(atleta.getDistanzaPercorsa() == 90 && atleti.indexOf(atleta) != atleti.size()-1)
            new Thread(atleti.get(atleti.indexOf(atleta) + 1)).start();
    }
}

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
    private boolean garaIniziata = false;
    
    public GestoreAtleti(int numeroAtleti){       
        for(; numeroAtleti > 0; numeroAtleti--){
            Atleta a = new Atleta(0);
            a.addObserver(this);
            atleti.add(a);
        }
        gestoreGrafica = new GestoreGrafica(atleti, this);
    }
    
    public boolean getGaraIniziata(){
        return garaIniziata;
    }
    
    public synchronized void cominciaGara(){
        for(Atleta a : atleti){
            a.setDistanzaPercorsa(0);
        }
        garaIniziata = true;
        new Thread(atleti.get(0)).start();
    }

    public synchronized void impostaVelocita(int velocita){
        for(Atleta a : atleti){
            a.setVelocita(velocita);
        }
    }
    
    @Override
    public synchronized void update(Atleta atleta){
        if(atleta.getDistanzaPercorsa() == 90 && atleti.indexOf(atleta) != atleti.size()-1)
            new Thread(atleti.get(atleti.indexOf(atleta) + 1)).start();
        if(atleta.getDistanzaPercorsa() == 100 && atleti.indexOf(atleta) == atleti.size()-1)
            garaIniziata = false;
    }
}

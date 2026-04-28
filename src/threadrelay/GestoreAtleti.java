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
    int counter;
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
        counter = 0;
        garaIniziata = true;
        new Thread(atleti.get(counter++)).start();
    }

    public synchronized void impostaVelocita(int velocita){
        for(Atleta a : atleti){
            a.setVelocita(velocita);
        }
    }
    
    @Override
    public synchronized void update(Atleta atleta){
        if(atleta.getDistanzaPercorsa() == 90 && counter != atleti.size())
            new Thread(atleti.get(counter++)).start();
        
        else if(atleta.getDistanzaPercorsa() == 100 && counter == atleti.size())
            garaIniziata = false;
    }
}

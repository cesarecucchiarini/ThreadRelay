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
    }
    
    public synchronized void bloccaGara(){
        for(Atleta a : atleti){
            a.ferma();
        }
    }
    
    public synchronized void riprendiGara(){
        counter = 0;
        Atleta a = atleti.get(counter++);
        while(a.getDistanzaPercorsa() == 100){
            a = atleti.get(counter++);
        }
        new Thread(a).start();
        if(a.getDistanzaPercorsa() >= 90 && counter < atleti.size())
            new Thread(atleti.get(counter++)).start();
    }
    
    public synchronized void fermaGara(){
        for(Atleta a : atleti){
            a.ferma();
            a.setDistanzaPercorsa(0);
        }
    }
}

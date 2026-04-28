/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cucchiarini.cesare
 */
public class Atleta implements Subject, Runnable{
    private ArrayList<Observer> observers = new ArrayList<>();
    private int distanzaPercorsa;
    private int velocita;

    public Atleta(int velocita) {
        this.distanzaPercorsa = 0;
        this.velocita = velocita;
    }
    
    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers(){
        List<Observer> copia = List.copyOf(observers);
        for(Observer observer : copia){
            observer.update(distanzaPercorsa);
        }
    }

    public void setDistanzaPercorsa(int distanzaPercorsa) {
        this.distanzaPercorsa = distanzaPercorsa;
        notifyObservers();
    }

    public void setVelocita(int velocita) {
        this.velocita = velocita;
    }
    
    @Override 
    public void run(){
        for(; distanzaPercorsa <= 100; distanzaPercorsa++){
            try {
                Thread.sleep(velocita);
                setDistanzaPercorsa(distanzaPercorsa + 1);
            } catch (InterruptedException ex) {
                System.getLogger(Atleta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
}

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
    private int 
    
    public GestoreAtleti(int numeroAtleti, int velocita){
        for(; numeroAtleti > 0; numeroAtleti--){
            Atleta a = new Atleta(velocita);
            a.addObserver(this);
            atleti.add(a);
        }
    }
    
    @Override
    public void update(int distanzaPercorsa){
        System.out.println()
    }
}

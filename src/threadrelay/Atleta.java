/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author cucchiarini.cesare
 */
public class Atleta extends Thread{
    private volatile int percorso;
    private int tempo;
    
    public Atleta(){
        super();
        percorso = 0;
        tempo = 100;
    }
    
    public int getPercorso(){
        return percorso;
    }
    
    public void setTempo(int tempo){
        this.tempo = tempo;
    }
    
    @Override
    public void run(){
        for(percorso = 0; percorso < 100; percorso++){
            try{
                Thread.sleep(tempo);                
            }
            catch(Exception e){}
        }
    }
}

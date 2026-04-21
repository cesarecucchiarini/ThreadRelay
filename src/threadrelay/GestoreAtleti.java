/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreAtleti implements Runnable{
    private Atleta[] atleti = new Atleta[4];
    private BoxGara gara;
    
    public GestoreAtleti(){
        gara = new BoxGara();
        for(int i = 0; i < 4; i++){
            atleti[i] = new Atleta(gara);
        }
    }
    
    public void azzeraAtleti(){
        for(Atleta a : atleti){
            a.setPercorso(0);
        }
    }
    
    public void fermaGara(){
        gara.setGara(false);
    }
    
    public Boolean getGara(){
        return gara.getGara();
    }
    
    public BoxGara getBoxGara(){
        return gara;
    }
    
    @Override
    public void run(){
        gara.setGara(true);
        for(int i = 0; i < 4 && gara.getGara(); i++){
            final Atleta atleta = atleti[i];          
            new Thread(atleta).start();
            try {
                while(atleta.getPercorso() != 90 && gara.getGara()){
                    synchronized(atleta){
                        atleta.wait();
                    }
                }
            } catch (InterruptedException ex) {}
            
            if(i == 3){
                while(atleta.getPercorso() != 100 && gara.getGara()){
                    try{
                        synchronized(atleta){
                            atleta.wait();
                        }
                    }
                    catch(Exception e){}
                }
            }
        }
        synchronized(this){
            this.notify();
        }
    }
    
    public Atleta[] getAtleti(){
        return atleti;
    }
}

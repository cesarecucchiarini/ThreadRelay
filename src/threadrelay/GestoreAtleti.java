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
    
    public GestoreAtleti(){
        for(int i = 0; i < 4; i++){
            atleti[i] = new Atleta();
        }
    }
    
    @Override
    public void run(){
        for(int i = 0; i < 4; i++){
            final Atleta atleta = atleti[i];            
            new Thread(atleta).start();
            try {
                while(atleta.getPercorso() != 90){
                    synchronized(atleta){
                        atleta.wait();
                    }
                }
            } catch (InterruptedException ex) {
                System.getLogger(GestoreAtleti.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            if(i == 3)
                while(atleta.getPercorso() != 100){
                    try{
                        atleta.wait();
                    }
                    catch(Exception e){}
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreAtleti extends Thread{
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
            atleta.start();
            try {
                while(atleta.getPercorso() != 90){
                    synchronized(atleta){
                        atleta.wait();
                    }
                    System.out.println("sto aspettando "+atleta.getPercorso());
                }
            } catch (InterruptedException ex) {
                System.getLogger(GestoreAtleti.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    public Atleta[] getAtleti(){
        return atleti;
    }
}

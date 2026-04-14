/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreAtleti {
    Atleta[] atleti = new Atleta[4];
    
    public GestoreAtleti(){
        for(int i = 0; i < 4; i++){
            atleti[i] = new Atleta();
        }
    }
}

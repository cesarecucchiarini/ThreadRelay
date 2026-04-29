/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sergi
 */
public class FormAtleti extends JFrame{
    private static final Color COLORE_SFONDO = new Color(245, 247, 250);
    private static final Color COLORE_ACCENTO = new Color(41, 121, 255);
    ArrayList<JProgressBar> barre = new ArrayList<JProgressBar>();
    private JButton bottoneAvvia;
    private JButton bottoneFerma;
    private JButton bottoneSospendi;
    private JButton bottoneRiprendi;
    
    public FormAtleti(int numeroAtleti, GestoreAtleti gestoreAtleti){
        for(; numeroAtleti > 0; numeroAtleti--){
            JProgressBar barra = new JProgressBar(0, 100);
            barra.setStringPainted(true);
            barra.setFont(barra.getFont().deriveFont(Font.BOLD, 14f));
            barra.setPreferredSize(new Dimension(0, 34));
            barra.setForeground(COLORE_ACCENTO);
            barre.add(barra);
        }
        
        this.setTitle("Thread Relay");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLORE_SFONDO);
        
        JPanel pannelloBarre = new JPanel(new GridLayout(barre.size(), 1, 0, 12));
        pannelloBarre.setBackground(COLORE_SFONDO);
        pannelloBarre.setBorder(new EmptyBorder(20, 24, 12, 24));
        for(int i = 0; i < barre.size(); i++){
            JPanel riga = new JPanel(new BorderLayout(12, 0));
            riga.setBackground(COLORE_SFONDO);
            riga.setBorder(new EmptyBorder(4, 0, 4, 0));

            JLabel indiceAtleta = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            indiceAtleta.setFont(indiceAtleta.getFont().deriveFont(Font.BOLD, 20f));
            indiceAtleta.setForeground(Color.WHITE);
            indiceAtleta.setOpaque(true);
            indiceAtleta.setBackground(COLORE_ACCENTO);
            indiceAtleta.setPreferredSize(new Dimension(44, 44));
            indiceAtleta.setBorder(new EmptyBorder(4, 4, 4, 4));

            riga.add(indiceAtleta, BorderLayout.WEST);
            riga.add(barre.get(i), BorderLayout.CENTER);
            pannelloBarre.add(riga);
        }
        this.add(pannelloBarre, BorderLayout.CENTER);

        JComboBox<String> comboVelocita = new JComboBox<>(new String[]{"veloce", "medio", "lento"});
        comboVelocita.setSelectedItem("medio");
        comboVelocita.setFont(comboVelocita.getFont().deriveFont(Font.BOLD, 22f));
        comboVelocita.setPreferredSize(new Dimension(260, 64));
        gestoreAtleti.impostaVelocita(mappaVelocita((String) comboVelocita.getSelectedItem()));
        
        bottoneAvvia = new JButton("Avvia");
        bottoneAvvia.setFont(bottoneAvvia.getFont().deriveFont(Font.BOLD, 22f));
        bottoneAvvia.setPreferredSize(new Dimension(260, 64));
        bottoneAvvia.addActionListener(e -> {
                comboVelocita.setEnabled(false);
                bottoneAvvia.setEnabled(false);
                bottoneFerma.setEnabled(true);
                bottoneSospendi.setEnabled(true);
                gestoreAtleti.impostaVelocita(mappaVelocita((String) comboVelocita.getSelectedItem()));
                gestoreAtleti.cominciaGara();
            });
        
        bottoneFerma = new JButton("Ferma");
        bottoneFerma.setFont(bottoneFerma.getFont().deriveFont(Font.BOLD, 22f));
        bottoneFerma.setPreferredSize(new Dimension(260, 64));
        bottoneFerma.addActionListener(e -> {
                comboVelocita.setEnabled(true);
                bottoneFerma.setEnabled(false);
                bottoneSospendi.setEnabled(false);
                bottoneRiprendi.setEnabled(false);
                bottoneAvvia.setEnabled(true);
                gestoreAtleti.fermaGara();
            });
        bottoneFerma.setEnabled(false);
        
        bottoneSospendi = new JButton("Sospendi");
        bottoneSospendi.setFont(bottoneSospendi.getFont().deriveFont(Font.BOLD, 22f));
        bottoneSospendi.setPreferredSize(new Dimension(260, 64));
        bottoneSospendi.addActionListener(e -> {
                bottoneSospendi.setEnabled(false);
                bottoneRiprendi.setEnabled(true);
                gestoreAtleti.bloccaGara();
            });
        bottoneSospendi.setEnabled(false);
        
        bottoneRiprendi = new JButton("Riprendi");
        bottoneRiprendi.setFont(bottoneRiprendi.getFont().deriveFont(Font.BOLD, 22f));
        bottoneRiprendi.setPreferredSize(new Dimension(260, 64));
        bottoneRiprendi.addActionListener(e -> {
                bottoneRiprendi.setEnabled(false);
                bottoneFerma.setEnabled(true);
                bottoneSospendi.setEnabled(true);
                gestoreAtleti.riprendiGara();
            });
        bottoneRiprendi.setEnabled(false);
        
        JPanel pannelloBottone = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 8));
        pannelloBottone.setBackground(COLORE_SFONDO);
        pannelloBottone.setBorder(new EmptyBorder(12, 16, 20, 16));
        pannelloBottone.add(comboVelocita);
        pannelloBottone.add(bottoneAvvia);
        pannelloBottone.add(bottoneFerma);
        pannelloBottone.add(bottoneSospendi);
        pannelloBottone.add(bottoneRiprendi);
        this.add(pannelloBottone, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }
    
    public void aggiornaBarra(int index, int valore){
        barre.get(index).setValue(valore);
    }
    
    public int mappaVelocita(String etichetta){
        switch (etichetta){
            case "veloce":
                return 10;
            case "lento":
                return 50;
            default:
                return 25;
        }
    }
}

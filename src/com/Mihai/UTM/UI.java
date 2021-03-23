package com.Mihai.UTM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI {
    ArrayList<Character> Alphabet = new ArrayList<Character>();
    public void initAlphabet(){
        Alphabet.clear();
        for (int i = 47; i<71; i++){
            Alphabet.add((char)i);
        }
    }
    JFrame a = new JFrame("DES encryption algorithm by Mihai:");
    JButton Button = new JButton("Start!");
    JButton Button2 = new JButton("Random Values");
    JTextField message = new JTextField();
    JTextField SecondKey = new JTextField();
    JTextPane EncryptedMessage = new JTextPane();
    JTextArea Steps = new JTextArea();

    public void CreateFrame(){
        a.setLayout(new BorderLayout());
        message.setBounds(35,48,490,20);
        message.setHorizontalAlignment(JTextField.CENTER);
        Button.setBounds(220,350,85,20);
        Button2.setBounds(200,15,120,20);
        EncryptedMessage.setBounds(75,383,430,20);
        EncryptedMessage.setForeground(Color.RED);
        EncryptedMessage.setContentType("text/html"); // let the text pane know this is what you want
        EncryptedMessage.setForeground(Color.RED);
        EncryptedMessage.setEditable(false); // as before
        Steps.setBounds(20,420,485,50);
        //Steps.setContentType("text/html"); // let the text pane know this is what you want
        Steps.setForeground(Color.GRAY);
        Steps.setEditable(false);
        SecondKey.setBounds(160,80,200,20);
        SecondKey.setHorizontalAlignment(JTextField.CENTER);
        JLabel note1 = new JLabel("K+:");
        JLabel note2 = new JLabel("i:");
        JLabel note3 = new JLabel("C[i] & D[i]:");
        JLabel KInfo = new JLabel("\"K+\" represents a 56 bit value, obtained from the K and PC-1 permutation.");
        JLabel iInfo = new JLabel("\"i\" represents the index of the C and D (two halves) from K+.");
        JLabel Tabels = new JLabel("Used tables:");
        JTextArea Tabel1 = new JTextArea("int[] PC2 = {14, 17, 11, 24, 1, 5, \n" +
                "                   3, 28, 15, 6, 21, 10, \n" +
                "                   23, 19, 12, 4, 26, 8, \n" +
                "                   16, 7, 27, 20, 13, 2, \n" +
                "                  41, 52, 31, 37, 47, 55, \n" +
                "                  30, 40, 51, 45, 33, 48, \n" +
                "                  44, 49, 39, 56, 34, 53, \n" +
                "                  46, 42, 50, 36, 29, 32};");
        Tabel1.setEditable(false);
        JTextArea Tabel2 = new JTextArea("int[] shiftBits = {1, 1, 2, 2, 2, 2, 2, 2,\n" +
                "                          1, 2, 2, 2, 2, 2, 2, 1};");
        Tabel2.setEditable(false);
        note1.setBounds(10,48,200,20);
        note2.setBounds(150,80,200,20);
        note3.setBounds(5,380,200,20);
        KInfo.setBounds(10, 100,500, 40);
        iInfo.setBounds(10, 118,500,40);
        Tabels.setBounds(210,159,200,20);
        Tabel1.setBounds(10, 185, 245, 150);
        Tabel2.setBounds(270,185,245,150);
        a.add(note1);
        a.add(note2);
        a.add(note3);
        a.add(KInfo);
        a.add(iInfo);
        a.add(Tabels);
        a.add(Tabel1);
        a.add(Tabel2);
        a.add(SecondKey);
        a.add(Button);
        a.add(message);
        a.add(EncryptedMessage);
        a.add(Steps);
        a.add(Button2);
        a.setSize(530,550);
        a.setLayout(null);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }

    public void Button(){
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                Main.DES cipher = new Main.DES();
                String keyy = message.getText();
                String key = cipher.binToHex(keyy);
                key = key.toUpperCase();
                initAlphabet();

                try{
                    cipher.choice = Integer.parseInt(SecondKey.getText());
                }catch (NumberFormatException ex) {
                    //handle exception here
                    JOptionPane.showMessageDialog(null,"\"i\" conține simboluri care nu sunt acceptate,\nintroduceți doar numere pana la 16.");
                }
                if(cipher.choice==0){
                    JOptionPane.showMessageDialog(null,"\"i\" conține simboluri care nu sunt acceptate,\nintroduceți doar numere pana la 16.");
                }
                if (cipher.choice>16){
                    JOptionPane.showMessageDialog(null,"\"i\" conține simboluri care nu sunt acceptate,\nintroduceți doar numere pana la 16.");
                }
                cipher.getKeys(key);
                EncryptedMessage.setText(String.valueOf(cipher.fokenmessage));
                //int choice = Integer.parseInt(SecondKey.getText());
                Steps.setText(cipher.KinBinary);
                //Steps.append(cipher.permutation());


            }
        });
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.DES cipher = new Main.DES();
                String hexKey = cipher.randomHexString(14);
                message.setText(cipher.hextoBin(hexKey));
                SecondKey.setText(String.valueOf(cipher.randomNum));
            }
        });
    }
}

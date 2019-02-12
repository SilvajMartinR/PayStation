/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author michael
 */
public class PayStationGUI {
    
    private Date date;
    private PayStationImpl payStation;
    
    private final JButton configButton = new JButton();
    private final JButton nickelButton = new JButton();
    private final JButton dimeButton = new JButton();
    private final JButton quarterButton = new JButton();
    private final JButton buyButton = new JButton();
    private final JButton cancelButton = new JButton();
    
    private final JLabel dateLabel = new JLabel();
    private final JLabel moneyLabel = new JLabel();
    private final JLabel timeLabel = new JLabel();
    
    public PayStationGUI(Date date) {
        
        this.date = date;
        payStation = new PayStationImpl();
        
        this.draw();
        
    }
    
    private void draw() {
        
        //Main Winow
        JFrame frame = new JFrame("Pay Station");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        
        //Display window
        Dimension windowSize = new Dimension(350, 225);
        
        JPanel window = new JPanel();
        window.setSize(windowSize);
        window.setBackground(Color.white);
        window.setLocation(25, 25);
        window.setLayout(new OverlayLayout(window));
        frame.add(window);
        
        //Date 
        dateLabel.setText(date.toString());
        Font dateFont = new Font(dateLabel.getFont().getName(), Font.PLAIN, 16);
        dateLabel.setForeground(Color.BLACK);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setVerticalAlignment(JLabel.TOP);
        dateLabel.setMinimumSize(windowSize);
        dateLabel.setMaximumSize(windowSize);
        dateLabel.setPreferredSize(windowSize);
        dateLabel.setFont(dateFont);
        window.add(dateLabel);
        
        //Rate Strategy
        JLabel stratLabel = new JLabel();
        stratLabel.setText("Rate strategy text goes here");
        stratLabel.setForeground(Color.BLACK);
        stratLabel.setMinimumSize(windowSize);
        stratLabel.setMaximumSize(windowSize);
        stratLabel.setPreferredSize(windowSize);
        stratLabel.setVerticalAlignment(JLabel.BOTTOM);
        stratLabel.setHorizontalAlignment(JLabel.CENTER);
        window.add(stratLabel);
        
        //Money Inserted
        Font moneyFont = new Font(moneyLabel.getFont().getName(), Font.BOLD, 52);
        moneyLabel.setText("$00.00");
        moneyLabel.setForeground(Color.BLACK);
        moneyLabel.setFont(moneyFont);
        moneyLabel.setMinimumSize(windowSize);
        moneyLabel.setMaximumSize(windowSize);
        moneyLabel.setPreferredSize(windowSize);
        moneyLabel.setHorizontalAlignment(JLabel.LEFT);
        window.add(moneyLabel);
        
        //Time Purchased
        timeLabel.setText("00:00");
        timeLabel.setFont(moneyFont);
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setMinimumSize(windowSize);
        timeLabel.setMaximumSize(windowSize);
        timeLabel.setPreferredSize(windowSize);
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);
        window.add(timeLabel);
        
        //Config button
        configButton.setSize(80, 40);
        configButton.setLocation((400-80), (500-60));
        configButton.setText("Config");
        frame.add(configButton);
        
        setConfigListener();
        
        //Nickel button
        nickelButton.setSize(40, 80);
        nickelButton.setLocation(25, 265);
        nickelButton.setText("5¢");
        frame.add(nickelButton);
        
        setNickelListener();
        
        //Dime button
        dimeButton.setSize(40, 80);
        dimeButton.setLocation(75, 265);
        dimeButton.setText("10¢");
        frame.add(dimeButton);
        
        setDimeListener();
        
        //Quarter button
        quarterButton.setSize(40, 80);
        quarterButton.setLocation(125, 265);
        quarterButton.setText("25¢");
        frame.add(quarterButton);
        
        setQuarterListener();
        
        //Buy button
        Font buyFont = new Font(buyButton.getFont().getName(), Font.PLAIN, 32);
        buyButton.setSize(350, 80);
        buyButton.setLocation(25, 355);
        buyButton.setText("BUY");
        buyButton.setFont(buyFont);
        frame.add(buyButton);
        
        setBuyListener();
        
        //Cancel button
        cancelButton.setSize(180, 80);
        cancelButton.setLocation(195, 265);
        cancelButton.setText("CANCEL");
        frame.add(cancelButton);
        
        setCancelListener();
        
        //Set frame visible
        frame.setVisible(true);
        
        //Start date/time updater thread
        DateTime dateTime = new DateTime();
        Thread thread = new Thread(dateTime);
        thread.start();
        
        while (true) {
            date = new Date();
            dateLabel.setText(date.toString());
        }
        
    }
    
    public void setConfigListener() {
        
        configButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
    }
    
    public void setNickelListener() {
        
        nickelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    payStation.addPayment(5);
                }
                catch (Exception ex) {
                    
                }
                moneyLabel.setText(centsToDisplay(payStation.getInsertedSoFar()));
                timeLabel.setText(timeToDisplay(payStation.readDisplay()));
            }
            
        });
        
    }
    
    public void setDimeListener() {
        
        dimeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    payStation.addPayment(10);
                }
                catch (Exception ex) {
                    
                }
                moneyLabel.setText(centsToDisplay(payStation.getInsertedSoFar()));
                timeLabel.setText(timeToDisplay(payStation.readDisplay()));
            }
            
        });
        
    }
    
    public void setQuarterListener() {
        
        quarterButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    payStation.addPayment(25);
                }
                catch (Exception ex) {
                    
                }
                moneyLabel.setText(centsToDisplay(payStation.getInsertedSoFar()));
                timeLabel.setText(timeToDisplay(payStation.readDisplay()));
            }
            
        });
        
    }
    
    public void setBuyListener() {
        
        buyButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                printReceipt(payStation.buy());
                moneyLabel.setText("$00.00");
                timeLabel.setText("00:00");
                
            }
            
        });
        
    }
    
    public void setCancelListener() {
        
        cancelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Map<Integer, Integer> map = payStation.cancel();
                
                int nickels = 0;
                if (map.containsKey(1)) {
                    nickels = map.get(1);
                }
                int dimes = 0;
                if (map.containsKey(2)) {
                    dimes = map.get(2);
                }
                int quarters = 0;
                if (map.containsKey(3)) {
                    quarters = map.get(3);
                }
                
                String returnedCoins = "Nickels returned = " + nickels + "\n";
                returnedCoins += "Dimes returned = " + dimes + "\n";
                returnedCoins += "Quarters returned = " + quarters;
                
                moneyLabel.setText("$00.00");
                timeLabel.setText("00:00");
                JOptionPane.showMessageDialog(null, returnedCoins);
                
            }
            
        });
        
    }
    
    private String centsToDisplay(int cents) {
        String string = "$";
        int dollars = cents / 100;
        cents = cents % 100;
        if (dollars < 10) {
            string += "0";
        }
        string += dollars + ".";
        if (cents < 10) {
            string += "0";
        }
        string += cents;
        return string;
    }
    
    private String timeToDisplay(int minutes) {
        String string = "";
        int hours = minutes / 60;
        if (hours < 10) {
            string += "0";
        }
        string+= hours + ":";
        minutes = minutes % 60;
        if (minutes < 10) {
            string += "0";
        }
        string += minutes;
        return string;
    }
    
    private class DateTime implements Runnable {
        @Override
        public void run() {
            date = new Date();
            dateLabel.setText(date.toString());
        }
    }
    
    private void printReceipt(ReceiptImpl receipt) {
        
                File receiptText = new File("receipt.txt");
                String output = "-----Pay Station Receipt-----\n";
                output += date.toString() + "\n\n";
                output += "Amount spent: " + centsToDisplay(receipt.getMoneyInserted()) + "\n";
                output += "Time bought: " + timeToDisplay(receipt.value());
                
                try{
                    receiptText.createNewFile();
                    OutputStream stream = new FileOutputStream(receiptText);
                    stream.write(output.getBytes(), 0, output.length());
                    stream.close();
                }
                catch(Exception ex){
                    
                }
                
                try{
                    Desktop.getDesktop().open(receiptText);
                }
                catch(Exception ex){
                    
                }
        
    }
    
}

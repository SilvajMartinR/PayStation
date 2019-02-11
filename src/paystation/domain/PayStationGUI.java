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
import javax.swing.*;

/**
 *
 * @author michael
 */
public class PayStationGUI {
    
    private Date date;
    
    private final JButton configButton = new JButton();
    private final JButton nickelButton = new JButton();
    private final JButton dimeButton = new JButton();
    private final JButton quarterButton = new JButton();
    private final JButton buyButton = new JButton();
    private final JButton cancelButton = new JButton();
    
    public PayStationGUI(Date date) {
        
        this.date = date;
        
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
        JLabel dateLabel = new JLabel(date.toString());
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
        JLabel moneyLabel = new JLabel();
        Font moneyFont = new Font(moneyLabel.getFont().getName(), Font.BOLD, 52);
        moneyLabel.setText("$0.00");
        moneyLabel.setForeground(Color.BLACK);
        moneyLabel.setFont(moneyFont);
        moneyLabel.setMinimumSize(windowSize);
        moneyLabel.setMaximumSize(windowSize);
        moneyLabel.setPreferredSize(windowSize);
        moneyLabel.setHorizontalAlignment(JLabel.LEFT);
        window.add(moneyLabel);
        
        //Time Purchased
        JLabel timeLabel = new JLabel();
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
        
        //Set frame visible (last)
        frame.setVisible(true);
        
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
                
            }
            
        });
        
    }
    
    public void setDimeListener() {
        
        dimeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });
        
    }
    
    public void setQuarterListener() {
        
        quarterButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });
        
    }
    
    public void setBuyListener() {
        
        buyButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                /**
                File receipt = new File("receipt.txt");
                try{
                    receipt.createNewFile();
                    Desktop.getDesktop().open(receipt);
                }
                catch(Exception ex){
                    
                }
                **/
                
            }
            
        });
        
    }
    
    public void setCancelListener() {
        
        cancelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });
        
    }
    
}

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
import java.util.Calendar;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private final JLabel stratLabel = new JLabel();
    
    private final JFrame frame = new JFrame("Pay Station");
    private final JFrame menuFrame = new JFrame("Select Town");
    
    private final String data[] = {"Alpha Town", "Beta Town", "Gamma Town"};
    private final JList townList = new JList(data);
    
    public PayStationGUI(Date date) {
        
        this.date = date;
        payStation = new PayStationImpl();
        
        this.draw();
        
    }
    /**
     * Main function to draw the visual elements of the GUI
     */
    private void draw() {
        
        //Main Winow
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
        stratLabel.setText(Constants.STRATEGY_NAMES[payStation.getRateStrategy()]);
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
    
    /**
     * Sets the event listener for when the "Config" button is clicked
     */
    public void setConfigListener() {
        
        configButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectStrategyMenu();
            }
        });
        
    }
    
    /**
     * Sets the event listener for when the Nickel / "5¢" button is pressed
     */
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
    
    /**
     * Sets the event listener for when the Dime / "10¢" button is pressed
     */
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
    
    /**
     * Sets the event listener for when the Quarter / "25¢" button is pressed
     */
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
    
    /**
     * Sets the event listener for when the "Buy" button is pressed
     */
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
    
    /**
     * Sets the event listener for when the "Cancel" button is pressed
     */
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
                
                String returnedCoins = "Nickels returned = " + nickels + " : " + (nickels * 5) + " cents\n";
                returnedCoins += "Dimes returned = " + dimes + " : " + (dimes * 10) + " cents\n";
                returnedCoins += "Quarters returned = " + quarters + " : " + (quarters * 25) + " cents";
                
                moneyLabel.setText("$00.00");
                timeLabel.setText("00:00");
                JOptionPane.showMessageDialog(null, returnedCoins);
                
            }
            
        });
        
    }
    
    /**
     * Converts the input number of cents to the format displayed on the GUI
     */
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
    
    /**
     * Converts the input number of minutes to the format displayed on the GUI
     */
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
    
    /**
     * Thread to update the time/date in real time on the display
     */
    private class DateTime implements Runnable {
        @Override
        public void run() {
            date = new Date();
            dateLabel.setText(date.toString());
        }
    }
    
    /**
     * Creates a printable receipt from the receipt data and writes it to and
     * opens a text file
     */
    private void printReceipt(ReceiptImpl receipt) {
        
                File receiptText = new File("receipt.txt");
                String output = "*** Pay Station Receipt - " + Constants.STRATEGY_NAMES[payStation.getRateStrategy()]
                        + " ***\n\n";
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
    
    /**
     * Function to display the town/strategy configuration menu
     */
    private void selectStrategyMenu() {
        
        menuFrame.setSize(200, 150);
        menuFrame.setLayout(null);
        menuFrame.setLocationRelativeTo(null);
        
        townList.setVisibleRowCount(3);
        townList.setSelectedIndex(payStation.getRateStrategy() - 1);
        townList.setSize(150, 50);
        townList.setLocation(20, 10);
        townList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        townList.addListSelectionListener(new TownListSelectionListener());
        menuFrame.add(townList);
        
        JButton closeButton = new JButton();
        closeButton.setText("Close");
        closeButton.setSize(70, 40);
        closeButton.setLocation(60, 80);
        menuFrame.add(closeButton);
        
        closeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.setVisible(false);
                townList.setSelectedIndex(payStation.getRateStrategy() - 1);
            }
            
        });
        
        menuFrame.setVisible(true);
    }
    
    /**
     * Listener used by the JList object in the configuration window
     */
    private class TownListSelectionListener implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            
            int newStrat = townList.getSelectedIndex() + 1;
            if (payStation.setRateStrategy(newStrat)) {
                stratLabel.setText(Constants.STRATEGY_NAMES[newStrat]);
                timeLabel.setText(timeToDisplay(payStation.readDisplay()));
            }
            
        }
        
    }
    
}

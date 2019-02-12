/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Date;
import java.util.logging.Handler;

public class Main {
    
    public static void main(String[] args) {
        
        Date date = new Date();
        
        PayStationGUI gui = new PayStationGUI(date);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * Progressive Rate Strategy 
 *  
 * 
 * @author Silva_Surfer
 */
public class ProgressiveRateStrategy implements RateStrategy{
    
    @Override
    public int calculateTime(int insertedMoney){
        
        int x = insertedMoney;
        
        if(x < 150){
            
            return ((x * 2)/5);
            
        }else if(x < 350 && x >= 150){
        
            return (int)((x - 150)*(.3) + 60);
        
        }else{
            
            return ((x - 350)/5 + 120);
        }
     
    }
    
}

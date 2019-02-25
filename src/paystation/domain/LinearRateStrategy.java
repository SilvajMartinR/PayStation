/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * Linear Rate Strategy
 *  Time = (amount * 2)/5
 * 
 * @author Silva_Surfer
 */
public class LinearRateStrategy implements RateStrategy{
    
    @Override
    public int calculateTime(int moneyInserted){
        
        return (moneyInserted * 2)/5;
        
    }
    
    
    
}

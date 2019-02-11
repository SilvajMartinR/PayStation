/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Calendar;

/**
 *
 * @author christianlomboy
 */
public class AlternatingRateStrategy {

    public int calculateTime(int insertedMoney, Calendar day) {

        int x = insertedMoney;

        if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || day.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

            if (x < 150) {

                return ((x * 2) / 5);

            } else if (x < 350 && x >= 150) {

                return (int) ((x - 150) * (.3) + 60);

            } else {

                return ((x - 350) / 5 + 120);
            }

        } else {

            return (x * 2) / 5;

        }

    }
    
    public Calendar setDay(Calendar date) {
        return date;
    }

}

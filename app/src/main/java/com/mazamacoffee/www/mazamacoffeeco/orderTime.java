package com.mazamacoffee.www.mazamacoffeeco;

/**
 * Created by Steven on 11/28/2016.
 */

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;




public class orderTime {
    // Returning a string that has the date of a particular order in this format : dd/MM/yy HH:mm:ss



        //getting current date and time using Date class

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");


        public String getOrderDate()
        {


            String orderDate = df.format(new Date());
            return orderDate;

        }




}

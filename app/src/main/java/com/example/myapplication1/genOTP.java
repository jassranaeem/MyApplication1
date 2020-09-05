package com.example.myapplication1;

import java.util.*;
public class genOTP
{
    public static char[] generatorOTP()
    {
        //Creating object of Random class
        Random obj = new Random();
        char[] otp = new char[4];
        for (int i=0; i<4; i++)
        {
            otp[i]= (char)(obj.nextInt(10)+48);
        }

        return otp;
    }
}
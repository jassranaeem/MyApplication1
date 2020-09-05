package com.example.myapplication1;
import java.net.*;
import java.io.*;
import java.io.InputStreamReader;
public class sendSMS {
    public static void main(String mobile, String message) {

        String request = "https://sendpk.com/api/sms.php?username="+config.USERNAME+"&password="+config.PASSWORD+"&mobile="+mobile+"&sender="+config.sender+"&message"+message;

        try

        {

            URL url = new URL(request);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection)
            {

                connection = (HttpURLConnection) urlConnection;
            }

            else

            {

                System.out.println("Please enter an HTTP URL.");

                return;
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;

            while((current = in.readLine()) !=
                    null)

            {

                urlString += current;
            }
            System.out.println(urlString);
        }catch(IOException e)
        {

            e.printStackTrace();
        }
    }
}
package utility;

import pojos.Registrant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTxt {

    public static void generateData(Registrant registrant, String fileName){

        // utilities icinde, sonradan kullanilabilecek bir dueumsa try catch
        // file /class icinde ise tekrar kulanima gerek yoksa exception
        try {

            FileWriter fileWriter = new FileWriter(fileName, false); // it is like scanner class, creates the file --> false olursa  veya append eklenmezse eski adtanin uzerine yaziyor
            BufferedWriter writer = new BufferedWriter(fileWriter);        // creates, writes the records in the file

            writer.append(registrant.getFirstName()+
                    ","+ registrant.getLastName()+
                    ","+
                    registrant.getEmail()+
                    ","+
                    registrant.getSsn()+
                    ","+
                    registrant.getPassword()+
                    ","+
                    registrant.getLogin()+"\n");
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
//            System.out.println("test failed");

        }


    }


}
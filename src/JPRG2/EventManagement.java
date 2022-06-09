/*
 * Class : DIT/FT/1B/03
 * Admission Number : p2037084
 * Name : Tan Jing Wen
 */
package JPRG2;

import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;

/**
 *
 * @author jw
 */
public class EventManagement {

    String registerr = "", fee = "";
    double fees;

    // create IO class object 
    public EventManagement() throws IOException {
        this.file = new IO();
    }
    
    IO file ;

    // AUTO SAVE 
    public void Save() throws IOException {
        file.write(file.origarray);
    }

    // DISPLAY
    public String Display() {
        String result = "S\\N\tName\tOrganizer\tDate&Time\tFees\n\n";
        for (int i = 0; i < file.origarray.length; i++) {
            result += (i + 1) + "\t"
                    + file.origarray[i].getName() + "\t"
                    + file.origarray[i].getOrganizer() + "\t"
                    + file.origarray[i].getDatentime() + "\t"
                    + file.origarray[i].getFees() + "\n\n" ;
        }
        return result;
    }

    // ADD
    public void Add(String name, String organizer, String datentime, Double fees,String typeofevent) {
        // ADVANCED REQUIREMENTS : no duplicated event name can be added
        int repeat;

        // 1 =  duplicate // 2 = no duplicate
        repeat = isDuplicate(name, file.origarray);
        if (repeat == 1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error ! The event name entered is duplicated !"
                    + "\nPlease enter a new event name again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {

            // ADDING NEW EVENT
            Event[] newarray = new Event[file.origarray.length + 1];
            for (int i = 0; i < file.origarray.length; i++) {
                newarray[i] = file.origarray[i];
            }
            newarray[file.origarray.length] = new Event(name, organizer, datentime, fees,typeofevent);
            file.origarray = newarray;
            JOptionPane.showMessageDialog(null, "Event added successfully !", "Message", JOptionPane.DEFAULT_OPTION);
        }
    }

    // DELETE
    public void Delete(String deletename) {
        int deleteindex, check = 0;
        
        // for loop to check whether event exist 
        for (int i = 0; i < file.origarray.length; i++) {
            // copy all the other array except the one should be delete
            if (deletename.equals(file.origarray[i].getName())) {
                deleteindex = i;
                check = 1;
                Event[] newarray = new Event[file.origarray.length - 1];
                for (int h = 0, j = 0; h < file.origarray.length; h++) {
                    if (h != deleteindex) {
                        newarray[j++] = file.origarray[h];
                    }
                }
                JOptionPane.showMessageDialog(
                        null,
                        "Event deleted!",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                file.origarray = newarray;
                Display();
            }
        }
        // if such event does not exist
        if (check == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cannot find the event \" " + deletename + "\" to delete !",
                    "Event",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // SEARCH BY NAME
    public String Search1(String searchname) {
        String result = "S\\N\tName\tOrganizer\tDate&Time\tFees\n\n";
        String typeofevent = "" , messages = "" ;
        int check = 0 , in = 0, j = 1; 
        
        // for loop to check whether event exists by checking name
        for (int i = 0; i < file.origarray.length; i++) {
            if (searchname.contains(file.origarray[i].getName())) {
                check = 1;
                in = i ;
                result += (j++) + "\t"
                        + file.origarray[i].getName() + "\t"
                        + file.origarray[i].getOrganizer() + "\t"
                        + file.origarray[i].getDatentime() + "\t"
                        + file.origarray[i].getFees() + "\n";
                // get the type of event and store it in variable
                typeofevent = file.origarray[i].getTypeofevent() ;
            }
        }
        
        // check the type of event
        switch (typeofevent) {
            // if is online event , then create online class object , call the getmessage method 
            case "Online Event":
                online o = new online(file.origarray[in].getName(),file.origarray[in].getOrganizer(),file.origarray[in].getDatentime(),file.origarray[in].getFees(),file.origarray[in].getTypeofevent());
                // store the message into variable
                messages = o.getMessage() ;
                break;
            // if is face to face event , then create facetoface class object , call the getmessage method 
            case "Face-to-Face Event":
                facetoface f = new facetoface(file.origarray[in].getName(),file.origarray[in].getOrganizer(),file.origarray[in].getDatentime(),file.origarray[in].getFees(),file.origarray[in].getTypeofevent());
                // store the message into variable
                messages = f.getMessage() ;
                break;
        }
        
        // if there is no event found
        if (check == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cannot find the event \" " + searchname + "\" !! Please search again.",
                    "Event",
                    JOptionPane.ERROR_MESSAGE);
        }
        // return result + message to be display 
        return result + "\n" + messages;
    }

    // SEARCH BY FEES
    public String Search2(Double searchfees) {
        String result = "S\\N\tName\tOrganizer\tDate&Time\tFees\n\n";
        int check = 0, j = 1;
        
        // for loop to check events that fees are below a certain amount
        for (int i = 0; i < file.origarray.length; i++) {
            if (searchfees >= file.origarray[i].getFees()) {
                check = 1;
                result += (j++) + "\t"
                        + file.origarray[i].getName() + "\t"
                        + file.origarray[i].getOrganizer() + "\t"
                        + file.origarray[i].getDatentime() + "\t"
                        + file.origarray[i].getFees() + "\n\n";
            }
        }
        
        // if there is no such event exist that below the certain amount
        if (check == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cannot find the event below $" + searchfees + "!! Please search again.",
                    "Event",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        // return search result
        return result;
    }

    // REGISTER BY NAME
    public String Register(String register) {
        String result = "";
        int check = 0;
        
        // check whether the event exist and display register message
        for (int i = 0; i < file.origarray.length; i++) {
            if (register.equals(file.origarray[i].getName())) {
                check = 1;
                registerr += "*  " + file.origarray[i].getName() + "\n";
                fees += file.origarray[i].getFees();
                result = "You have registered for the following event(s) :" + "\n" + registerr + "\n\n" + "The cost is $" + fees;
            }
        }
        
        // if there is no such event exist 
        if (check == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cannot find the event \" " + register + "\" to register !!",
                    "Event",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        // return search result
        return result;
    }   
    
    // : no duplicate event name can be added
    public int isDuplicate(String name, Event[] array) {
        int check = 0;
        for (int i = 0; i < array.length; i++) {
            if (name.equals(array[i].getName())) {
                check = 1;
            }
        }
        if (check == 1) {
            return 1;
        } else {
            return 2;
        }
    }

}

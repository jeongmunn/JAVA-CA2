/*
 * Class : DIT/FT/1B/03
 * Admission Number : p2037084
 * Name : Tan Jing Wen
 */
package JPRG2;

import javax.swing.JOptionPane;
import java.util.Arrays;
import java.io.* ;

/**
 *
 * @author jw
 */

public class Event implements Serializable {
   public String name , organizer , datentime , result , typeofevent ;
   int count;
   double fees;
   
    // Event class constructor
    public Event(String name,String organizer,String datentime,double fees,String typeofevent){
        this.name = name ;
        this.organizer = organizer ;
        this.datentime = datentime ;
        this.fees = fees ;
        this.typeofevent = typeofevent ;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getOrganizer(){
        return this.organizer;
    }
    
    public String getDatentime(){
        return this.datentime;
    }

    public double getFees(){
        return this.fees;
    }
    
    public String getTypeofevent(){
        return this.typeofevent ;
    }
    
    public String getMessages(){
        String result = "";
        return result ;
    }
}


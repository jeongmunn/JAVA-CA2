/*
 * Class : DIT/FT/1B/03
 * Admission Number : p2037084
 * Name : Tan Jing Wen
 */
package JPRG2;

/**
 *
 * @author jw
 */
public class online extends Event{

    // online class constructor
    public online(String name,String organizer,String datentime,double fees,String typeofevent){
        super(name,organizer,datentime,fees,typeofevent);
    }
    
    // online message method
    public String getMessage(){
        String result = "This is an online event. There is no limit on the number of participants.";
        return result ;
    }
}

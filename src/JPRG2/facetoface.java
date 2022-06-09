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
public class facetoface extends Event {
    
    // facetoface class constructor
    public facetoface(String name, String organizer, String datentime,double fees, String typeofevent) {
        super(name, organizer, datentime, fees,typeofevent);
    }

    // facetoface message method
    public String getMessage(){
        String result = "This is a face to face event.\nThe number of participant is limited to 20.\nPlease wear your masks and keep social distance.";
        return result ;
    }
}

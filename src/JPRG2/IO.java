/*
 * Class : DIT/FT/1B/03
 * Admission Number : p2037084
 * Name : Tan Jing Wen
 */
package JPRG2;

import java.io.* ;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author jw
 */
public class IO{
    // declare Event array
    Event[] origarray;
    // declare string array to store data after split the data into string
    String[] result;
    int arraylength;
    
    // IO class constructor 
    public IO() throws IOException{
        read();
    }
    
     // method to read event.txt file 
    public void read() throws IOException {
        BufferedReader br;
        FileReader fr;

        try {
            // find event.txt
            fr = new FileReader("./src/JPRG2/event.txt");
            br = new BufferedReader(fr);

            // read the first line
            String n = br.readLine();
            // get array length , parse it into integer
            arraylength = Integer.parseInt(n);
            // declare arraylength for origarray
            origarray = new Event[arraylength];
            // for loop to loop and store string[] data into Event[] array 
            for (int i = 0; i < arraylength; i++) {
                n = br.readLine();
                result = n.split(";");
                // store data into array
                origarray[i] = new Event(result[0], result[1],result[2],Double.parseDouble(result[3]),result[4]);
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR ! File is not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
    
    // method to write , save and seriaalize file
    public void write(Event[] array) throws IOException {
        PrintWriter pw ;
        try{
        // write the event.dat
        pw = new PrintWriter(new FileWriter("./src/JPRG2/event.dat"));
        // store the array length in first line
        pw.println(origarray.length);
        // for loop to loop array data to store in file
        for(int i= 0 ; i < origarray.length ; i++){
            pw.println(origarray[i].getName() + ";" + origarray[i].getOrganizer() + ";" + origarray[i].getDatentime() + ";" + origarray[i].getFees() + ";" + origarray[i].getTypeofevent());
        }
        pw.close();
        // serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/JPRG2/event.dat"));
        out.writeObject(array);
        out.flush();
        } catch (NotSerializableException e){
            e.printStackTrace();              
        } catch (IOException e){
            e.printStackTrace();        
        }
        
    }
}

package HJ.hospitalityjobsjobseeker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Printdate {
	
    public static void main( String[] args ) {
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
	 
	 //get current date time with Date()
	 Date date = new Date();
	 
	 // Now format the date
	 String date1= dateFormat.format(date);
	 
	 // Print the Date
	 System.out.println(date1);
	}
}

package Collectdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask_runningtask   extends TimerTask  {

	 @Override
	  public void run() {
	try {
		String line;
        String command = "tasklist";
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        Integer counter = -2;
        while ((line = reader.readLine()) != null) {
            //System.out.print(line);
            counter += 1;
        }
            reader.close();
        BufferedWriter f_writer= new BufferedWriter(new FileWriter(
                "C:/Users/anika.babu/Desktop/New folder/Output/Running_Process.txt",true));
            f_writer.newLine();
            f_writer.write(String.valueOf(new Date().getTime()) +"|"+ Integer.toString(counter));
            f_writer.close();

    } catch (Exception e) {
        System.out.println("HEY Buddy ! U r Doing Something Wrong ");
        e.printStackTrace();
    }
}
}

package Collectdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunningProcess_Metrics implements Runnable {
    int ThreadNum;
    RunningProcess_Metrics (int i) { this.ThreadNum = i; }
    @Override
    public void run() {
        try {
            String command = "tasklist";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            Integer counter = -2;
            while ((line = reader.readLine()) != null) {
                //System.out.print(line);
                counter += 1;
            }
            System.out.println(Thread.currentThread().getName()+ " :: " + ThreadNum + ">> Running Process :: " + counter);

            reader.close();

        } catch (Exception e) {
            System.out.println("HEY Buddy ! U r Doing Something Wrong ");
            e.printStackTrace();
        }
    }

}
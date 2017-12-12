package my;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

import javax.swing.JOptionPane;

public class backup {

	public static void Backupdbtosql() {
	    try {

	        /*NOTE: Getting path to the Jar file being executed*/
	        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
	        CodeSource codeSource = backup.class.getProtectionDomain().getCodeSource();
	        File jarFile = new File(codeSource.getLocation().toURI().getPath());
	        String jarDir = jarFile.getParentFile().getPath();

	        String dbName = "mytally";
	        String dbUser = "root";
	        String dbPass = "root";

	        /*NOTE: Creating Path Constraints for folder saving*/
	        String folderPath = jarDir + "\\urvil";
	        
	        /*NOTE: Creating Folder if it does not exist*/
	        File f1 = new File(folderPath);
	        f1.mkdir();

	        /*NOTE: Creating Path Constraints for backup saving*/
	        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
	         String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

	        /*NOTE: Used to create a cmd command*/
	        String executeCmd = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.0\\bin\\mysqldump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

	        /*NOTE: Executing the command here*/
	        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	        int processComplete = runtimeProcess.waitFor();

	        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
	        if (processComplete == 0) {
	            System.out.println("Backup Complete");
	        } else {
	            System.out.println("Backup Failure");
	        }

	    } catch (URISyntaxException | IOException | InterruptedException ex) {
	        JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
	    }
	}
	
	public static void b2(){
	
		String dbName = "mytally";
		String dbUser = "root";
		String dbPass = "root";
		String executeCmd = "";
	
		executeCmd = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.0\\bin\\mysqldump -u "+dbUser+" -p"+dbPass+" "+dbName+" -r backup.sql";
		Process runtimeProcess;
		try {
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		int processComplete = runtimeProcess.waitFor();
		if(processComplete == 0){
			System.out.println("Backup taken successfully");
		} else {
			System.out.println("Could not take mysql backup");
		}
		}
		catch(Exception e){
			System.out.println("Ex"+e);
		}
	}
}

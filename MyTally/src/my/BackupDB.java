package my;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupDB {
	public static void backup() {
		String[] command ={"cmd",};
        
        String sqldumpPath = "C:/Program Files (x86)/MySQL/MySQL Server 5.0/bin";
	    String backupPath = "E:/backup/";
        
        String date = new SimpleDateFormat("dd-MM-yyyy__HH-mm-ss").format(new Date());
        backupPath += "backup__" + date + ".sql";
        
        Process p;
		try {
            
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            
            PrintWriter out = new PrintWriter(p.getOutputStream());
            out.println("C:");
            out.println("cd "+sqldumpPath);
            out.println("mysqldump -uroot -proot mytally > "+backupPath);
            out.println("exit");
	       
            out.close();
            p.waitFor(); 
		} 
        catch (Exception e) {}
	}
}

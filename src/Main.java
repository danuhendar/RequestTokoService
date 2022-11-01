
public class Main {
	public static void main(String args[]) {
		try {
			//ThreadMain t1 = new ThreadMain(1);
			//t1.start();
			Global_function gf = new Global_function(false);
			IDMRequestToko t1 = new IDMRequestToko();
			t1.Run();
			
			String tanggal_jam = gf.get_tanggal_curdate_curtime();
			gf.WriteFile("timemessage.txt", "", tanggal_jam, false);
			
			
			CheckThread t2 = new CheckThread();
			t2.start();
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}

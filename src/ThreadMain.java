public class ThreadMain extends Thread {
	IDMRequestToko idm;
     
    public ThreadMain(int num){
    	idm = new IDMRequestToko();
    }
    
    public void run(){
        for(int l = 0;l<1;l++){
           try{
        	   idm.Run();
           }catch(Exception exc){
               
           }
           
        }
    } 
}

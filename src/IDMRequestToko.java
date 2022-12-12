import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IDMRequestToko {
    MqttClient client_transreport_login;
    MqttClient client_transreport;
    Global_function gf = new Global_function(true);
    Interface_ga inter_login;
    Connection con;
    SQLConnection sqlcon = new SQLConnection();
    int counter = 1;
    
	public IDMRequestToko() {
		 
	}
	
	
	public String get_tanggal_curdate(){
        String res = "";
        try {
              int year = Calendar.getInstance().get(Calendar.YEAR);
              int month = Calendar.getInstance().get(Calendar.MONTH)+1;
              
              String bulan = "";
              if(month<10){
                  bulan = "0"+month;
              }else{
                  bulan = ""+month;
              }
              int d = Calendar.getInstance().get(Calendar.DATE);
              String tanggal = "";
              if(d<10){
                  tanggal = "0"+d;
              }else{
                  tanggal = ""+d;
              }
              String concat = year+""+bulan+""+tanggal;
              res = concat;                      
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
    public String get_tanggal_curdate_curtime(){
        String res = "";
        try {
              int year = Calendar.getInstance().get(Calendar.YEAR);
              int month = Calendar.getInstance().get(Calendar.MONTH)+1;
              String bulan = "";
              if(month<10){
                  bulan = "0"+month;
              }else{
                  bulan = ""+month;
              }
              int d = Calendar.getInstance().get(Calendar.DATE);
              String tanggal = "";
              if(d<10){
                  tanggal = "0"+d;
              }else{
                  tanggal = ""+d;
              }
              int h = Calendar.getInstance().get(Calendar.HOUR);
              String jam = "";
              if(h<10){
                  jam = "0"+h;
              }else{
                  jam = ""+h;
              }
              int min = Calendar.getInstance().get(Calendar.MINUTE);
              String menit = "";
              if(min<10){
                  menit = "0"+min;
              }else{
                  menit = ""+min;
              }
              int sec = Calendar.getInstance().get(Calendar.SECOND);
              String detik = "";
              if(sec<10){
                  detik = "0"+sec;
              }else{
                  detik = ""+sec;
              }
              String concat = year+""+bulan+""+tanggal+""+jam+""+menit+""+detik;
              res = concat;                      
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
    
    public String get_tanggal_curdate_curtime_format(){
        String res = "";
        try {
             LocalDateTime myDateObj = LocalDateTime.now();
             //System.out.println("Before formatting: " + myDateObj);
             
             DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

             String formattedDate = myDateObj.format(myFormatObj);
             System.out.println("After formatting: " + formattedDate);
             res = formattedDate;                      
        } catch (Exception e) {
              res = "";  
        }
        
        return res;
    }
	
	String Parser_TASK,
    Parser_ID,
    Parser_SOURCE,
    Parser_COMMAND,
    Parser_OTP,
    Parser_TANGGAL_JAM,
    Parser_VERSI,
    Parser_HASIL,
    Parser_FROM,
    Parser_TO,
    Parser_SN_HDD,
    Parser_IP_ADDRESS,
    Parser_STATION,
    Parser_CABANG,
    Parser_NAMA_FILE,
    Parser_CHAT_MESSAGE,
    Parser_REMOTE_PATH,
    Parser_LOCAL_PATH,
    Parser_SUB_ID; 
    
    public void UnpackJSON(String json_message){
        
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(json_message);
        } catch (org.json.simple.parser.ParseException ex) {
            System.out.println("message json : "+json_message);
            System.out.println("message error : "+ex.getMessage());
            //ex.printStackTrace();
            //Logger.getLogger(IDMReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Parser_TASK = obj.get("TASK").toString();
        } catch (Exception ex) {
             Parser_TASK = "";
        }       
        try{
            Parser_ID = obj.get("ID").toString();
        }catch(Exception exc){
            Parser_ID = "";
        }
        try{
            Parser_SOURCE = obj.get("SOURCE").toString();
        }catch(Exception exc){
            Parser_SOURCE = "";
        }
        try{
            Parser_COMMAND = obj.get("COMMAND").toString();
        }catch(Exception exc){
            Parser_COMMAND = "";
        }
          try{
           Parser_OTP = obj.get("OTP").toString();
        }catch(Exception exc){
            Parser_OTP = "";
        }
        
        
        try{
           Parser_TANGGAL_JAM = obj.get("TANGGAL_JAM").toString();
        }catch(Exception exc){
            Parser_TANGGAL_JAM = "";
        }
        try{
            Parser_VERSI = obj.get("RESULT").toString().split("_")[7];
        }catch(Exception exc){
            try{
                Parser_VERSI = obj.get("VERSI").toString();
            }catch(Exception exc1){ Parser_VERSI = "";}
            
        }

        try{
            Parser_HASIL = obj.get("HASIL").toString();
            Parser_FROM = obj.get("FROM").toString();
            Parser_TO = obj.get("TO").toString();

        }catch(Exception exc){
            Parser_HASIL = "";
            Parser_FROM = "";
            Parser_TO = "";
        }
       
        try{
            Parser_SN_HDD = obj.get("SN_HDD").toString();
        }catch(Exception exc){
            try{
                Parser_SN_HDD = obj.get("SN_HDD").toString();
            }catch(Exception exc1){Parser_SN_HDD = "";}
            
        }
        try{
            Parser_IP_ADDRESS = obj.get("IP_ADDRESS").toString();
        }catch(Exception exc){
            try{
                Parser_IP_ADDRESS = obj.get("IP_ADDRESS").toString();    
            }catch(Exception exc1){
                Parser_IP_ADDRESS = "";
            }

        }
        
        try{
            Parser_STATION = obj.get("STATION").toString();
        }catch(Exception exc){
            try{
                Parser_STATION = obj.get("STATION").toString();
            }catch(Exception exc1){Parser_STATION = "";}
            
        }
        
        try{
           Parser_CABANG = obj.get("CABANG").toString();
        }catch(Exception exc){
            try{
                Parser_CABANG = obj.get("CABANG").toString();
            }catch(Exception exc1){Parser_CABANG = "";}
        }
        
        try{
            Parser_NAMA_FILE = obj.get("NAMA_FILE").toString();
        }catch(Exception exc){
            Parser_NAMA_FILE = "";
        }
        try{
            Parser_CHAT_MESSAGE = obj.get("CHAT_MESSAGE").toString();
        }catch(Exception exc){
            Parser_CHAT_MESSAGE = "";
        }
        try{
            Parser_REMOTE_PATH = obj.get("REMOTE_PATH").toString();
        }catch(Exception exc){
            Parser_REMOTE_PATH = "";
        }
        try{
            Parser_LOCAL_PATH = obj.get("LOCAL_PATH").toString();
        }catch(Exception exc){
            Parser_LOCAL_PATH = "";
        }
        try{
            Parser_SUB_ID = obj.get("SUB_ID").toString();
        }catch(Exception exc){
            Parser_SUB_ID = "";
        }
        
    }
    
    public void RunRequestListToko(String get_topic_sub,int qos_message_command,String get_topic_pub) {
    	//---------------------------- COMMAND -----------------------//
        try {

			System.out.println("SUBS : "+get_topic_sub);
			
			  
	        client_transreport.subscribe(get_topic_sub,qos_message_command, new IMqttMessageListener() {
	            public void messageArrived (final String topic, final MqttMessage message) throws Exception {
	                        //----------------------------- FILTER TOPIC NOT CONTAINS -------------------------------//
	                        Date HariSekarang_run = new Date();
	                        String payload = new String(message.getPayload());
	                        String msg_type = "";
	                        String message_ADT_Decompress = "";
	                        try{
	                            message_ADT_Decompress = gf.ADTDecompress(message.getPayload());
	                            msg_type = "json";
	                        }catch(Exception exc){
	                            message_ADT_Decompress = payload;
	                            msg_type = "non json";
	                        }
	                        counter++;
	                        //======================== BONGKAR JSON ==========================//
	                        UnpackJSON(message_ADT_Decompress);
	                        //======================== INSERT KE TABLE TRANSREPORT ==========================//
	                        try{
	                           gf.InsTransReport(Parser_TASK,Parser_ID,Parser_SOURCE,Parser_COMMAND,Parser_OTP,Parser_TANGGAL_JAM,Parser_VERSI,Parser_HASIL,Parser_TO,Parser_FROM,Parser_SN_HDD,Parser_IP_ADDRESS,Parser_STATION,Parser_CABANG,Parser_NAMA_FILE,Parser_CHAT_MESSAGE,Parser_REMOTE_PATH,Parser_LOCAL_PATH,Parser_SUB_ID,false,"INSERT","transreport");
	                        }catch(Exception exc){
	                            //System.exit(0);
	                        }
	                        String tanggal_jam = gf.get_tanggal_curdate_curtime();
                        	gf.WriteFile("timemessage.txt", "", tanggal_jam, false);
	                        //=========================================================//
	                        gf.PrintMessage2("RECV > "+Parser_TASK,counter,msg_type,topic,Parser_TASK,Parser_FROM,Parser_TO,null,HariSekarang_run);
	                        String get = "";                         
	                           try {
	                            	String sql_jabatan = "SELECT JABATAN FROM idm_org_structure WHERE NIK = '"+Parser_FROM.split("_")[1]+"';";
	                            	//System.out.println("sql_jabatan : "+sql_jabatan);
	                            	String get_jabatan = gf.GetTransReport(sql_jabatan, 1, true);
	                            	//System.out.println("get_jabatan : "+get_jabatan);
	                            	String get_all_branch_code = gf.GetTransReport("SELECT GROUP_CONCAT(BRANCH_CODE) AS BRANCH_CODE FROM idm_org_branch", 1, true);
	                            	String query_list_toko = "";
	                            	if(get_jabatan.equals("REGIONAL_MANAGER") || get_jabatan.equals("MANAGER_EDPHO")) {
	                            		/*	
	                            		if(get_non_station.equals("")) {
	                            			query_list_toko = "SELECT a.KDCAB AS CABANG,\n" +
	                                                "	a.TOKO,\n" +
	                                                "	a.NAMA,\n" +
	                                                "	a.STATION,\n" +
	                                                "	REPLACE(REPLACE(a.IP, '\r', ''), '\n', '') AS IP,\n" +
	                                                "	a.KONEKSI,\n" +
	                                                "	IFNULL(CONCAT('READY ON : ',DATE_FORMAT(b.ADDTIME,'%d-%m-%Y %T')),'NEED INITIAL REQUEST') AS LAST_INITIAL_REPORT,\n" +
	                                                "	a.IS_INDUK\n" +
	                                                "	FROM tokomain a LEFT JOIN (SELECT a.KDTK,a.STATION,a.`ADDTIME` FROM initreport a WHERE a.KDCAB IN('"+get_all_branch_code.replaceAll(",", "','")+"')) b ON b.KDTK=REPLACE(REPLACE(a.TOKO, '', ''), '', '') AND b.STATION=a.STATION\n"+
	                                                "	WHERE a.KDCAB IN('"+get_all_branch_code.replaceAll(",", "','")+"') \n"+
	                                                "	ORDER BY a.KDCAB,a.TOKO,a.STATION ASC;";
	                            		}else {
	                            			query_list_toko = "SELECT a.KDCAB AS CABANG,\n" +
	                                                "	a.TOKO,\n" +
	                                                "	a.NAMA,\n" +
	                                                "	a.STATION,\n" +
	                                                "	REPLACE(REPLACE(a.IP, '\r', ''), '\n', '') AS IP,\n" +
	                                                "	a.KONEKSI,\n" +
	                                                "	IFNULL(CONCAT('READY ON : ',DATE_FORMAT(b.ADDTIME,'%d-%m-%Y %T')),'NEED INITIAL REQUEST') AS LAST_INITIAL_REPORT,\n" +
	                                                "	a.IS_INDUK\n" +
	                                                "	FROM tokomain a LEFT JOIN (SELECT a.KDTK,a.STATION,a.`ADDTIME` FROM initreport a WHERE a.KDCAB IN('"+get_all_branch_code.replaceAll(",", "','")+"')) b ON b.KDTK=REPLACE(REPLACE(a.TOKO, '', ''), '', '') AND b.STATION=a.STATION\n"+
	                                                "	WHERE a.KDCAB IN('"+get_all_branch_code.replaceAll(",", "','")+"') AND a.STATION NOT IN("+get_non_station+") \n"+
	                                                "	ORDER BY a.KDCAB,a.TOKO,a.STATION ASC;";
	                            		}
	                            		*/
	                            		
	                            		query_list_toko = "SELECT a.KDCAB AS CABANG,\n" +
	                                            "	a.TOKO,\n" +
	                                            "	a.NAMA,\n" +
	                                            "	a.STATION,\n" +
	                                            "	REPLACE(REPLACE(a.IP, '\r', ''), '\n', '') AS IP,\n" +
	                                            "	a.KONEKSI,\n" +
	                                            "	IFNULL(CONCAT('READY ON : ',DATE_FORMAT(b.ADDTIME,'%d-%m-%Y %T')),'NEED INITIAL REQUEST') AS LAST_INITIAL_REPORT,\n" +
	                                            "	a.IS_INDUK\n" +
	                                            "	FROM tokomain a LEFT JOIN (SELECT a.KDTK,a.STATION,a.`ADDTIME` FROM initreport a WHERE a.KDCAB IN('"+get_all_branch_code.replaceAll(",", "','")+"')) b ON b.KDTK=REPLACE(REPLACE(a.TOKO, '', ''), '', '') AND b.STATION=a.STATION\n"+
	                                            "	WHERE a.KDCAB IN('"+get_all_branch_code.replaceAll(",", "','")+"') \n"+
	                                            "	ORDER BY a.KDCAB,a.TOKO,a.STATION ASC;";
	                            		
	                            	}else {
	                            		/* 
	                            		if(get_non_station.equals("")) {
	                            			query_list_toko = "SELECT a.KDCAB AS CABANG,\n" +
	                                                "	a.TOKO,\n" +
	                                                "	a.NAMA,\n" +
	                                                "	a.STATION,\n" +
	                                                "	REPLACE(REPLACE(a.IP, '\r', ''), '\n', '') AS IP,\n" +
	                                                "	a.KONEKSI,\n" +
	                                                "	IFNULL(CONCAT('READY ON : ',DATE_FORMAT(b.ADDTIME,'%d-%m-%Y %T')),'NEED INITIAL REQUEST') AS LAST_INITIAL_REPORT,\n" +
	                                                "	a.IS_INDUK\n" +
	                                                "	FROM tokomain a LEFT JOIN (SELECT a.KDTK,a.STATION,a.`ADDTIME` FROM initreport a "+Parser_COMMAND+") b ON b.KDTK=REPLACE(REPLACE(a.TOKO, '', ''), '', '') AND b.STATION=a.STATION\n"+
	                                                "	\n" +Parser_COMMAND+ "\n"+
	                                                "	ORDER BY a.KDCAB,a.TOKO,a.STATION ASC;";
	                            		}else {
	                            			query_list_toko = "SELECT a.KDCAB AS CABANG,\n" +
	                                                "	a.TOKO,\n" +
	                                                "	a.NAMA,\n" +
	                                                "	a.STATION,\n" +
	                                                "	REPLACE(REPLACE(a.IP, '\r', ''), '\n', '') AS IP,\n" +
	                                                "	a.KONEKSI,\n" +
	                                                "	IFNULL(CONCAT('READY ON : ',DATE_FORMAT(b.ADDTIME,'%d-%m-%Y %T')),'NEED INITIAL REQUEST') AS LAST_INITIAL_REPORT,\n" +
	                                                "	a.IS_INDUK\n" +
	                                                "	FROM tokomain a LEFT JOIN (SELECT a.KDTK,a.STATION,a.`ADDTIME` FROM initreport a "+Parser_COMMAND+") b ON b.KDTK=REPLACE(REPLACE(a.TOKO, '', ''), '', '') AND b.STATION=a.STATION\n"+
	                                                "	\n" +Parser_COMMAND+ " AND a.STATION NOT IN("+get_non_station+")\n"+
	                                                "	ORDER BY a.KDCAB,a.TOKO,a.STATION ASC;";
	                            		}
	                            		*/ 
	                            		
	                            		
	                            		query_list_toko = "SELECT a.KDCAB AS CABANG,\n" +
	                                            "	a.TOKO,\n" +
	                                            "	a.NAMA,\n" +
	                                            "	a.STATION,\n" +
	                                            "	REPLACE(REPLACE(a.IP, '\r', ''), '\n', '') AS IP,\n" +
	                                            "	a.KONEKSI,\n" +
	                                            "	IFNULL(CONCAT('READY ON : ',DATE_FORMAT(b.ADDTIME,'%d-%m-%Y %T')),'NEED INITIAL REQUEST') AS LAST_INITIAL_REPORT,\n" +
	                                            "	a.IS_INDUK\n" +
	                                            "	FROM tokomain a LEFT JOIN (SELECT a.KDCAB,a.KDTK,a.STATION,a.`ADDTIME` FROM initreport a "+Parser_COMMAND+") b ON b.KDTK=REPLACE(REPLACE(a.TOKO, '', ''), '', '') AND b.STATION=a.STATION AND b.KDCAB=a.KDCAB \n"+
	                                            "	\n" +Parser_COMMAND+ "\n"+
	                                            "	ORDER BY a.KDCAB,a.TOKO,a.STATION ASC;";
	                            		
	                            		query_list_toko = query_list_toko.replace("where kdcab", "where a.KDCAB");
	                            	
	                            	}
	                                
	                                //System.err.println("kondisi 1 :"+query_list_toko+"\n");
	                                //System.out.println("==========================================\n");
	                                get = gf.GetTransReport(query_list_toko, 8, false);//inter_login.call_get_procedure(query_list_toko.replace("where kdcab", "where a.KDCAB"), 7 , false);
	                                
	                            }catch(Exception exc) {
	                            	String query_list_toko = "SELECT a.KDCAB AS CABANG,\n" +
	                                        "	a.TOKO,\n" +
	                                        "	a.NAMA,\n" +
	                                        "	a.STATION,\n" +
	                                        "	REPLACE(REPLACE(a.IP, '\r', ''), '\n', '') AS IP,\n" +
	                                        "	a.KONEKSI,\n" +
	                                        "	IFNULL(CONCAT('READY ON : ',DATE_FORMAT(b.ADDTIME,'%d-%m-%Y %T')),'NEED INITIAL REQUEST') AS LAST_INITIAL_REPORT,\n" +
	                                        "	a.IS_INDUK\n" +
	                                        "	FROM tokomain a LEFT JOIN (SELECT a.KDTK,a.STATION,a.`ADDTIME` FROM initreport a "+Parser_COMMAND+") b ON b.KDTK=REPLACE(REPLACE(a.TOKO, '', ''), '', '') AND b.STATION=a.STATION\n"+
	                                        "	\n" +Parser_COMMAND+ " \n"+
	                                        "	ORDER BY a.KDCAB,a.TOKO,a.STATION ASC;";
	          
	                            	  //System.out.println("kondisi 2 :"+query_list_toko);
	                            	  //gf.WriteFile("query_login.txt", "",query_list_toko.replace("where kdcab", "where a.KDCAB"), false);
					                  get = gf.GetTransReport(query_list_toko.replace("where kdcab", "where a.KDCAB"), 7, false);//inter_login.call_get_procedure(query_list_toko.replace("where kdcab", "where a.KDCAB"), 7 , false);
	                            }
	                            Parser_HASIL = get;
	                            //System.out.println("HASIL : "+Parser_HASIL);
	                            //System.out.println("============================================");
	                            String nik = "";
                                try {
                                	String res_nik[] = Parser_FROM.split("_");
                                	nik = res_nik[1];
                                }catch(Exception exc) {
                                	nik = Parser_FROM;
                                }
	                            String res_topic = get_topic_pub.replace("FROM", nik);
	                            System.out.println("TOPIC DEST : "+res_topic);
	                            Parser_TASK = "RESINITALLTOKO";
	                            Parser_SOURCE = "IDMreporter";
	                            String res_message = gf.CreateMessage(Parser_TASK,Parser_ID,Parser_SOURCE,Parser_COMMAND,Parser_OTP,Parser_TANGGAL_JAM,Parser_VERSI,Parser_HASIL,Parser_FROM,Parser_TO,Parser_SN_HDD,Parser_IP_ADDRESS,Parser_STATION,Parser_CABANG,"",Parser_NAMA_FILE,Parser_CHAT_MESSAGE,Parser_REMOTE_PATH,Parser_LOCAL_PATH,Parser_SUB_ID);
	                            //System.err.println("res_message : "+res_message);
	                            //gf.WriteLog("MESSAGE SENT\t:\t"+res_message, true);
	                            byte[] convert_message = res_message.getBytes("US-ASCII");
	                            byte[] bytemessage = gf.compress(convert_message);
	                            gf.PublishMessageAndDocumenter(res_topic, bytemessage, counter, res_message,0);
	                            
	                        
	            }
	             
	        });
        }catch(Exception exc) {}
    }
    
     
	public void Run() {
		  System.out.println("=================================          START         ==================================");   
	      try {
			  client_transreport =  gf.get_ConnectionMQtt();
			  //String get_non_station = gf.get_non_station_tokomain();
			  
			  String get_topic_sub[] = gf.en.getTopicSub().split(",");
			  String get_topic_pub = gf.en.getTopicPub();     
			  
			  for(int i = 0;i<get_topic_sub.length;i++) {
				  String res_topic_sub = get_topic_sub[i];
				  //System.out.println(res_topic_sub);
				  RunRequestListToko(res_topic_sub, 0, get_topic_pub);
			  }
			  
			  
			  System.out.println("PUBS : "+get_topic_pub);
			  
	      }catch(Exception exc){
	         exc.printStackTrace();
	      }  
	    }
	
	
	 
}

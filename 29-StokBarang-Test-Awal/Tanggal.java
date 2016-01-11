import java.util.Date;  
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
  
public class Tanggal{  
  
    private String getTanggal() {  
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        Date date = new Date();  
        return dateFormat.format(date);  
    }  
      
    public static void main(String Args[]){  
        Tanggal tgl = new Tanggal();  
        System.out.println(""+tgl.getTanggal());  
    }  
}  
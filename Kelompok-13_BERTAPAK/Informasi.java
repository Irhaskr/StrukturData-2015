import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Informasi {

    private String idClient;
    private String koordinat;
    ArrayList<Object[]> posisi = new ArrayList<Object[]>();
    
    public void setIDuser(String idClient) {
        this.idClient = idClient;
        
    }
    
    public void setKoordinat(String koordinat){
        Object[] data = new Object[2];
        data[0] = koordinat;
        data[1] = Calendar.getInstance().getTime();
        posisi.add(data);
    }
    
    public String getID(){
        return idClient;
    }
    
    public String getKoordinat(){
        String nilai ="";
        for(int n=0; n<posisi.size(); n++){
            nilai +=(posisi.get(n))[0]+" ";
        }
        return nilai;
        
    }
    
    public String getInformasi(String pertama, String terakhir){
        try{
            String koordinat = "";
            int jumlahlangkah = 0;
            
            String[] waktuAwal = pertama.split(":");
            String[] waktuAkhir = terakhir.split(":");
            Calendar result;
            Date utama, akhir, current;
            
            result = Calendar.getInstance();
            result.set(Calendar.HOUR_OF_DAY, Integer.parseInt(waktuAwal[0]));
            result.set(Calendar.MINUTE, Integer.parseInt(waktuAwal[1]));
            result.set(Calendar.SECOND, Integer.parseInt(waktuAwal[2]));
            utama = result.getTime();
            
            result = Calendar.getInstance();
            result.set(Calendar.HOUR_OF_DAY, Integer.parseInt(waktuAkhir[0]));
            result.set(Calendar.MINUTE, Integer.parseInt(waktuAkhir[1]));
            result.set(Calendar.SECOND, Integer.parseInt(waktuAkhir[2]));
            akhir= result.getTime();
            
            int i;
            for(i=0; i<posisi.size(); i++){
                current = (Date)((posisi.get(i))[1]);
                if(current.after(utama)&&current.before(akhir)){
                    koordinat = koordinat + " "+(String)((posisi.get(i))[0])+",";
                    jumlahlangkah++;
                }else if(current.after(akhir)){
                    break;
                }
            }
            if(koordinat.length()==0&&jumlahlangkah==0){
                return "Data tidak ditemukan...";
            }
            return "JUMLAH = "+jumlahlangkah+" KOORDINAT = "+koordinat;
        }catch(Exception e){
            return null;
        }
    }
    
    }
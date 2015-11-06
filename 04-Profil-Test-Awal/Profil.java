
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Profil
{
   private String nama = "M. Irhas";
   private String nim  = "1408107010031";
   
   public Profil(){
    }
    
    public Profil(String nama, String nim){
       this.nama=nama;
       this.nim=nim;
    }
    public String getNama(){
        return nama;
    }
   public String getNim(){
       return nim;
    }
} 
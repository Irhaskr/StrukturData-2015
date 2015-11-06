import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

public class KopiBerkas {
    
    public void kopi(String sumber, String sasaran) throws IOException {
        // Deklarasi variabel untuk stream
        FileInputStream masukan  = null;
        FileOutputStream keluaran=null;

        try {
            // Object stream untuk masukkan
            masukan = new FileInputStream(sumber);
            keluaran = new FileOutputStream(sasaran);
            int karakter = masukan.read();
            while (karakter != -1){
                keluaran.write(karakter);
                karakter = masukan.read();
            }
            // Selama masih ada data yang perlu dikeluarkan
           
            // Flush semua
            keluaran.flush();
        }
         catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }
          
        
        
        finally {
            if (masukan != null)
                masukan.close();
            // Tutup stream keluaran
            if (keluaran != null)
                keluaran.close();
        }
    }
    
   
}
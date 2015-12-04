import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;



public class Client {    
   
    
    public static void main() 
                throws UnknownHostException, IOException {
                    String nama= "M.Irhas(1408107010031)";
        // 0. Buka socket
        Socket koneksi = new Socket("192.168.43.139", 33333);
         OutputStream keluaran = koneksi.getOutputStream();
        // Note: Karena protokol-nya berbasis teks pakai writer aja.
        Writer keluaranWriter = new OutputStreamWriter(keluaran); 
        // 3. Selagi ada data kirim
        keluaranWriter.write(nama);
        // Syarat protokol-nya, semua perintah diakhiri dengan: CR & LF
        keluaranWriter.write("\r\n"); 
        keluaranWriter.flush(); // Paksa kirim data yang belum ter

        // Kirim perintah untuk informasi namaDomain
        //kirimPerintah(nama);
        
        // Baca balasannya
        //bacaBalasan();

        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }
    
    
}

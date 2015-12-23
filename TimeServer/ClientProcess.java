import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.Calendar;

public class ClientProcess implements Runnable {
    public ClientProcess(Socket koneksi) {
        this.koneksi = koneksi;
    }

    public void run() {        
        if (koneksi != null) {
            // Ambil IP dari koneksi
            ipStr = koneksi.getInetAddress().getHostAddress();
            
            try {
                // Ambil InputStream
                masukan = koneksi.getInputStream();
                masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
                // Ambil OutputStream
                keluaran = koneksi.getOutputStream();
                keluaranWriter = new BufferedWriter(new OutputStreamWriter(keluaran)); 

                // Selama masih terhubung dengan client tangani
                while (koneksi != null) {
                    tangani();
                }            
            }
            catch(IOException salah) { 
                System.out.println(salah);
            }
            finally {
                try { 
                    // Tampilkan pesan
                    System.out.println("Tutup: " + ipStr);
                    System.out.println();
                    
                    // Tutup koneksi
                    koneksi.close();
                }
                catch(IOException salah) {
                    System.out.println(salah);
                }                
            }
        }
    }
    
    private void tangani() throws IOException {
        // Baca perintah dari socket
        String perintah = masukanReader.readLine();
        // Jika tidak ada perintah keluar saja
        if (perintah == null)
            return;            
        // Ada perintah, hilangkan spasi depan & belakang serta ubah ke huruf besar semua
        perintah = perintah.trim().toUpperCase();
        
        //untuk mengetahui waktu saat ini
        Calendar kalender = Calendar.getInstance();
        String waktuStr = kalender.getTime().toString();
        
        // Tangani perintahnya
        if (perintah.compareTo("SIAPA") == 0) {
            keluaranWriter.write(ipStr);
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        else if (perintah.compareTo("WAKTU") == 0) {
            keluaranWriter.write(waktuStr);
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
 
        else {
            // Kirim pesan jika perintah tidak dikenal
            String pesan = "Perintah tidak dikenal";
            keluaranWriter.write(pesan);
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        
        // Tampilkan perintahnya
        System.out.println("Dari: " + ipStr);
        System.out.println("perintah: " + perintah);
        System.out.println();
    }

    // Koneksi ke Client
    private Socket koneksi; 
    // IP address dari client
    private String ipStr; 
    
    // InputStream untuk baca perintah
    private InputStream masukan = null;
    // Reader untuk InputStream, pakai yang buffer
    private BufferedReader masukanReader = null;
    // OutputStream untuk tulis balasan
    private OutputStream keluaran = null;
    // Writer untuk OutputStream, pakai yang buffer
    private BufferedWriter keluaranWriter = null;
}

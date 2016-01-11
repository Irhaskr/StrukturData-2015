import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

import java.util.Calendar;

public class ClientProcess implements Runnable {
    ArrayList<Informasi> ID = new ArrayList<Informasi>();
    
    
    public ClientProcess(Socket koneksi, ArrayList <Informasi> ID ){
        this.koneksi = koneksi;
        this.ID=ID;
        
        
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
               tangani();
            }
            catch(IOException salah) { 
                System.out.println(salah);
            }
            finally {
                try { 
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
        String perintah = masukanReader.readLine();
        // Jika tidak ada perintah keluar saja
        if (perintah == null)
            return;            
        // Ada perintah, hilangkan spasi depan & belakang serta ubah ke huruf besar semua
        perintah = perintah.trim().toUpperCase();
        
        // Ambil parameter-nya
        String[] param = perintah.split(" ");
       
        
        // Tangani perintahnya
        if (param[0].compareTo("ID") == 0 && param[2].equals("LANGKAH") && param.length==6) {
            // Hanya bertanya siapa yang mengirim
            Informasi x = null;
            String koordinat = null;
            
            for(Informasi info : ID){
                if(param[1].equals(info.getID())){
                    x = info;
                }
            }
           
            
            if(x!= null){
                koordinat = param[4]+ " "+param[5];
                x.setKoordinat(koordinat);
            }else{
                x = new Informasi();
                x.setIDuser(param[1]);
                koordinat = param[4]+ " "+param[5];
                x.setKoordinat(koordinat);
                ID.add(x);
            }
            
            keluaranWriter.write(""+param[1]);
            System.out.println("ID."+param[1]);
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }else if(param[0].compareTo("ID") == 0 && param[6].equals("KOORDINAT") && param.length==7) {
            Informasi x = null;
            String keluaran = null;
            
            for(Informasi info : ID){
                if(param[1].equals(info.getID())){
                    x = info;
                }
            }
            
            if(x!=null){
                keluaran=x.getInformasi(param[2],param[3]);
            }
            if(keluaran == null){
                keluaran = "Error...";
            }
            
            keluaranWriter.write(keluaran);
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }else {
            // Perintah tidak dikenal
            keluaranWriter.write(PERINTAH_TIDAK_DIKENAL, 0, PERINTAH_TIDAK_DIKENAL.length());
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
    
    private final static String PERINTAH_TIDAK_DIKENAL = "Perintah tidak dikenal!";
}
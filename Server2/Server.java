import java.net.Socket;
import java.net.ServerSocket;
import java.net.BindException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.Scanner;

public class Server {
    public Server() 
           throws BindException, IOException {
        serverSocket = new ServerSocket(33333);
    }
    
    public void dengar() throws IOException {
        System.out.println("Menunggu koneksi...");
        while (true) {
            Socket koneksi = null;
            try {
                koneksi = serverSocket.accept();
                prosesPermintaanClient(koneksi);
                koneksi.close();
            }
            catch(IOException err) {
                System.out.println(err);
            }
            catch(InterruptedException err) {
                System.out.println(err);
            }
        }
    }
    
    private void prosesPermintaanClient(Socket koneksi) 
                 throws InterruptedException, IOException {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        
        // Ambil dan tampilkan masukan
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
        String baris = masukanReader.readLine();
        System.out.println(baris);
        
        // Baca pesan dari keyboard
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Pesan kembali: ");
        baris = keyboard.nextLine();
        
        // Kirim ke client
        OutputStream keluaran = koneksi.getOutputStream();
        BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
        keluaranBuf.write(baris);
        keluaranBuf.newLine();
        keluaranBuf.flush();
        
        // Ambil dan tampilkan masukan yang di Upper
        System.out.print("Hasil Upper: ");
        InputStream masukanBaru = koneksi.getInputStream();
        BufferedReader masukanReaderBaru = new BufferedReader(new InputStreamReader(masukanBaru)); 
        String baru = masukanReaderBaru.readLine();
        System.out.println(baru);
    }

    private ServerSocket serverSocket = null;
}
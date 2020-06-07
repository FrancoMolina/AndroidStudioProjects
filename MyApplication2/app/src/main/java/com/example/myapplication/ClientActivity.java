package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientActivity extends AppCompatActivity {

    private Socket socket;                                          //socket
    private static final int SERVERPORT = 6000;                     //puerto el que les parezca yo puse este en (caso de que no funcione)
    private static final String SERVER_IP = "192.168.100.15";            //IP de la rasp
    private static final String MYTAG="mytag";                      //tag para el mensaje de debugging
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        new Thread(new ClientThread()).start();
    }
    /**
     * metodo para iniciar el socket
     * @param
     * @return void
     * @exception  IOException  si un error E/S ocurre cuando creamos el socket.
     * @exception  UnknownHostException  si no se puede encontrar una direccion IP
     * desde {@code host}, o si un scope_id fue especificado
     * para una direccion IPv6 global.
    * */
    /*public void socketStart(){
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Log.d(MYTAG, "Trying start socket: ");
            socket = new Socket(serverAddr, SERVERPORT);
            //socket = new Socket("192.168.100.15",SERVERPORT);
            Log.d(MYTAG, "socketStart: ");

        } catch (UnknownHostException e1) {
            e1.printStackTrace();
            Log.i(MYTAG, "UnknownHostException");
        } catch (IOException e1) {
            e1.printStackTrace();
            Log.i(MYTAG, "IOException");
        }
        return;
    }*/
    /**
     *metodo que finalmente envia el mensaje
     * @param message
     * @return void
     *
     * */
    public void socketSend(String message){
        try{
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            socket.close();
            Log.d(MYTAG, "socketSending: ");
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                socket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            String message = getIntent().getStringExtra("EXTRA_MESSAGE");
            //estas dos lineas son solo para el debugging
            TextView textView = findViewById(R.id.displayMessage);
            textView.setText(message);
            //hasta aca
            socketSend(message);
        }

    }
}
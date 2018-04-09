package com.lab409.socket.demo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class ClientSendRunnable implements Runnable{

    private Socket s = null;

    public ClientSendRunnable(Socket s){
        this.s=s;
    }

    public void run() {
        // TODO Auto-generated method stub
        OutputStream os=null;
        DataOutputStream dos=null;

        try {
            while(true){
                os=s.getOutputStream();
                dos=new DataOutputStream(os);
                Scanner in=new Scanner(System.in);
                String line=in.nextLine();
                dos.writeUTF(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}




class ClientReceiveRunnable implements Runnable{

    private Socket s=null;

    public ClientReceiveRunnable(Socket s){
        this.s=s;
    }

    public void run() {
        // TODO Auto-generated method stub
        InputStream is=null;
        DataInputStream dis=null;

        try {
            while(true){
                is=s.getInputStream();
                dis=new DataInputStream(is);
                System.out.println("client received:"+dis.readUTF());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

public class TestThreadClient {

    public static void main(String[] args) throws Exception{
        Socket s=null;

        s=new Socket("127.0.0.1",5652);
        Runnable r1=new ClientSendRunnable(s);
        Thread t1=new Thread(r1);
        t1.start();

        Runnable r2=new ClientReceiveRunnable(s);
        Thread t2=new Thread(r2);
        t2.start();
    }

}
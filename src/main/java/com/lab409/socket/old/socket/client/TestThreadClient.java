package com.lab409.socket.old.socket.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
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
        BufferedOutputStream bos;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder(100);
        try {
            while(true){
                String line = reader.readLine();
                if(line==null) continue;
                os=s.getOutputStream();
                dos = new DataOutputStream(os);
                //bos = new BufferedOutputStream(os);

               // writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //Scanner in=new Scanner(System.in);
                //builder.append(in.nextLine());
              //  builder.append("\r\n");
                dos.writeUTF(line+"\r\n");
                dos.flush();
                //writer.write(builder.toString());
                //builder.delete(0,100);
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
        BufferedReader reader;
        try {
            while(true){
                is=s.getInputStream();
                reader =new BufferedReader(new InputStreamReader(is,"UTF-8"));
                System.out.println(s.getRemoteSocketAddress()+ " " +reader.readLine());
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

        s=new Socket("127.0.0.1",8080);
        Runnable r1=new ClientSendRunnable(s);
        Thread t1=new Thread(r1);
        t1.start();

        Runnable r2=new ClientReceiveRunnable(s);
        Thread t2=new Thread(r2);
        t2.start();
    }

}
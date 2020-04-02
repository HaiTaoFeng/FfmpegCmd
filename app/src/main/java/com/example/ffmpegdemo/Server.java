package com.example.ffmpegdemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket = null;
    public final int port = 9998;
    private int i = 0;

    public Server(){

        //输出服务器的IP地址
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("local host:"+addr);
            serverSocket = new ServerSocket(port);
            System.out.println("0k");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void startService(){

        try {
            Socket socket = null;
            System.out.println("waiting...");
            //等待连接，每建立一个连接，就新建一个线程
            while(true){
                socket = serverSocket.accept();//等待一个客户端的连接，在连接之前，此方法是阻塞的
                System.out.println("connect to"+socket.getInetAddress()+":"+socket.getLocalPort());
                new ConnectThread(socket).start();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    //向客户端发送信息
    class ConnectThread extends Thread{
        Socket socket = null;

        public ConnectThread(Socket socket){
            super();
            this.socket = socket;
        }

        @Override
        public void run(){
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                while(true){
                    i++;
                    String msgRecv = dis.readUTF();
                    System.out.println("msg from client:"+msgRecv);
                    dos.writeUTF(msgRecv + i);
                    dos.flush();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}

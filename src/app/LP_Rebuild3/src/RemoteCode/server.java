//package RemoteCode;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Created by zyvis on 2017/4/14.
 * 此代码运行在服务端，作为中间代理服务器
 * 3345作为客户端接入，3346作为数据提供端接入
 * 由于写得仓促，需要先运行这个，
 * 再运行客户端，最终启动数据提供端
 */
public class server {
    public static void main(String[] args) throws IOException {
        DataReceiverListener dataReceiverLinstener=new DataReceiverListener();
        DataSenderListener dataSenderLinstener=new DataSenderListener();
        System.out.println("double socket is ready");
        dataReceiverLinstener.start();
        dataSenderLinstener.start();
        boolean repeat=false;
        while(true){
            if(!repeat){System.out.println("wait for connection");repeat=true;}
            if(dataReceiverLinstener.isLink()&&dataSenderLinstener.isLink()){
                System.out.println("trans start");
                new Send(new PrintStream(dataReceiverLinstener.clientSocket.getOutputStream()),new Scanner(dataSenderLinstener.dataSenderSocket.getInputStream())).run();
                repeat=false;
            }
            else {
                //System.out.println(dataReceiverLinstener.isLink()&&dataSenderLinstener.isLink());
                continue;

            }
        }

    }
    private static class Send extends Thread{
        PrintStream clientPrinter;
        Scanner dataScanner;

        public Send(PrintStream clientPrinter, Scanner dataScanner) {
            this.clientPrinter = clientPrinter;
            this.dataScanner = dataScanner;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("client && data sender is ready");
            for(;;) {
                try{
                    clientPrinter.println(dataScanner.nextLine());
                    System.out.println("double send");
                } catch (NoSuchElementException e){
                    System.out.println("DATA END");
                    //dataSenderLinstener.interruptLink();
                    System.out.println("data sender out of connection !");
                    break;
                }

            }
        }
    }
    private static class DataReceiverListener extends Thread{
        ServerSocket lpClient=new ServerSocket(3345);//LP Client;
        Socket clientSocket=null;

        private DataReceiverListener() throws IOException {
        }

        @Override
        public void run() {
            super.run();
            System.out.println("DataReceiverListener start");
            for(;;) {
                if (!isLink()) {
                    try {
                        clientSocket = lpClient.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("client connect ! isLink = "+isLink());
                    break;
                }
            }
        }
        boolean isLink(){
            return clientSocket!=null;
        }
        void interruptLink(){
            clientSocket=null;
        }
    }
    private static class DataSenderListener extends Thread{
        ServerSocket lpDataSender=new ServerSocket(3346);
        Socket dataSenderSocket=null;
        private DataSenderListener() throws IOException {
        }

        @Override
        public void run() {
            super.run();
            System.out.println("DataSenderListener start");
            for(;;) {
                if (!isLink()) {
                    try {
                        dataSenderSocket = lpDataSender.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("data sender connect ! isLink = "+isLink());
                    break;
                }
            }
        }
        void interruptLink(){
            dataSenderSocket=null;
        }
        boolean isLink(){
            return dataSenderSocket!=null;
        }
    }
}

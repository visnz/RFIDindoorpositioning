package RemoteCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zyvis on 2017/7/17.
 */
public class server_new {
    public static void main(String[] args) throws IOException, InterruptedException {
        PortListener portListener = new PortListener(10086);
        Thread.sleep(5000);
        portListener.disconnect();

    }
    private static class PortListener extends Thread{
        ServerSocket receiver;
        Socket socket =null;
        boolean mention=true;
        private PortListener(int port) throws IOException {
            receiver=new ServerSocket(port);
            this.start();
        }

        @Override
        public synchronized void run() {
            super.run();

            //Log.d("PortListener start");
            for(;;) {
                //System.out.println("run main");
                if (!isLink()) {
                    if(mention) {
                        System.out.println("no socket is linking");
                        mention=false;
                    }
                    try {
                        socket = receiver.accept();
                        if(isLink())System.out.println("connect ! socket link in : "+socket.getInetAddress());
                        mention=true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    if(mention) {
                        System.out.println("socket is linking");
                        mention=false;
                    }
                    //System.out.println(socket.isClosed());

                    if(!socket.isConnected()){
                        System.out.println("remote socket disconnect");
                        try {
                            disconnect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mention=true;
                    }
                }
            }
        }
        void disconnect() throws IOException {
            if(isLink()) {
                System.out.println("port listener disconnect the socket");
                socket.close();
                socket = null;
                mention=true;
                System.out.println(isLink()+"disconnect");
            }
        }
        boolean isLink(){
            return socket !=null;
        }
    }
}

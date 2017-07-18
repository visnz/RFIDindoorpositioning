package Network;

import Loger.defaultLog;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 类中封装了一个Socket和Scanner
 * 直接由构造器构造IP和Port，使用{@link #getData()}获取数据
 * Created by zyvis on 2017/4/10.
 */
public class SocketReceiver {
    protected Socket insideSocket;
    protected Scanner scanner;

    public SocketReceiver(String IP,int port) throws IOException {
        insideSocket=new Socket(IP,port);
        defaultLog.report("connection successfully : "+IP.toString()+":"+port);
        scanner = new Scanner(insideSocket.getInputStream());
        defaultLog.report("scanner ready");
    }
    public String getData() throws IOException {
        String tmp = scanner.nextLine();
        //defaultLog.report("data get : "+tmp);
        return tmp;
    }

}

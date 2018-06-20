package ftpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//主类和程序入口
public class FtpServer {
    static boolean flag=true;
    ServerSocket serverSocket;
    FtpServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        Share.init();
    }
    public void listen()throws IOException{
        Socket socket = null;
        while (true) {
            socket = serverSocket.accept();
            ControllerThread thread = new ControllerThread(socket);
            thread.start();
        }

    }
    public static void main(String[] args)throws IOException{
        FtpServer ftpServer = new FtpServer(21);
        ftpServer.listen();

    }
}

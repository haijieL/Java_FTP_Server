package ftpServer;
import javafx.print.Printer;
import java.io.*;
import java.net.Socket;
//接收命令类
public class ControllerThread extends Thread{
    private int len = 0;
    private String mode="control";
    private String IP;
    private String port;
    private String nowDir;//当前目录
    //保存当前登录账号
    private String accountName;
    private String pass;
    private boolean isLogin = false;//是否登录了
    private int count = 0;//用于记录是否第一次访问，第一次访问数据流没数据
    private Socket socket;
    ControllerThread(){}
    ControllerThread(Socket socket){
        this.socket = socket;
        nowDir = Share.rootDir;
    }
    //传输数据模式的构造函数
    public ControllerThread(Socket socket,String mode) {
        this.socket=socket;
        this.mode=mode;
    }
    public boolean loginValiate(Command command){
        if (command instanceof UserCommand || command instanceof PassComand)
            return true;
        else
            return isLogin;
    }
    @Override
    public void run(){
        Writer writer;
        BufferedReader reader;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                if (count == 0){
                    count++;
                    writer.write("220");
                    writer.write("\r\n");
                    writer.flush();
                }
                else{
                    if (!socket.isClosed()) {
                        String line = reader.readLine();
                        if (line != null) {
                            String[] datas = line.split(" ");
                            String data = datas[0];

                            Command command = CommandFactory.createCommand(data);
                            //进行登录验证
                            if (loginValiate(command)){
                                if (command == null){
                                    writer.write("502  unused command\r\n");
                                    writer.flush();
                                }
                                else{//调用对应命令对象
                                    if (datas.length>=2)
                                        data = datas[1];
                                    command.getResult(data,writer,this);
                                }
                            }
                            else {
                                writer.write("532 please login\r\n");
                                writer.flush();
                            }
                        }
                    }
                    else{
//                        writer.close();
//                        reader.close();
                        break;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public Socket getSocket() {
        return socket;
    }
    public String getNowDir() {
        return nowDir;
    }
    public void setNowDir(String nowDir) {
        this.nowDir = nowDir;
    }
    public boolean isLogin() {
        return isLogin;
    }
    public void setLogin(boolean login) {
        isLogin = login;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getPort() {
        return port;
    }
    public void setIP(String IP) {
        this.IP = IP;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getIP() {
        return IP;
    }
    public void setLen(int len) {
        this.len = len;
    }
    public int getLen() {
        return len;
    }
}

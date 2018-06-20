package ftpServer;
import java.io.*;
import java.net.Socket;
//断点上传
//将服务器上已上传的大小返回给客户端
public class AppeCommand implements Command{
    public void getResult(String data, Writer writer, ControllerThread t){
        System.out.println("开始传输。。。。。。");
        try{
//            writer.write("150 Binary data connection\r\n");
//            writer.flush();
            //数据连接
            Socket tempSocket = new Socket(t.getIP(),Integer.parseInt(t.getPort()));
            InputStream inSocket = tempSocket.getInputStream();
            File file = new File(t.getNowDir()+File.separator + data);
            RandomAccessFile inFile = new
                    RandomAccessFile(t.getNowDir()+"/"+data,"rw");
            if (!file.exists()) {
                writer.write("226 s\r\n");
                writer.flush();
            }
            else{
                inFile.seek(file.length());
                writer.write("226 " + file.length() + "\r\n");
                writer.flush();
            }
            byte byteBuffer[] = new byte[1024];
            int amount;
            int i = 0;
            //这里又会阻塞掉，无法从客户端输出流里面获取数据？是因为客户端没有发送数据么
            while((amount =inSocket.read(byteBuffer) )!= -1){
                inFile.write(byteBuffer, 0, amount);
            }
            System.out.println("传输完成，关闭连接。。。\n");

            inFile.close();
            inSocket.close();
            tempSocket.close();
            //断开数据连接
            writer.write("226 transfer complete\r\n");
            writer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

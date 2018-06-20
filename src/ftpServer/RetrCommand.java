package ftpServer;
import java.io.*;
import java.net.Socket;

/**
 * 处理文件的发送
 * */
public class RetrCommand implements Command{

    @Override
    public void getResult(String data, Writer writer, ControllerThread t) {
        Socket s;
        String desDir = t.getNowDir()+File.separator+data;
        File file = new File(desDir);
        System.out.println(desDir);
        if(file.exists())
        {
            try {
                writer.write("150 open ascii mode...\r\n");
                writer.flush();
                s = new Socket(t.getIP(), Integer.parseInt(t.getPort()));
                BufferedOutputStream dataOut = new BufferedOutputStream(s.getOutputStream());
                byte[] buf = new byte[1024];
                RandomAccessFile is = new RandomAccessFile(file,"rw");
//                long len = t.getLen();
//                long len_file = is.length();
                if (t.getLen() != 0){
                    is.seek(t.getLen());
                    t.setLen(0);
                }
                while(-1 != is.read(buf)) {
                    dataOut.write(buf);
                    //len += 1024;
                    //System.out.println((len/(double)len_file));
                }
                dataOut.flush();
                s.close();
                writer.write("220 transfer complete...\r\n");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                writer.write("220  file does not exist\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

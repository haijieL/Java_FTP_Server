package ftpServer;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
//打印当前路径
public class PwdCommand implements Command{
    @Override
    public void getResult(String data, Writer writer, ControllerThread t){
        String str = "257 \""+t.getNowDir()+"\"is current ";
        try {
            if (new File(t.getNowDir()).isDirectory())
                str += "directory.\r\n";
            else
                str += "file.\r\n";
            writer.write(str);
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

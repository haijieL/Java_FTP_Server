package ftpServer;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

//新建目录
public class MkdCommand implements Command{
    @Override
    public void getResult(String data, Writer writer, ControllerThread t){
        File file = new File(t.getNowDir() + File.separator + data);
        String resp;
        if (file.exists()){
            resp = "550 Already exists.\r\n";
        }
        else{
            file.mkdir();
            resp = "250 Directory created.\r\n";
        }
        try {
            writer.write(resp);
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

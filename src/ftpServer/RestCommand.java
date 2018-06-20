package ftpServer;
import java.io.IOException;
import java.io.Writer;
//断点下载
public class RestCommand {
    public void getResult(String data, Writer writer, ControllerThread t){
        t.setLen(Integer.parseInt(data));
        try {
            writer.write("350\r\n");
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

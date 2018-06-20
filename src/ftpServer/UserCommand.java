package ftpServer;
import java.io.IOException;
import java.io.Writer;
public class UserCommand implements Command{
    @Override
    public void getResult(String data, Writer writer, ControllerThread t) {
        String resp;
        if (Share.userList.containsKey(data)) {
            resp = "331";
            t.setAccountName(data);
        }
        else
            resp="501";
        try{
            writer.write(resp);
            writer.write("\r\n");
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

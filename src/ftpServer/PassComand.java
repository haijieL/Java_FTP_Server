package ftpServer;

import java.io.IOException;
import java.io.Writer;
//密码命令
public class PassComand implements Command {

    @Override
    public void getResult(String data, Writer writer, ControllerThread t) {
        //获得当前用户的密码
        String key = t.getAccountName();
        String pass = Share.userList.get(key);
        String response = null;
        if (pass.equals(data)) {
            t.setLogin(true);
            response = "230 User " + key + " logged in";
        } else {
           // System.out.println("登录失败，密码错误");
            response = "530 wrong password";
        }
        try {
            writer.write(response);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
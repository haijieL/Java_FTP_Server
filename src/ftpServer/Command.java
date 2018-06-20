package ftpServer;

import java.io.Writer;
//命令的接口
interface  Command {
    public void getResult(String data, Writer writer, ControllerThread t);
}

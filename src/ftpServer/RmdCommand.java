package ftpServer;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

//删除文件夹
public class RmdCommand implements Command{
    @Override
    public void getResult(String data, Writer writer, ControllerThread t){
        File file = new File(t.getNowDir() + File.separator + data);
        String resp;
        if (file.exists() && file.isDirectory()){
            del(file);
            resp = "250 Directory removed.\r\n";
        }
        else
            resp = "550 Not a valid directory.\r\n";
        try {
            writer.write(resp);
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void del(File file){
        // 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
        if(file.isDirectory()) {

            File[] subFiles = file.listFiles();
            // 遍历该目录
            for (File subFile : subFiles) {
                del(subFile);
                }
            }

        file.delete();
    }
}

package ftpServer;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//共享的变量
public class Share {
    /*根目录的路径*/
    public static String pathname;
    public static  String rootDir = "E:\\ftp";
    /*允许登录的用户*/
    public static Map<String,String> userList;
    public static void init() throws IOException{
        pathname = "C:\\Users\\Administrator\\Desktop\\user.txt";
        userList = new HashMap<String,String>();
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
        line = br.readLine();
        while (line != null) {
            String[] datas = line.split(":");
            userList.put(datas[0],datas[1]);
            line = br.readLine(); // 一次读入一行数据
        }
        reader.close();
    }
}

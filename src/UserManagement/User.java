package UserManagement;

import ftpServer.Share;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
public class User{
    public static Map <String, String>UserList = new HashMap<String,String>();
    public static String path = "C:\\Users\\Administrator\\Desktop\\user.txt";
    public static void main(String[] args){
        File filename = new File(path);
        try {
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            while (line != null) {
                String[] datas = line.split(":");
                UserList.put(datas[0], datas[1]);
                line = br.readLine(); // 一次读入一行数据
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        String userCommand;
        while (flag) {
            userCommand = scanner.nextLine();
            String[] datas = userCommand.split(" ");
            switch (datas[0]) {
                case "USERLIST":
                    UserManagement.userList();
                    continue;
                case "ADDUSER":
                    UserManagement.addUser(datas[1]);
                    continue;
                case "DELUSER":
                    UserManagement.delUser(datas[1]);
                    continue;
                case "QUIT":
                    UserManagement.save();
                    flag = false;
                    continue;
                default:
                    System.out.println("没有该命令");
            }
        }
    }
}

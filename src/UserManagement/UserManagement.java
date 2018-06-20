package UserManagement;
import ftpServer.Share;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
public class UserManagement {
    public static void userList(){
        System.out.println("用户列表如下:");
        Iterator<Map.Entry<String, String>> it = User.UserList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey());
        }
    }
    public static void addUser(String user){
        if (User.UserList.containsKey(user)){
            System.out.println("添加用户失败，该用户已存在！！！！！！！");
        }
        else{
            Scanner scanner = new Scanner(System.in);
            String pwd, Cpwd;
            while(true){
                System.out.println("请输入密码");
                pwd = scanner.next();
                System.out.println("请确认密码");
                Cpwd = scanner.next();
                if (pwd.equals(Cpwd)){
                    User.UserList.put(user,pwd);
                    System.out.println("添加用户成功");
                    break;
                }
                else{
                    System.out.println("两次密码不一样，请重新输入");
                }
            }
        }
    }
    public static void delUser(String user){
        if (User.UserList.containsKey(user)){
            System.out.println("删除用户失败，该用户不存在！！！！！！！");
        }
        else {
            User.UserList.remove(user);
            System.out.println("删除用户成功！！！！！！！");
        }
    }
    public static void save(){
        Iterator<Map.Entry<String, String>> it = User.UserList.entrySet().iterator();
        String file=User.path;

        //写字符转换成字节流
        try {
            FileOutputStream fileWriter=new FileOutputStream(file);
            //OutputStreamWriter writer=new OutputStreamWriter(fileWriter);
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                fileWriter.write((entry.getKey() + ":" + entry.getValue() + "\n").getBytes());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

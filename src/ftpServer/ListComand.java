package ftpServer;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//获取当前文件夹下所有的文件的信息
public class ListComand implements Command{
    @Override
    public void getResult(String data, Writer writer, ControllerThread t) {
        String desDir;
        if (!data.equals("LIST"))
            desDir = t.getNowDir() + data;
        else
            desDir = t.getNowDir();
        //System.out.println(desDir);
        File dir = new File(desDir);
        if (!dir.exists()) {
            try {
                writer.write("550  Directory does not exist\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder dirs = new StringBuilder();
            //System.out.println("文件目录如下：");
            dirs.append("time\t\t\ttype\tsize\t\tname\n");
            String[] lists = dir.list();
            String flag = null;
            for (String name : lists) {
                //  System.out.println(name);
                File temp = new File(desDir + File.separator + name);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(temp.lastModified());
                dirs.append(sdf.format(cal.getTime()));
                dirs.append("\t");
                if (temp.isDirectory()) {
                    flag = "<DIR>\t\t";
                } else {

                    flag = "\t" + temp.length() + "\t";
                }
                dirs.append(flag);
                dirs.append("\t");
                dirs.append(name);
                dirs.append("\n");

            }
            Socket s;
            try {
                writer.write("125 Data connection already open; Transfer starting.\r\n");
                writer.flush();
                s = new Socket(t.getIP(), Integer.parseInt(t.getPort()));
                BufferedWriter dataWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                dataWriter.write(dirs.toString());
                dataWriter.flush();
                s.close();
                writer.write("220 transfer complete...\r\n");
                writer.flush();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    }



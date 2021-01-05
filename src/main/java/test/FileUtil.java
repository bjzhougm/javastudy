package test;
import java.io.*;

public class FileUtil {
    public static void main(String[] args) throws IOException {
        String url = "/Users/zhouguimin/IdeaProjects/javastudy/src/main/resources/employees.conf";
        InputStream inputStream = new FileInputStream(url);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String data = null;
        while ((data = br.readLine()) != null){
            //System.out.println(data);
            String name = null;
            if (data.contains("a")){
                name = data;
            }
            String salary = null;
            if (data.contains("b")){
                salary = data;
            }
            System.out.println(name + salary);
        }
        br.close();

    }
}


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DecodeDemo {
    public static void main(String[] args) {
        DecodeDemo demo = new DecodeDemo();
        byte[] bytes = demo.readfile("D:\\WorkSpace_vsCode\\java\\data.txt");
        int i = 0;
        for (byte b : bytes) {
            System.out.println(b);
        }
    }

    byte[] readfile(String pathname) {
        File file = new File(pathname);
        FileInputStream input = null;
        byte[] b = new byte[1024];
        try {
            if (file.isFile()) {
                input = new FileInputStream(file);
                int len = input.read(b);
                //System.out.println(String.format("该文件：%s length:%d", pathname， len+""));
            } else {
                System.err.println(String.format("文件:%s 不存在！", pathname));
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                file = null;
                if (null != input) {
                    input.close();
                }

            } catch (IOException e) {
                System.out.println("inputStream close failure!");
            }
        }
        return b;
    }
}
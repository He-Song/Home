import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.ior.ByteBuffer;

public class SocketDemo {
    public static void main(String[] args) {
        SocketDemo sd = new SocketDemo();
        ByteBuffer buffer = sd.getByteBuffer();
        sd.handleBuffer(buffer);

    }
    
    public void handleBuffer(ByteBuffer byteBuffer){
        byte[] bytes = byteBuffer.toArray();
        byteBuffer.read();
        byteBuffer.
        for(byte b : bytes){
            System.out.println(b);
        }
        
    }

    
    public ByteBuffer getByteBuffer(){
        Socket socket = null;
        String IP_ADDR = "challenge.yuansuan.cn";
        int PORT = 7042;
        String outString = "";
        boolean isSuccess = false;
        ByteBuffer bf = new ByteBuffer();
        try {
            socket = new Socket(IP_ADDR, PORT);
            String readline;
            String id = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            InputStream input = socket.getInputStream();
            readline = reader.readLine();
            while (null != readline) {
                System.out.println(readline);
                String[] strs = readline.split(":");
                if (null != strs && strs.length > 1) {
                    id = strs[1];
                    break;
                }
            }
            //往服务器发送信息
            outString = "IAM:" + id + ":bob@example.com\n";
            out.writeBytes(outString);
    
            readline = reader.readLine();
            System.out.println(readline);
            while(null != readline){
                if("SUCCESS!".equals(readline)){
                    isSuccess = true;
                    break;
                }
            }
    
            if(isSuccess){
                //List<Integer> arrayList = SocketDemo.receveData(input);
                byte[] b = new byte[1024];
                //StringBuffer sb = new StringBuffer();
                //String str = null;
                int index;
                try {
                    while((index = input.read(b)) != -1){
                        for(byte bb : b){
                            bf.append(bb);
                            //System.out.print(bb + " ");
                        }
                        /* str = new String(b);
                        sb.append(str); */
                    }
                    System.out.println(bf.size());
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
                //System.out.println(sb);
    
            }
            reader.close();
            out.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket = null;
                    System.out.println("客户端 finally 异常:" + e.getMessage());
                }
            }
        }
        return bf;
    }

}
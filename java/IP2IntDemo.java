public class IP2IntDemo {
    public static void main(String[] args) {
        
        IP2IntDemo ip2IntDemo = new IP2IntDemo();
        System.out.println(123456 & 4294967295L);
        
        long ip = ip2IntDemo.getLongFromIP("192.168.5.20");
        System.out.println(ip);
        System.out.println(ip2IntDemo.getIPFromLong(197267490));
        
        int[][] info = { 
            { 197271584, 36640, 197267490, 2531 }, 
            { 197271584, 61039, 197267490, 2531 },
            { 197271585, 61036, 197267490, 2531 }, 
            { 197271585, 36651, 197267490, 2531 },
            { 197271587, 61038, 197267490, 2531 }, 
            { 197271587, 48824, 197267490, 2531 },
            { 197271586, 55400, 197267490, 2531 },
            { 197271586, 55400, 197267490, 2531 },

            { 197271590, 36640, 197267490, 2531 }, 
            { 197271591, 61039, 197267490, 2531 },
            { 197271592, 61036, 197267490, 2531 }, 
            { 197271593, 36651, 197267490, 2531 },
            { 197271594, 61038, 197267490, 2531 }, 
            { 197271595, 48824, 197267490, 2531 },
            { 197271596, 55400, 197267490, 2531 },
            { 197271597, 55400, 197267490, 2531 },
            { 0, 0, 0, 0}
        };
        for (int i = 0; i < info.length; i++) {
            int flowHash = ip2IntDemo.caclFlowHash(info[i][0], info[i][1], info[i][2], info[i][3]);
            ip2IntDemo.getResult(flowHash);
        }

    }

    public int caclFlowHash(int srcIP, int srcPort, int desIP, int desPort) {
        int ipOr = srcIP ^ desIP;
        int portOr = srcPort ^ desPort;
        int flowHash = 31 * 1 + 6;

        flowHash = 31 * flowHash + portOr;
        flowHash = 31 * flowHash + ipOr;
        return flowHash;
    }

    public int operate(int op1, int op2) {
        return (op1 & Integer.MAX_VALUE) % op2;
    }

    public int operrate_and(int op1, int op2){
        return op1 & op2;
    }

    public void getResult(int flowHash) {
        //System.out.println("index_mod:" + (operate(flowHash, 65537) % 8));
        System.out.println("index_and:" + (operrate_and(flowHash, Integer.MAX_VALUE) % 8));
    }

    public long getLongFromIP(String ip){
        String[] strs = ip.split("\\.");
        long result = -1;
        if(strs.length != 4){
            return -1;
        }else{
            try{
                long a = Long.parseLong(strs[0]);
                long b = Long.parseLong(strs[1]);
                long c = Long.parseLong(strs[2]);
                long d = Long.parseLong(strs[3]);
                a <<= 24;
                b <<= 16;
                c <<= 8;
                result = a + b + c + d;
            }catch(NumberFormatException exception){
                System.err.println("输入的IP格式有误");
            }
        }
        return result;
    }

    public String getIPFromLong(long ip){
        StringBuilder sb = new StringBuilder();
        sb.append((ip & 0xff000000) >> 24).append(".");
        sb.append((ip & 0x00ff0000) >> 16).append(".");
        sb.append((ip & 0x0000ff00) >> 8).append(".");
        sb.append(ip & 0x000000ff);
        return sb.toString();
    }
}

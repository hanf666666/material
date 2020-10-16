import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class querystockcodes {
    public static String db = "D:\\sina-stock-codes-2.csv";

    public static final int COLUMNS = 33;
    public static List<String> codes = new ArrayList<String>();

    static {
        File in = new File(db);
        if (!in.exists()) {
            // 从网络获取
            if (codes.size() < 1)
                try {
                    //js代码段包含股票代码
                    codes = getAllStackCodes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } else {
            // 从本地获取
            if (codes.size() < 1)
                try {
                    codes = getAllStockCodesFromLocal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    // 解析一组股票代码字符串 把code中包括的所有股票代码放入List中
    private static List<String> handleStockCode(String code) {
        List<String> codes = null;
        int end = code.indexOf(";");
        code = code.substring(0, end);
        int start = code.lastIndexOf("=");
        code = code.substring(start);
        code = code.substring(code.indexOf("\"") + 1, code.lastIndexOf("\""));
        codes = Arrays.asList(code.split(","));
        return codes;
    }

    // 返回的值是一个js代码段 包括指定url页面包含的所有股票代码
    public static String getBatchStackCodes(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(30000);
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        StringBuffer sb = new StringBuffer();
        boolean flag = false;
        while ((line = br.readLine()) != null) {
            if (line.contains("<script type=\"text/javascript\">") || flag) {
                sb.append(line);
                flag = true;
            }
            if (line.contains("</script>")) {
                flag = false;
                if (sb.length() > 0) {
                    if (sb.toString().contains("code_list") && sb.toString().contains("element_list")) {
                        break;
                    } else {
                        sb.setLength(0);
                    }
                }
            }
        }
        if (br != null) {
            br.close();
            br = null;
        }
        return sb.toString();
    }

    // 获取新浪78也的所有股票代码
    public static List<String> getAllStackCodes() throws IOException {
        System.out.println("Start getAllStackCodes...");
        List<String> codes = new ArrayList<String>();
        int i = 1;
        URL url = null;
        // 新浪 股票 好像目前为止就 77页
        for (; i < 78; i++) {
            url = new URL("http://vip.stock.finance.sina.com.cn/q/go.php/vIR_CustomSearch/index.phtml?p=" + i +"&sr_p=-1");
            String code = getBatchStackCodes(url);
            System.out.println("code=" + i);
            codes.addAll(handleStockCode(code));
        }
        saveStockCodes(codes, db);
        return codes;
    }

    // 把新浪77页的所有股票代码存入本地文件
    private static void saveStockCodes(List<String> codes, String fileName) throws IOException {
        // 将所有股票代码存入文件中
        // System.out.println("Start saveStockCodes...");
        File out = new File(fileName);
        if (!out.exists())
            out.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(out,true));
        for (String code : codes) {
            bw.write(code);
            bw.newLine();
        }
        if (bw != null) {
            bw.close();
            bw = null;
        }
    }

    public static List<String> getAllStockCodesFromLocal() throws IOException {
        List<String> codes = new ArrayList<String>();
        File in = new File(db);
        if (!in.exists())
            throw new IOException("指定数据文件不存在!");
        BufferedReader br = new BufferedReader(new FileReader(in));
        String line = null;
        while ((line = br.readLine()) != null) {
            codes.add(line);
        }
        // 删除最后一个空行
        codes.remove(codes.size() - 1);
        if (br != null) {
            br.close();
            br = null;
        }
        return codes;
    }

}
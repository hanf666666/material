package 股票涨跌预判demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class zhumain {
    public static void main(String[] args) {
          //存放股票代码
          List<String> codeslist = new ArrayList<String>();
        // 新浪 股票 好像目前为止就 77页
        for (int i=0; i <77; i++) {
            URL url = null;
            String code="";
            try {
                url = new URL("http://vip.stock.finance.sina.com.cn/q/go.php/vIR_CustomSearch/index.phtml?p=" + i +"&sr_p=-1");
                //包含股票的js代码
                 code = getBatchStackCodes(url);
            } catch (Exception e) {
                e.printStackTrace();
            }

           // System.out.println("第几页" + i);
            codeslist.addAll(handleStockCode(code));






        }


        //近30日的股票分析
        String guolvchucode=fenXin(codeslist);


    }

    //分析股票
    private static String fenXin(List<String> codeslist) {
        System.out.println("总共有多少只股票==="+codeslist.size());
        if(codeslist.size()>0){
            for(String code:codeslist){
                stockUtils.stockper(code.substring(2,code.length()),"20190825","20191113");
            }

        }





        return "";
    }


    // 返回的值是一个js代码段 包括指定url页面包含的所有股票代码
    private static String getBatchStackCodes(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(30000);
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        StringBuffer sb = new StringBuffer();
        boolean flag = false;
        while ((line = br.readLine()) != null) {
           // System.out.println("打印每一行"+line);
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


    // 解析一组股票代码字符串 把code中包括的所有股票代码放入List中
    private static List<String> handleStockCode(String code) {
        List<String> codes = null;
        int end = code.indexOf(";");
        code = code.substring(0, end);
        int start = code.lastIndexOf("=");
        code = code.substring(start);
        code = code.substring(code.indexOf("\"") + 1, code.lastIndexOf("\""));
        codes = Arrays.asList(code.split(","));
        //  System.out.println("codes每页的股票编码==="+code);
        return codes;
    }



}

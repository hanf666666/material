import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import 股票涨跌预判demo.Stock;

import java.net.URL;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class demo2 {

    public static void main(String[] args) {
        try {
    			/*搜狐股票行情历史接口
    			 * 说明：其中 code :股票代码，格式 国别_代码
    			 * 		   period :数据周期（d表示日线，m表示月线，w表示周线）
    			 * 		   order：排序方法（D表示降序排，A表示升序排）
    			 * 		   stat=1代表合计，stat=0代表不合计；
    			 * 返回的数据以这条为例"2018-07-20","61.22","61.83","0.61","1.00%","61.22","62.69","57637","35856.55","0.53%";
    			 * 分别表示:                 日期， 开盘，   收盘，   涨跌，  涨幅，   最低，   最高，   成交量， 成交额，   换手率。
    			 * 【优点】1）有重要数据换手率;2）免费;3）封装简单;
    			 * 【局限性】1）数据不是除权后的数据;2）一次最多只能读取100条交易记录;3）只能取日线、周线、月线数据;
    			 */
            String url="http://q.stock.sohu.com/hisHq?code=cn_000677"
                    + "&start=20191024&end=20191025&stat=1&order=D&period=d"
                    + "&callback=historySearchHandler&rt=jsonp";
            URL ur = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ur.openStream(), "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line.substring(22,line.length()-2));
                minprice(line.substring(22,line.length()-2),"2019-10-25");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //算是否可以入股
    private static void minprice(String  json,String currentdate) {

        JSONObject jsonObject = null;
        HashMap<String, String> stringStringHashMap = new HashMap<String, String> ();
        try {
            jsonObject = new JSONObject(json);
            System.out.println("股票代码==="+jsonObject.getString("code"));
            JSONArray hq = jsonObject.getJSONArray("hq");
            for (int i = 0; i < hq.length(); i++) {
                JSONArray jsonArray = hq.getJSONArray(i);
               /*  * 返回的数据以这条为例"2018-07-20","61.22","61.83","0.61","1.00%","61.22","62.69","57637","35856.55","0.53%";
    			 * 分别表示:                 日期， 开盘，   收盘，   涨跌，  涨幅，   最低，   最高，   成交量， 成交额，   换手率。
    			 **/

                 String  curdatetime=jsonArray.getString(0); //VARCHAR(20) COMMENT '股票时间',
                  String startprice=jsonArray.getString(1);// DOUBLE COMMENT '开盘',
                 String endprice =jsonArray.getString(2);//DOUBLE COMMENT '收盘',
                 String  udprice =jsonArray.getString(3); //DOUBLE COMMENT '涨跌',
                String  udpriceper =jsonArray.getString(4); //DOUBLE COMMENT '涨跌幅度',

                String  smallprice=jsonArray.getString(5) ;//DOUBLE COMMENT '最高',
                String bigprice  =jsonArray.getString(6);//DOUBLE COMMENT '最低',
                String finishliang  =jsonArray.getString(7);//DOUBLE COMMENT '成交量',
                String finishmoney  =jsonArray.getString(8);//DOUBLE COMMENT '成交额',
                String changliang  =jsonArray.getString(9);//DOUBLE COMMENT '换手率'

                Stock stock= new Stock(curdatetime,startprice,endprice,udprice,udpriceper,bigprice,smallprice,finishliang,finishmoney,changliang);
                System.out.println(stock);
                minprice2(jsonObject.getString("code"),stringStringHashMap,stock,currentdate);

            }
            if(stringStringHashMap.size()>0){
                for(String key:stringStringHashMap.keySet()){
                    System.out.println("筛选的股票==="+key);

                }
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void minprice2(String code,Map stringStringHashMap, Stock stock, String currentdate) {
        if(stock.getCurdatetime().equals(currentdate)){
            stringStringHashMap.put(code,stock.getBigprice());
        }else{
            if(Double.parseDouble(stock.getBigprice()) < Double.parseDouble((String) stringStringHashMap.get(code))){
                   stringStringHashMap.remove(code);
            }

        }







    }



}


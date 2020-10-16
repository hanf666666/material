package demo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class FenxiStockUtils {


    //策略1获取最低价格的股票
    public static boolean minPrice(Map<String, Stock> stringStockHashMap, String endtime) {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String curent = null;
        try {
            System.out.println(simpleDateFormat1.parse(endtime) + "fasdfa");
            curent = simpleDateFormat2.format(simpleDateFormat1.parse(endtime).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double minprice = null;
        Stock minstock = null;

        for (String stringStock : stringStockHashMap.keySet()) {
            Stock stock = stringStockHashMap.get(stringStock);

            Double curprice = Double.valueOf(stock.endprice);
            if (minprice == null || minprice >= curprice) {
                minprice = curprice;
                minstock = stock;
                System.out.println("minstock======" + minstock);
            }

        }
        System.out.println("====minstock======" + minstock);

        if (minstock != null) {
            if (curent.equals(minstock.curdatetime)) {
                return true;
            }
        }


        return false;
    }


    //获取n天的涨停股票
    public static boolean zhangtindays(Map<String, Stock> stringStockHashMap, String endtime) {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String curent = null;
        int i = 0;
        try {
            System.out.println(simpleDateFormat1.parse(endtime) + "fasdfa");
            curent = simpleDateFormat2.format(simpleDateFormat1.parse(endtime).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double minprice = null;
        Stock minstock = null;

        for (String stringStock : stringStockHashMap.keySet()) {
            Stock stock = stringStockHashMap.get(stringStock);


            Double Udpriceper = Double.valueOf(stock.getUdpriceper().substring(0, 4));
            System.out.println(curent + "==" + Udpriceper + "==" + stock.curdatetime);
            if (curent.equals(stock.curdatetime) && Udpriceper >= 9.90) {

                i = i + 1;
                System.out.println("minstock======" + minstock);
            }
            if ("2020-04-15".equals(stock.curdatetime) && Udpriceper >= 9.90) {

                i = i + 1;
            }
            if ("2020-04-16".equals(stock.curdatetime) && Udpriceper >= 9.90) {

                i = i + 1;
            }

        }
        System.out.println("i的值" + i);

        if (i == 3) {
            return true;
        }


        return false;
    }

    //换手率高
    public static Boolean huanshougao(Map<String, Stock> stringStockHashMap, String endtime) {

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String curent = null;
        int i = 0;
        try {
            System.out.println(simpleDateFormat1.parse(endtime) + "fasdfa");
            curent = simpleDateFormat2.format(simpleDateFormat1.parse(endtime).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double minprice = null;
        Stock minstock = null;

        for (String stringStock : stringStockHashMap.keySet()) {
            Stock stock = stringStockHashMap.get(stringStock);

            Double huanshoulv = null;
            try {
                huanshoulv = Double.valueOf(stock.getChangliang().substring(0, 4));
            } catch (Exception e) {
                e.printStackTrace();
                huanshoulv = new Double(0);
            }

            if (huanshoulv >= 4) {

                i = i + 1;
                System.out.println("minstock======" + minstock);
            }


        }
        System.out.println("i的值" + i);

        if (i > 20) {
            return true;
        }


        return false;


    }


    //换手率高
    public static Boolean zhangtingAndHuanshouDi(Map<String, Stock> stringStockHashMap, String endtime) {

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String curent = null;
        int i = 0;
        try {
            System.out.println(simpleDateFormat1.parse(endtime) + "fasdfa");
            curent = simpleDateFormat2.format(simpleDateFormat1.parse(endtime).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double minprice = null;
        Stock minstock = null;

        for (String stringStock : stringStockHashMap.keySet()) {
            Stock stock = stringStockHashMap.get(stringStock);

            Double Udpriceper = Double.valueOf(stock.getUdpriceper().substring(0, 4));
            System.out.println(curent + "==" + Udpriceper + "==" + stock.curdatetime);

            Double huanshoulv = null;
            try {
                huanshoulv = Double.valueOf(stock.getChangliang().substring(0, 4));
            } catch (Exception e) {
                e.printStackTrace();
                huanshoulv = new Double(0);
            }

            if (huanshoulv <= 5 && curent.equals(stock.curdatetime) && Udpriceper >= 9.90) {

                i = i + 1;
                System.out.println("涨停加换手低======" + minstock);
            }


        }
        System.out.println("i的值" + i);

        if (i == 1) {
            return true;
        }


        return false;


    }


    //三板且回落股
    public static Boolean ban3huiluo(Map<String, Stock> stringStockHashMap, String endtime) {

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String curent = null;
        int i = 0;
        int j = 0;
        try {
            System.out.println(simpleDateFormat1.parse(endtime) + "fasdfa");
            curent = simpleDateFormat2.format(simpleDateFormat1.parse(endtime).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double minprice = null;
        Stock minstock = null;

        for (String stringStock : stringStockHashMap.keySet()) {
            Stock stock = stringStockHashMap.get(stringStock);

            Double Udpriceper = Double.valueOf(stock.getUdpriceper().substring(0, 4));
            System.out.println(curent + "==" + Udpriceper + "==" + stock.curdatetime);
            if ( Udpriceper >= 9.90) {

                i = i + 1;
                System.out.println("minstock======" + minstock);
            }

            if ( Udpriceper < -2.0) {

                j = j + 1;
                System.out.println("minstock======" + minstock);
            }
        }


        System.out.println("i的值" + i);

        if (i > 3&&j<5) {
            return true;
        }


        return false;


    }


}

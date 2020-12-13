package 股票涨跌预判demo;

public class Stock {
              // ["2019-10-25",         "3.83",              "3.79",        "-0.07",         "-1.81%",         "3.71",        "3.85",               "359732",         "13599.56",            "4.16%"]
    public Stock(String curdatetime, String startprice, String endprice, String udprice, String udpriceper, String smallprice, String bigprice, String finishliang, String finishmoney, String changliang) {
        this.curdatetime = curdatetime;
        this.startprice = startprice;
        this.endprice = endprice;
        this.udprice = udprice;
        this.udpriceper = udpriceper;
        this.bigprice = bigprice;
        this.smallprice = smallprice;
        this.finishliang = finishliang;
        this.finishmoney = finishmoney;
        this.changliang = changliang;
    }

    //String stockcode;// VARCHAR(20) COMMENT '股票代码',
    String  curdatetime; //VARCHAR(20) COMMENT '股票时间',
    //String stockname ;//VARCHAR(20) COMMENT '股票名称',
    String startprice;// DOUBLE COMMENT '开盘',
    String endprice ;//DOUBLE COMMENT '收盘',
    String  udprice; //DOUBLE COMMENT '涨跌',
    String udpriceper;//DOUBLE COMMENT '涨跌幅度',
    String bigprice ;//DOUBLE COMMENT '最高',
    String smallprice ;//DOUBLE COMMENT '最低',
    String finishliang ;//DOUBLE COMMENT '成交量',
    String finishmoney ;//DOUBLE COMMENT '成交额',
    String changliang ;//DOUBLE COMMENT '换手率'

    public String getCurdatetime() {
        return curdatetime;
    }

    public void setCurdatetime(String curdatetime) {
        this.curdatetime = curdatetime;
    }

    public String getStartprice() {
        return startprice;
    }

    public void setStartprice(String startprice) {
        this.startprice = startprice;
    }

    public String getEndprice() {
        return endprice;
    }

    public void setEndprice(String endprice) {
        this.endprice = endprice;
    }

    public String getUdpriceper() {
        return udpriceper;
    }

    public void setUdpriceper(String udpriceper) {
        this.udpriceper = udpriceper;
    }

    public String getBigprice() {
        return bigprice;
    }

    public void setBigprice(String bigprice) {
        this.bigprice = bigprice;
    }

    public String getSmallprice() {
        return smallprice;
    }

    public void setSmallprice(String smallprice) {
        this.smallprice = smallprice;
    }

    public String getFinishliang() {
        return finishliang;
    }

    public void setFinishliang(String finishliang) {
        this.finishliang = finishliang;
    }

    public String getFinishmoney() {
        return finishmoney;
    }

    public void setFinishmoney(String finishmoney) {
        this.finishmoney = finishmoney;
    }

    public String getChangliang() {
        return changliang;
    }

    public void setChangliang(String changliang) {
        this.changliang = changliang;
    }

    public String getUdprice() {
        return udprice;
    }

    public void setUdprice(String udprice) {
        this.udprice = udprice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "curdatetime='" + curdatetime + '\'' +
                ", startprice='" + startprice + '\'' +
                ", endprice='" + endprice + '\'' +
                ", udprice='" + udprice + '\'' +
                ", udpriceper='" + udpriceper + '\'' +
                ", bigprice='" + bigprice + '\'' +
                ", smallprice='" + smallprice + '\'' +
                ", finishliang='" + finishliang + '\'' +
                ", finishmoney='" + finishmoney + '\'' +
                ", changliang='" + changliang + '\'' +
                '}';
    }
}

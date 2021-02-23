package model;

public class CustomerOrder {

    private String tranID;
    private String custID;
    private String itemInfo;
    private double itemPrice;
    private int itemQty;
    private int loyaltyPoint;

    public CustomerOrder() {
    }

    public CustomerOrder(String tranID, String custID, String itemInfo, double itemPrice, int itemQty) {
        this.tranID = tranID;
        this.custID = custID;
        this.itemInfo = itemInfo;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "CustomerID=" + custID +
                ", TranID=" + tranID +
                ", Item Info=" + itemInfo +
                ", Item Price=" + itemPrice +
                ", Item Qty=" + itemQty +
                ", Loyalty Point='" + loyaltyPoint + '\'' +
                '}';
    }

    public String getTranID() {
        return tranID;
    }

    public void setTranID(String tranID) {
        this.tranID = tranID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }
}

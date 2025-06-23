package org.mrdarkimc.itemworth.WorthProvider;

public class Priceable {
    private String type;
    private int buyprice;
    private int sellprice;

    public Priceable(String type, int buyprice, int sellprice) {
        this.type = type;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
    }
    public Priceable(int buyprice, int sellprice) {
        this.type = "empty";
        this.buyprice = buyprice;
        this.sellprice = sellprice;
    }

    public int getBuyprice() {
        return buyprice;
    }

    public int getSellprice() {
        return sellprice;
    }
}

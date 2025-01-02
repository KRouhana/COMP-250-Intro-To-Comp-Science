public abstract class MarketProduct {
    private String product_name;

    public MarketProduct(String product_name){
        this.product_name=product_name;
    }

    public String getName(){
        return product_name;
    }

    public abstract int getCost();
    public abstract boolean equals(Object obj);
}
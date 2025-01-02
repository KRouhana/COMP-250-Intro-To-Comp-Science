public class Fruit extends MarketProduct {
    private double weight;
    private int pricePerKg;

    public Fruit(String productName, double weight, int pricePerKg) {
        super(productName);
        this.weight = weight;
        this.pricePerKg = pricePerKg;
    }

    public int getCost() {
        return (int) (weight * pricePerKg);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Fruit) {
            Fruit other = (Fruit) obj;
            return other.getName().equals(this.getName()) &&
                   other.weight == this.weight &&
                   other.getCost() == this.getCost();
        }
        return false;
    }

}
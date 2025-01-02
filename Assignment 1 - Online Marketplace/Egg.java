public class Egg extends MarketProduct {
    private int numEggs;
    private int pricePerDozen;

    public Egg(String productName, int numEggs, int pricePerDozen) {
        super(productName);
        this.numEggs = numEggs;
        this.pricePerDozen = pricePerDozen;
    }

    public int getCost() {
        return (int) Math.round((double) numEggs * pricePerDozen / 12);
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Egg) {
            Egg other = (Egg) obj;
            return this.getName().equals(other.getName()) &&
                   this.numEggs == other.numEggs &&
                   this.getCost() == other.getCost();
        }
        return false;
    }
}
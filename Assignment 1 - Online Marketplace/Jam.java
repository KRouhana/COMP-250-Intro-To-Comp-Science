public class Jam extends MarketProduct {
    private int numJars;
    private int pricePerJar;

    public Jam(String productName, int numJars, int pricePerJar) {
        super(productName);
        this.numJars = numJars;
        this.pricePerJar = pricePerJar;
    }

    public int getCost() {
        return numJars * pricePerJar;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Jam) {
            Jam other = (Jam) obj;
            return other.getName().equals(this.getName()) &&
                   other.numJars == this.numJars &&
                   other.getCost() == this.getCost();
        }
        return false;
    }
}
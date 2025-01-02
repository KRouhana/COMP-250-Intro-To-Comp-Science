public class SeasonalFruit extends Fruit{
    public SeasonalFruit(String product_name, double weight, int price_per_kg){
        super (product_name,weight, price_per_kg);
    }

        public int getCost() {
            return (int) (super.getCost()*0.85);
        }
    }
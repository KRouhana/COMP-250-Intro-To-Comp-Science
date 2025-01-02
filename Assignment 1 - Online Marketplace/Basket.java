public class Basket {
    private MarketProduct[] basket;

    public Basket() {
        basket = new MarketProduct[0];
    }

    public MarketProduct[] getProducts() {
        MarketProduct[] basketCopy = new MarketProduct[basket.length];
        System.arraycopy(basket, 0, basketCopy, 0, basket.length);
        return basketCopy;
    }

    public void add(MarketProduct product) {
        MarketProduct[] tempBasket = new MarketProduct[basket.length + 1];
        System.arraycopy(basket, 0, tempBasket, 0, basket.length);
        tempBasket[basket.length] = product;
        this.basket = tempBasket;
    }

    public boolean remove(MarketProduct product) {
        for (int i = 0; i < basket.length; i++) {
            if (product.equals(basket[i])) {
                MarketProduct[] tempBasket = new MarketProduct[basket.length - 1];
                System.arraycopy(basket, 0, tempBasket, 0, i);
                System.arraycopy(basket, i + 1, tempBasket, i, basket.length - i - 1);
                basket = tempBasket;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        basket = new MarketProduct[0];
    }

    public int getNumOfProducts() {
        return basket.length;
    }

    public int getSubTotal() {
        int subTotal = 0;
        for (MarketProduct product : basket) {
            subTotal += product.getCost();
        }
        return subTotal;
    }

    public int getTotalTax() {
        double tax = 0;
        for (MarketProduct product : basket) {
            if (product instanceof Jam) {
                tax += 0.15 * product.getCost();
            }
        }
        return (int) tax;
    }

    public int getTotalCost() {
        return getSubTotal() + getTotalTax();
    }

    public String formatPrice(int price) {
        if (price <= 0) {
            return "0.00";
        }
        int cents = price % 100;
        int units = price / 100;
        return String.format("%d.%02d", units, cents);
    }

    public String toString() {
        StringBuilder receipt = new StringBuilder();
        for (MarketProduct product : basket) {
            receipt.append(product.getName()).append("\t").append(formatPrice(product.getCost())).append("\n");
        }
        receipt.append("\nSubtotal\t").append(formatPrice(getSubTotal())).append("\n");
        receipt.append("Total Tax\t").append(formatPrice(getTotalTax())).append("\n");
        receipt.append("\nTotal Cost\t").append(formatPrice(getTotalCost()));
        return receipt.toString();
    }
}

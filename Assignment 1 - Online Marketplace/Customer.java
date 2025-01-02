public class Customer {
    private String name;
    private int balance;
    private Basket basket;

    public Customer(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.basket = new Basket();
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Basket getBasket() {
        return basket;
    }

    public int addFunds(int funds) {
        if (funds < 0) {
            throw new IllegalArgumentException("Cannot add negative funds");
        }
        balance += funds;
        return balance;
    }

    public void addToBasket(MarketProduct product) {
        basket.add(product);
    }

    public boolean removeFromBasket(MarketProduct product) {
        return basket.remove(product);
    }

    public String checkOut() {
        if (balance < basket.getTotalCost()) {
            throw new IllegalStateException("Not enough funds to cover balance.");
        }
        balance -= basket.getTotalCost();
        String receipt = basket.toString();
        basket.clear();
        return receipt;
    }
}
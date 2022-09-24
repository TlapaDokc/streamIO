import java.io.*;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private boolean[] isFilled;
    private int[] userBasket;
    private int totalsum = 0;

    public Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
        this.isFilled = new boolean[prices.length];
        this.userBasket = new int[prices.length];
    }

    public Basket(int[] prices, String[] products, boolean[] isFilled, int[] userBasket) {
        this.prices = prices;
        this.products = products;
        this.isFilled = isFilled;
        this.userBasket = userBasket;
    }

    public int[] getPrices() {
        return prices;
    }

    public String[] getProducts() {
        return products;
    }

    public int getTotalsum() {
        return totalsum;
    }

    public void addToCart(int productNum, int amount) {
        userBasket[productNum - 1] += amount;
        isFilled[productNum - 1] = true;
    }

    public void printCart() {
        for (int i = 0; i < userBasket.length; i++) {
            if (isFilled[i]) {
                int sum = userBasket[i] * prices[i];
                totalsum += sum;
                System.out.println(products[i] + " " + userBasket[i] + " шт " +
                        prices[i] + " руб/шт " + sum + " в сумме");
            }
        }
    }

    public void saveBin(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Basket) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

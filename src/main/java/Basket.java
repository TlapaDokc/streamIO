import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Basket {
    private String[] products;
    private int[] prices;
    private boolean[] isFilled;
    private int[] userBasket;
    private int totalsum = 0;

    public Basket() {
    }

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

    public boolean[] getIsFilled() {
        return isFilled;
    }

    public int[] getUserBasket() {
        return userBasket;
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

    public void saveTxt(File textFile) {
        try (PrintWriter out = new PrintWriter(textFile);) {
            for (int i : userBasket) {
                out.print(i + " ");
            }
            out.println();
            for (String i : products) {
                out.print(i + " ");
            }
            out.println();
            for (int i : prices) {
                out.print(i + " ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        String[] strUserBasket;
        String[] scProducts;
        String[] strPrices;
        try (Scanner sc = new Scanner(textFile)) {
            strUserBasket = sc.nextLine().split(" ");
            scProducts = sc.nextLine().split(" ");
            strPrices = sc.nextLine().split(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] scUserBasket = new int[strUserBasket.length];
        for (int i = 0; i < strUserBasket.length; i++) {
            scUserBasket[i] = Integer.parseInt(strUserBasket[i]);
        }
        boolean[] scIsFilled = new boolean[scUserBasket.length];
        for (int i = 0; i < scUserBasket.length; i++) {
            if (scUserBasket[i] > 0) {
                scIsFilled[i] = true;
            }
        }
        int[] scPrices = new int[strPrices.length];
        for (int i = 0; i < strPrices.length; i++) {
            scPrices[i] = Integer.parseInt(strPrices[i]);
        }
        return new Basket(scPrices, scProducts, scIsFilled, scUserBasket);
    }
}

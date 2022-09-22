import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File basketTextFile = new File("basket.txt");
        Basket basket;
        if (basketTextFile.exists()) {
            basket = Basket.loadFromTxtFile(basketTextFile);
        } else {
            int[] prices = {12, 15, 7, 9, 11};
            String[] products = {"Молоко", "Яйца", "Хлеб", "Картофель", "Гречневая крупа"};
            basket = new Basket(prices, products);
        }
        System.out.println("Список возможных продуктов для покупки:");
        for (int i = 0; i < basket.getProducts().length - 1; i++) {
            System.out.println(i + 1 + ". " + basket.getProducts()[i] + " " + basket.getPrices()[i]);
        }
        while (true) {
            System.out.println("Выберите товар и количество или введите 'end'");
            Scanner sc = new Scanner(System.in);
            String strProductNumberCount = sc.nextLine();
            if (strProductNumberCount.equals("end")) {
                break;
            }
            String[] productNumberCount = strProductNumberCount.split(" ");
            if (productNumberCount.length != 2) {
                System.out.println("Введите два числа");
                continue;
            }
            int productNum;
            int amount;
            try {
                productNum = Integer.parseInt(productNumberCount[0]);
                amount = Integer.parseInt(productNumberCount[1]);
            } catch (NumberFormatException e) {
                System.out.println("Введите числа");
                continue;
            }
            if (productNum > 5 || productNum < 1 || amount < 1) {
                System.out.println("Введенные числа не корректны");
                continue;
            }
            basket.addToCart(productNum, amount);
            basket.saveTxt(basketTextFile);
        }
        System.out.println("Ваша корзина:");
        basket.printCart();
        System.out.println("Итог " + basket.getTotalsum() + " руб");
    }
}
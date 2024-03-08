/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CustomersPurchaseSortFind {

    private final ArrayList<Purchase> customers;

    public CustomersPurchaseSortFind() {
        this.customers = new ArrayList<>();
    }

    void readFile(String fname) {
        if (fname == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }
        try (Scanner scanner = new Scanner(new File(fname))){
            while(scanner.hasNext()) customers.add(new Purchase(scanner.nextLine()));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fname);
            e.printStackTrace();
        }
    }

    void showSortedBy(String category) {
        switch (category.toLowerCase()) {
            case "nazwiska": {
                System.out.println(category);
                customers.stream()
                        .sorted(
                                Comparator.comparing(Purchase::getClientSurname).thenComparing(Purchase::getClientId)
                        ).forEach(e -> System.out.println(e.getAllInfo()));
                System.out.println();
                break;
            }
            case "koszty": {
                System.out.println(category);
                customers.stream()
                        .sorted(
                            Comparator.<Purchase, Double>comparing(
                                    purchase -> purchase.getCostOfGoods() * purchase.getQuantity()
                            ).reversed().thenComparing(Purchase::getClientId)
                        ).forEach(e -> System.out.println(e.getAllInfo() + " (koszt: " + e.getCostOfGoods() * e.getQuantity() + ")"));
                System.out.println();
                break;
            }
        }
    }

    void showPurchaseFor(String clientId) {
        System.out.println("Klient " + clientId);
        customers.stream().filter(
                client -> client.getClientId().equals(clientId)
                ).forEach(System.out::println);
        System.out.println();
    }
}

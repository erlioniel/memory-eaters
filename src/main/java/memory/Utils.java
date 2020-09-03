package memory;

import lombok.SneakyThrows;

import java.util.Scanner;

public final class Utils {
    @SneakyThrows
    public static void inputAwait() {
        var scanner = new Scanner(System.in);
        System.out.println("Please input a line");

        scanner.nextLine();
        System.out.println("Starting");
    }

}

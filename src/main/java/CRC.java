import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduceti sirul binar: ");
        String message = scanner.next();

        System.out.print("Introduceti polinomul generator: ");
        String polynomial = scanner.next();

        if (!isValidBinary(message) || !isValidBinary(polynomial)) {
            System.out.println("Eroare: Sirurile trebuie sa fie binare!");
            return;
        }

        if (message.length() <= polynomial.length()) {
            System.out.println("Eroare: Lungimea mesajului trebuie sa fie mai mare decat polinomul!");
            return;
        }

        String crcResult = calculateCRC(message, polynomial);
        System.out.println("Mesaj transmis: " + crcResult);
    }

    private static boolean isValidBinary(String s) {
        return s.matches("[01]+");
    }

    private static String padMessage(String message, int polyLength) {
        return message + "0".repeat(polyLength - 1);
    }

    private static String xorOperation(String dividend, String divisor) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < divisor.length(); i++) {
            result.append(dividend.charAt(i) == divisor.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    private static String divide(String message, String polynomial) {
        int polyLength = polynomial.length();
        String remainder = message.substring(0, polyLength);

        for (int i = polyLength; i <= message.length(); i++) {
            if (remainder.charAt(0) == '1') {
                remainder = xorOperation(remainder, polynomial);
            }
            remainder = remainder.substring(1);
            if (i < message.length()) {
                remainder += message.charAt(i);
            }
            System.out.println("Pas intermediar: " + remainder);
        }
        return remainder;
    }

    private static String calculateCRC(String message, String polynomial) {
        String paddedMessage = padMessage(message, polynomial.length());
        System.out.println("Mesaj extins: " + paddedMessage);

        String remainder = divide(paddedMessage, polynomial);

        return message + remainder;
    }
}

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        String[][] array = new String[n][n];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = ".";
                if (i == j || j == n - i - 1) {
                    array[i][j] = "*";
                } else if (i == (n - 1) / 2 || j == (n - 1) / 2) {
                    array[i][j] = "*";
                }
            }
        }

        for (int j = 0; j < array.length; j++) {
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[j][k] + " ");
            }
            System.out.println();
        }
    }
}
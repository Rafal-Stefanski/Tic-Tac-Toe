import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = scanner.nextInt();

        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean check = false;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == n && array[i - 1] == m || array[i] == m && array[i - 1] == n) {
                check = true;
                break;
            }
        }
        System.out.println(check);
    }
}
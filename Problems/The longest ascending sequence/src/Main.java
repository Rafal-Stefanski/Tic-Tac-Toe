import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
//        int[] array = {1, 2, 4, 1, 2, 3, 5, 7, 4, 3};

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        int counter = 1;
        int maxCounter = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] < array[i]) {
                counter++;
                maxCounter = counter;
            } else {
                counter = 1;
            }
        }

        System.out.println(maxCounter);

    }
}
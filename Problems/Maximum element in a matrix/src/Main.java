import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] array = new int[n][m];

        int minValue = Integer.MIN_VALUE;
        String location = "";

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = scanner.nextInt();
                if (array[i][j] > minValue) {
                    minValue = array[i][j];
                    location = i + " " + j;
                }
            }
        }

        System.out.println(location);
    }
}
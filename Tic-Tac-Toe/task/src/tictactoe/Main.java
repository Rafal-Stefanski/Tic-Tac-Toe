package tictactoe;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        String userInput = "_________";
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                gameGrid[i][j] = userInput.charAt(i * 3 + j);
            }
        }
        displayGameGrid(gameGrid);
        String currentGameState = getGameState(gameGrid).toString();
        //System.out.println(currentGameState);
        int totalMoves = 0;
        while (currentGameState.equals("Game not finished")) {
            updateGameGrid(gameGrid,totalMoves % 2);
            currentGameState = getGameState(gameGrid).toString();
            //System.out.println(currentGameState);
            displayGameGrid(gameGrid);
            ++totalMoves;
        }
        System.out.println(currentGameState);
    }

    static char[][] gameGrid = new char[3][3];
    public static boolean impossibleCheck(char[][] gameGrid) {
        boolean isXWon = false;
        boolean isYWon = false;
        int XCount = 0;
        int OCount = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (gameGrid[i][j] == 'X') {
                    ++XCount;
                } else if (gameGrid[i][j] == 'O') {
                    ++OCount;
                }
            }

        }

        char currentSymbolCheck = 'X';
        for (int start = 0; start < 2; ++start) {
            for (int i = 0; i < 3; ++i) {
                if (gameGrid[i][0] == currentSymbolCheck && gameGrid[i][1] == currentSymbolCheck && gameGrid[i][2] == currentSymbolCheck){
                    if (currentSymbolCheck == 'X') {
                        isXWon = true;
                    } else {
                        isYWon = true;
                    }
                    break;

                }
                if (gameGrid[0][i] == currentSymbolCheck && gameGrid[1][i] == currentSymbolCheck && gameGrid[2][i] == currentSymbolCheck){
                    if (currentSymbolCheck == 'X') {
                        isXWon = true;
                    } else {
                        isYWon = true;
                    }
                    break;
                }
            }
            for (int j = 0; j < 2; ++j) {
                if (gameGrid[0][0] == currentSymbolCheck && gameGrid[1][1] == currentSymbolCheck && gameGrid[2][2] == currentSymbolCheck){
                    if (currentSymbolCheck == 'X') {
                        isXWon = true;
                    } else {
                        isYWon = true;
                    }
                    break;
                }
                if (gameGrid[0][2] == currentSymbolCheck && gameGrid[1][1] == currentSymbolCheck && gameGrid[2][0] == currentSymbolCheck){
                    if (currentSymbolCheck == 'X') {
                        isXWon = true;
                    } else {
                        isYWon = true;
                    }
                    break;
                }
            }
            currentSymbolCheck = 'O';
        }
        if ((isXWon && isYWon) || Math.abs(XCount - OCount) >= 2) {
            return true;
        }
        return false;
    }
    public static int checkEmptyCells(char[][] gameGrid) {
        int emptyCellsCount = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (gameGrid[i][j] == '_') {
                    ++emptyCellsCount;
                }
            }
        }
        return  emptyCellsCount;
    }

    public static String checkWins(char[][] gameGrid) {
        char currentSymbolCheck = 'X';
        for (int start = 0; start < 2; ++start) {
            for (int i = 0; i < 3; ++i) {
                if (gameGrid[i][0] == currentSymbolCheck && gameGrid[i][1] == currentSymbolCheck && gameGrid[i][2] == currentSymbolCheck){
                    return  currentSymbolCheck + " wins";

                }
                if (gameGrid[0][i] == currentSymbolCheck && gameGrid[1][i] == currentSymbolCheck && gameGrid[2][i] == currentSymbolCheck){
                    return  currentSymbolCheck + " wins";
                }
            }
            for (int j = 0; j < 2; ++j) {
                if (gameGrid[0][0] == currentSymbolCheck && gameGrid[1][1] == currentSymbolCheck && gameGrid[2][2] == currentSymbolCheck){
                    return  currentSymbolCheck + " wins";
                }
                if (gameGrid[0][2] == currentSymbolCheck && gameGrid[1][1] == currentSymbolCheck && gameGrid[2][0] == currentSymbolCheck){
                    return  currentSymbolCheck + " wins";
                }
            }
            currentSymbolCheck = 'O';
        }
        return "";
    }

    public static String getGameState(char[][] gameGrid) {
        String winStatus = checkWins(gameGrid);
        if (impossibleCheck(gameGrid)){
            return "Impossible";
        } else if (winStatus != "") {
            return winStatus;
        } else if (checkWins(gameGrid) == "" && checkEmptyCells(gameGrid) == 0) {
            return "Draw";
        } else {
            return "Game not finished";
        }

    }
    public static void displayGameGrid(char[][] gameGrid) {
        System.out.println("---------");
        for (int i = 0; i < 3; ++i) {
            System.out.print("| ");
            for (int j = 0; j < 3; ++j) {
                System.out.print(gameGrid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void updateGameGrid(char[][] gameGrid, int currentPlayerSymbol) {
        Scanner scanner = new Scanner(System.in);
        boolean isException = true;
        System.out.println("Enter the coordinates:");
        String inputX = scanner.next();
        String inputY = scanner.next();
        while (!inputX.matches("[0-9]+") || !inputY.matches("[0-9]+")) {
            System.out.println("You should enter numbers!");
            System.out.println("Enter the coordinates:");
            inputX = scanner.next();
            inputY = scanner.next();
        }
        int intX = Integer.parseInt(inputX);
        int intY = Integer.parseInt(inputY);
        while (intX > 3 || intY > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            System.out.println("Enter the coordinates:");
            inputX = scanner.next();
            inputY = scanner.next();
            while (!inputX.matches("[0-9]+") || !inputY.matches("[0-9]+")) {
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates:");
                inputX = scanner.next();
                inputY = scanner.next();
            }
            intX = Integer.parseInt(inputX);
            intY = Integer.parseInt(inputY);
        }
        while (gameGrid[3 - intY][intX - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            System.out.println("Enter the coordinates:");
            inputX = scanner.next();
            inputY = scanner.next();
            while (!inputX.matches("[0-9]+") || !inputY.matches("[0-9]+")) {
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates:");
                inputX = scanner.next();
                inputY = scanner.next();
            }
            intX = Integer.parseInt(inputX);
            intY = Integer.parseInt(inputY);

            while (intX > 3 || intY > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.println("Enter the coordinates:");
                inputX = scanner.next();
                inputY = scanner.next();
                while (!inputX.matches("[0-9]+") || !inputY.matches("[0-9]+")) {
                    System.out.println("You should enter numbers!");
                    System.out.println("Enter the coordinates:");
                    inputX = scanner.next();
                    inputY = scanner.next();
                }
                intX = Integer.parseInt(inputX);
                intY = Integer.parseInt(inputY);
            }


        }
        if (currentPlayerSymbol == 0) {
            gameGrid[3 - intY][intX - 1] = 'X';
        } else {
            gameGrid[3 - intY][intX - 1] = 'O';
        }


    }


}


// previuos version

//package tictactoe;
//
//import java.util.Scanner;
//
//public class Main {
//
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
////        System.out.print("Enter cells: ");
////        String in = scanner.next();
//
//        String in = "_________";
//        printArray(in);
//        playGame(in);
//
////        newCoordinates(in, 'O'); // need to change input for X and O
//
//    }
//
//    /**
//     * for  digit check
//     * if ((input.matches("\\d+"))) {
//     * if ((!(input.matches("[123]+")))) {
//     */
//
//    public static void playGame(String in){
//        char sign;
//        int count = 0;
//        while (whoWon(in) == 0) {
//            if (count % 2 == 0) {
//                sign = 'X';
//            } else {
//                sign = 'O';
//            }
//            count++;
//            in = newCoordinates(in, sign);
////            newCoordinates(in, sign); // very bad idea!
//        }
//
//
//    }
//
//    public static String newCoordinates(String in, char sign) {
//
//        boolean flag = true;
//
//        do {
//
//        String newInput = in;
//        System.out.print("Enter the coordinates: ");
//        String coordinates = scanner.next() + scanner.nextLine();
//
//        char[] inArray = new char[9];
//        for (int i = 0; i < 9; i++) {
//            inArray[i] = in.charAt(i);
//        }
////        System.out.println(Arrays.toString(inArray));
//
//        String coordinatesToCheck = coordinates.replaceAll("\\s", "");
//        boolean coordinatesAreNumbers = true;
//        for (int i = 0; i < coordinatesToCheck.length(); i++) {
//            if (!Character.isDigit(coordinatesToCheck.charAt(i))) {
//                System.out.println("You should enter numbers!");
//                coordinatesAreNumbers = false;
//                flag = false;
//                break;
//            }
//        }
//
//        if (coordinatesAreNumbers) {
//            char f = coordinates.charAt(0);
//            int firstCoordinate = Integer.parseInt(String.valueOf(f));
//            char s = coordinates.charAt(coordinates.length() - 1);
//            int secondCoordinate = Integer.parseInt(String.valueOf(s));
//
////        System.out.println("firstCoordinate " + firstCoordinate);
////        System.out.println("secondCoordinate " + secondCoordinate);
//
//            if (firstCoordinate <= 0 | firstCoordinate >= 4 | secondCoordinate <= 0 | secondCoordinate >= 4) {
//                System.out.println("Coordinates should be from 1 to 3!");
////                scanner.reset();
//                flag = false;
//            }
//
//            if (firstCoordinate > 0 && firstCoordinate < 4 && secondCoordinate > 0 && secondCoordinate < 4) {
//                switch (coordinates) {
//                    case "1 1":
//                        if (inArray[0] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[0] = sign;
//                        }
//                        break;
//                    case "1 2":
//                        if (inArray[1] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[1] = sign;
//                        }
//                        break;
//                    case "1 3":
//                        if (inArray[2] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[2] = sign;
//                        }
//                        break;
//                    case "2 1":
//                        if (inArray[3] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[3] = sign;
//                        }
//                        break;
//                    case "2 2":
//                        if (inArray[4] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[4] = sign;
//                        }
//                        break;
//                    case "2 3":
//                        if (inArray[5] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[5] = sign;
//                        }
//                        break;
//                    case "3 1":
//                        if (inArray[6] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[6] = sign;
//                        }
//                        break;
//                    case "3 2":
//                        if (inArray[7] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[7] = sign;
//                        }
//                        break;
//                    case "3 3":
//                        if (inArray[8] != '_') {
//                            System.out.println("This cell is occupied! Choose another one!");
//                            flag = false;
//                        } else {
//                            inArray[8] = sign;
//                        }
//                        break;
//                }
//                // changing the in String
//                newInput = "";
//                for (int i = 0; i < inArray.length; i++) {
//                    newInput += inArray[i];
//                }
//                printArray(newInput);
//                if (!newInput.equals(in)) {
//                }
//            }
//        }
//        return newInput;
//        } while (flag);
//    }
//
//    public static void printArray(String in) {
//
//        System.out.println("---------");
//        System.out.println("| " + in.charAt(0) + " " + in.charAt(1) + " " + in.charAt(2) + " |");
//        System.out.println("| " + in.charAt(3) + " " + in.charAt(4) + " " + in.charAt(5) + " |");
//        System.out.println("| " + in.charAt(6) + " " + in.charAt(7) + " " + in.charAt(8) + " |");
//        System.out.println("---------");
//    }
//
//
//    public static int whoWon(String in) {
//
//        boolean xWins = false;
//        boolean oWins = false;
////        boolean draw = false;
//        boolean impossible = false;
//
//        // count X's and O's
//        int countX = 0;
//        int countO = 0;
//        int count_ = 0;
//        for (int i = 0; i < in.length(); i++) {
//            if (in.charAt(i) == 'X') {
//                countX++;
//            } else if (in.charAt(i) == 'O') {
//                countO++;
//            } else if (in.charAt(i) == '_') {
//                count_++;
//            }
//        }
//        if ((countO > (countX + 1)) || ((countO + 1) < countX)) {
////            System.out.println("Too many moves from one player - Impossible");
//            impossible = true;
//
//        }
//
//        // filling up the array[][]
//        char[][] ticTackArray = new char[3][3];
//        int k = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                ticTackArray[i][j] = in.charAt(k);
//                k++;
//            }
//        }
//
//        // building rows
//        String raw0 = "";
////        int rawCount =0;
//        for (int j = 0; j < 3; j++) {
//            raw0 += (ticTackArray[0][j]);
//        }
//        String raw1 = "";
//        for (int j = 0; j < 3; j++) {
//            raw1 += (ticTackArray[1][j]);
//        }
//        String raw2 = "";
//        for (int j = 0; j < 3; j++) {
//            raw2 += (ticTackArray[2][j]);
//        }
////        System.out.println(raw0);
////        System.out.println(raw1);
////        System.out.println(raw2);
//
//        // building columns
//        String coll0 = "";
//        for (int i = 0; i < 3; i++) {
//            coll0 += (ticTackArray[i][0]);
//        }
//
//        String coll1 = "";
//        for (int i = 0; i < 3; i++) {
//            coll1 += (ticTackArray[i][1]);
//        }
//
//        String coll2 = "";
//        for (int i = 0; i < 3; i++) {
//            coll2 += (ticTackArray[i][2]);
//        }
//
//        // building straight across
//        String strAcross = "";
//        for (int i = 0; i < 3; i++) {
//            strAcross += (ticTackArray[i][i]);
//        }
//
//        // building second across
//        int j = 2;
//        String secAcross = "";
//        for (int i = 0; i < 3; i++) {
//            secAcross += (ticTackArray[i][j]);
//            j--;
//        }
//
//        if (coll0.equals("XXX") || coll1.equals("XXX") || coll2.equals("XXX") || strAcross.equals("XXX") || secAcross.equals("XXX") || raw0.equals("XXX") || raw1.equals("XXX") || raw2.equals("XXX")) {
//            xWins = true;
//        }
//        if (coll0.equals("OOO") || coll1.equals("OOO") || coll2.equals("OOO") || strAcross.equals("OOO") || secAcross.equals("OOO") || raw0.equals("OOO") || raw1.equals("OOO") || raw2.equals("OOO")) {
//            oWins = true;
//        }
//        int winnLoose = 0;
//        if (xWins & oWins) {
//            impossible = true;
//        }
//        if (impossible) {
////            System.out.println("Impossible");
//            return winnLoose = 5;
//        } else if (xWins) {
//            System.out.println("X wins");
//            return winnLoose = 1;
//        } else if (oWins) {
//            System.out.println("O wins");
//            return winnLoose = 2;
//        } else if (count_ == 0) {
//            System.out.println("Draw");
//            return winnLoose = 3;
//        } else {
////            System.out.println("Game not finished");
//            return winnLoose = 0;
//        }
//
////        System.out.println("\n=== check ===");
////        System.out.println("impossible " + impossible);
////        System.out.println("xWins " + xWins);
////        System.out.println("oWins " + oWins);
////        System.out.println("draw " + draw);
////        System.out.println("count of '_' = " + count_);
//
//    }
//
//}
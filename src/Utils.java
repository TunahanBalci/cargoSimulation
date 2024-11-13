

public class Utils {

    public static final String RED_BACKGROUND_BRIGHT = "\u001B[101m"; // Bright red background
    public static final String WHITE_TEXT = "\u001B[97m"; // White text
    public static final String RESET = "\u001B[0m"; // Reset to default color
    public static final String YELLOW_BACKGROUND_BRIGHT = "\u001B[103m"; // Bright yellow background
    public static final String BLACK_TEXT = "\u001B[30m"; // Black text
    public static final String BLUE_BACKGROUND = "\u001B[44m"; // Dark blue background
    public static final String YELLOW_TEXT = "\u001B[33m";

    public static void cityInfo(String message){
        System.out.println(BLUE_BACKGROUND + BLACK_TEXT + " " + message);
    }

    public static void printError(String message){
        System.out.println(RED_BACKGROUND_BRIGHT + WHITE_TEXT + " ERROR: " + message);
    }

    public static void printInfo(String message){
        System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_TEXT +" INFO: " + message);
    }

    public static void printReadFile(String message){
        System.out.println(YELLOW_TEXT + "ReadFile: " + message + RESET);
    }

}

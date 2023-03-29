import java.util.Scanner;

/**
 * A program that calculates the cost of tuition and fees for a specified number
 * of semesters.
 * The program prompts the user to enter the cost of a regular credit, the cost
 * of an overloaded credit,
 * the maximum number of credits without being considered overloaded, and the
 * number of semesters to calculate.
 * The program then calculates the total cost of tuition and fees based on the
 * user input and prints the result.
 * If the user does not enter any values, default values are used instead.
 *
 * @author Eli Woodard
 * @version 1.03
 */
public class Main {

    /**
     * The main method of the program. Prompts the user for input, calculates the
     * cost of tuition and fees,
     * and prints the result.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n-------------Press Enter to use Default Values:-------------\n"
                + "| Regular credit:              | 380.45$ | \n"
                + "| Overloaded creidt:           | 252$    | \n"
                + "| Max credits before overload: | 15      | \n"
                + "------------------------\n");

        double regularCreditCost = getInputOrDefault(scanner, "Enter the cost of a regular credit: ", 380.45);
        double overloadedCreditCost = getInputOrDefault(scanner, "Enter the cost of an overloaded credit: ", 252.0);
        int maxCreditsWithoutOverload = getInputOrDefault(scanner,
                "Enter the maximum number of credits without being considered overloaded: ", 15);
        int numCredits = getIntFromUser("Enter the number of semesters you want to calculate: ");

        double totalTuition = calculateTuition(regularCreditCost, overloadedCreditCost, maxCreditsWithoutOverload,
                numCredits, scanner);
        double totalFees = calculateFees(maxCreditsWithoutOverload, numCredits, numCredits);

        double additionalFees = getDoubleFromUser("Enter any additional fees you want to add (or 0 if none): ");
        if (additionalFees > 0) {
            totalFees += additionalFees;
        }

        printTotalCost(totalTuition, totalFees);
    }

    /**
     * Gets input from the user, and returns the default value if the input is empty
     * or invalid.
     *
     * @param scanner      The scanner object used to get input from the user.
     * @param message      The prompt to display to the user.
     * @param defaultValue The default value to return if the user does not enter
     *                     any input.
     * @return The integer value entered by the user, or the default value if no
     *         input or invalid input is entered.
     */
    private static int getInputOrDefault(Scanner scanner, String message, int defaultValue) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            // System.out.println("No value entered, using default value.");
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value entered, using default value.");
                return defaultValue;
            }
        }
    }

    /**
     * Returns the integer input value entered by the user, or a default value if
     * the input is empty or not a valid integer.
     * 
     * @param scanner      the scanner object to use for input
     * @param message      the message to display when prompting for input
     * @param defaultValue the default value to return if input is empty
     * @return the integer value entered by the user or the default value
     */
    private static double getInputOrDefault(Scanner scanner, String message, double defaultValue) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            // System.out.println("No value entered, using default value.");
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value entered, using default value.");
                return defaultValue;
            }
        }
    }

    /**
     * Prompts the user for an integer value, and keeps prompting until a valid
     * integer greater than zero is entered.
     * 
     * @param message the message to display when prompting for input
     * @return the integer value entered by the user
     */
    private static int getIntFromUser(String message) {
        Scanner scanner = new Scanner(System.in);
        int value;
        do {
            System.out.print(message);
            value = scanner.nextInt();
            if (value <= 0) {
                System.out.println("Invalid value entered, using default value.");
                value = 15;
            }
        } while (value <= 0);
        return value;
    }

    /**
     * Calculates the total tuition cost based on the regular credit cost,
     * overloaded credit cost, maximum credits before overload,
     * and number of semesters entered by the user.
     * 
     * @param regularCreditCost         the cost of a regular credit
     * @param overloadedCreditCost      the cost of an overloaded credit
     * @param maxCreditsWithoutOverload the maximum number of credits before being
     *                                  considered overloaded
     * @param numSemesters              the number of semesters to calculate tuition
     *                                  for
     * @param scanner                   the scanner object to use for input
     * @return the total tuition cost
     */
    private static double calculateTuition(double regularCreditCost, double overloadedCreditCost,
            int maxCreditsWithoutOverload, int numSemesters, Scanner scanner) {
        double totalTuition = 0.0;
        for (int i = 1; i <= numSemesters; i++) {
            int numCredits = getIntFromUser("Enter the number of credits for semester " + i + ": ");
            if (numCredits > maxCreditsWithoutOverload) {
                totalTuition += ((numCredits - maxCreditsWithoutOverload) * overloadedCreditCost)
                        + (maxCreditsWithoutOverload * regularCreditCost);
            } else {
                totalTuition += numCredits * regularCreditCost;
            }
        }
        return totalTuition;
    }

    /**
     * Calculates the total fees based on the maximum credits before overload,
     * number of semesters, and number of credits entered by the user.
     * 
     * @param maxCreditsWithoutOverload the maximum number of credits before being
     *                                  considered overloaded
     * @param numSemesters              the number of semesters to calculate fees
     *                                  for
     * @param numCredits                the number of credits entered by the user
     * @return the total fees
     */
    private static double calculateFees(int maxCreditsWithoutOverload, int numSemesters, int numCredits) {
        double totalFees = 0.0;
        for (int i = 1; i <= numSemesters; i++) {
            if (numCredits >= 1 && numCredits <= 10) {
                totalFees += 175.0 + 30.0 + 10.0;
            } else if (numCredits >= 11 && numCredits <= maxCreditsWithoutOverload) {
                totalFees += 175.0 + 30.0;
            }
        }
        return totalFees;
    }

    /**
     * Prompts the user for a double value, and keeps prompting until a valid double
     * is entered.
     * 
     * @param message the message to display when prompting for input
     * @return the double value entered by the user
     */
    private static double getDoubleFromUser(String message) {
        Scanner scanner = new Scanner(System.in);
        double value = 0.0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                // System.out.println("No value entered, please try again.");
            } else {
                try {
                    value = Double.parseDouble(input);
                    isValidInput = true;
                } catch (NumberFormatException e) {
                    // System.out.println("Invalid value entered, please try again.");
                }
            }
        }
        return value;
    }

    /**
     * Prints the total tuition, total fees, and total cost to the console.
     * 
     * @param totalTuition the total tuition cost
     * @param totalFees    the total fees
     */
    private static void printTotalCost(double totalTuition, double totalFees) {
        double totalCost = totalTuition + totalFees;
        System.out.println("\n------------------------\n");
        System.out.printf("Total tuition: $%.2f\n", totalTuition);
        if (totalFees > 0) {
            System.out.printf("Total fees: $%.2f (not important) \n", totalFees);
        }
        System.out.printf("Total cost: $%.2f\n", totalCost);
        System.out.println("\n------------------------\n");
    }
}

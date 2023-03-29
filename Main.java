import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the original values
        double originalRegularCreditCost = 380.45;
        double originalOverloadedCreditCost = 252.0;
        int originalMaxCreditsWithoutOverload = 16;

        // Ask the user for new values
        System.out.print("Enter the cost of a regular credit (default " + originalRegularCreditCost + "): ");
        double regularCreditCost = scanner.nextDouble();
        if (regularCreditCost <= 0) {
            regularCreditCost = originalRegularCreditCost;
        }

        System.out.print("Enter the cost of an overloaded credit (default " + originalOverloadedCreditCost + "): ");
        double overloadedCreditCost = scanner.nextDouble();
        if (overloadedCreditCost <= 0) {
            overloadedCreditCost = originalOverloadedCreditCost;
        }

        System.out.print("Enter the maximum number of credits without being considered overloaded (default " + originalMaxCreditsWithoutOverload + "): ");
        int maxCreditsWithoutOverload = scanner.nextInt();
        if (maxCreditsWithoutOverload <= 0) {
            maxCreditsWithoutOverload = originalMaxCreditsWithoutOverload;
        }

        // Calculate the tuition and fees
        System.out.print("Enter the number of semesters you want to calculate: ");
        int numSemesters = scanner.nextInt();

        double totalTuition = 0.0;
        double totalFees = 0.0;
        for (int i = 1; i <= numSemesters; i++) {
            System.out.print("Enter the number of credits for semester " + i + ": ");
            int numCredits = scanner.nextInt();
            if (numCredits > maxCreditsWithoutOverload) {
                totalTuition += ((numCredits - maxCreditsWithoutOverload) * overloadedCreditCost) + (maxCreditsWithoutOverload * regularCreditCost);
            } else {
                totalTuition += numCredits * regularCreditCost;
            }
            if (numCredits >= 1 && numCredits <= 10) {
                totalFees += 175.0 + 30.0 + 10.0;
            } else if (numCredits >= 11 && numCredits <= maxCreditsWithoutOverload) {
                totalFees += 175.0 + 30.0;
            }
        }

        System.out.print("Enter any additional fees you want to add (or 0 if none): ");
        double additionalFees = scanner.nextDouble();
        if (additionalFees > 0) {
            totalFees += additionalFees;
        }

        double totalCost = totalTuition + totalFees;
        System.out.println("\n------------------------\n");
        System.out.println("Total tuition: $" + totalTuition);
        if (totalFees > 0) {
            System.out.println("Total fees: $" + totalFees);
            System.out.println("Total cost: $" + totalCost);
        }
        System.out.println("\n------------------------\n");
    }
}

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class BudgetApp {
    public static void main(String[] args) throws FileNotFoundException{

        // Wave 1:
        // Variables
        // BudgetCategory groceries = new BudgetCategory("Groceries", 500, 401);
        // BudgetCategory rent = new BudgetCategory("Rent", 1350, 1350);
        // BudgetCategory diningOut = new BudgetCategory("Dining Out", 120, 185);
        // BudgetCategory transportation = new BudgetCategory("Transportation", 200, 190);
        // BudgetCategory entertainment = new BudgetCategory("Entertainment", 150, 165);
        // BudgetCategory utilities = new BudgetCategory("Utilities", 150, 165);
        
        // Call out getters and toString
        //for groceries
        // System.out.println(groceries);
        // System.out.println(groceries.toString());
        // System.out.println("Groceries limit: " + groceries.getLimit());

        // // for rent
        // System.out.println(rent);
        // System.out.println(rent.toString());
        // System.out.println("Rent spent: " + rent.getSpent());

        // Wave 3
        //1. Modify your code so that it accepts a file name from the command line argument. Have it read from that file to populate the budget category information.
        String fileName = args[0];
        Scanner scan = new Scanner(new File(fileName));

        // Create a list of BudgetCategory
        List <BudgetCategory> budgetList = new ArrayList<>();
        Map<String, BudgetCategory> budgetCategoryMap = new HashMap<>();

        // Create an instance to for budget class
        Budget budget = new Budget();

        while(scan.hasNextLine()) {
            String category = scan.nextLine();

            // Limit comes first, then spending
            double limit = scan.nextDouble();
            double spent = scan.nextDouble();

            // Consume \n after spent input 
            if(scan.hasNextLine()) scan.nextLine();

            //2. Store each of the category, spent, limit scanned in the loop to a list and to a map
            BudgetCategory myBudgetList = new BudgetCategory(category, limit, spent);
            budgetList.add(myBudgetList);
            budgetCategoryMap.put(category, myBudgetList);

            // Add value into our budget
            budget.add(myBudgetList);


            // String limitString = String.format("$%.2f", limit);
            // String spentString = String.format("$%.2f", spent);

            
        }
            // From my understanding, the Most overspent will be what we spent minus limit. For example:
            // Dining out limit: 120, spent: 185. -> Overspent: 65. 
            // -> This will be the first number to be displayed as first.
            // -> After testing with print statement, I figured that I don't need to use Collections.reverseOrder().
            // Collections.sort(budgetList);

            Scanner userScanner = new Scanner(System.in);

            // While loop
            while(true){
                System.out.println("Please enter the category that you're interested in: ");
                String categoryName = userScanner.nextLine().trim();

                if (categoryName.equalsIgnoreCase("quit")){
                    System.out.println("Thanks for stopping by!");
                    userScanner.close();
                    break;
                } else if (categoryName.equalsIgnoreCase("summary")){
                    double totalLimit = budget.totalLimit();
                    double totalSpent = budget.totalSpent();
                    double remainder = budget.remainder();
                    

                    // For loop
                    // for (BudgetCategory category : budgetCategoryMap.values()){
                    //     totalLimit +=  category.getLimit();
                    //     totalSpent+= category.getSpent();
                    // }

                    // double difference = totalSpent - totalLimit;
                    System.out.println("Here's your Budget summary:");
                    System.out.println("Total of LIMIT: " + totalLimit);
                    System.out.println("Total of SPENT: " + totalSpent);

                    if(remainder > 0){
                        System.out.println("You're OVER the budget!!! " + remainder);
                    } else if (remainder< 0){
                        System.out.println("You're UNDER the budget! " + Math.abs(remainder));
                    } else {
                        System.out.println("You're just making an EVEN! Better watch out.");
                    }
                }

                else {
                    // Use budget.get(categoryName) instead of budgetCategoryMap.get(categoryName) to be able to ignore case-sensitive when user enter any category
                    BudgetCategory myCategory = budget.get(categoryName); 
                if(myCategory == null){
                    System.out.println("There is no match! Try again...");
                } else {
                    System.out.println(myCategory);
                }
            }
            
        }
    }

            // Print out the whole list by using for each loop
            // System.out.println("Categories List Most Overspent to Least:");
            // for (BudgetCategory eachCategory : budgetList){
            //     System.out.println(eachCategory);
            // }
    
        // double totalDifferent = budgetDifference(budgetList);
    //     if (totalDifferent > 0){
    //         System.out.println("You're OVER budget: " + totalDifferent);
    //     }
    //     else if (totalDifferent < 0){
    //         System.out.println("You're UNDER budget " + totalDifferent + ". Good job!");
    //     }
    //     else {System.out.println("You're okay for making 0 balance.");}
    
    // }

    /**
     * Returns overall how much over/under budget a person is given a list of their
     * categories.
     * 
     * This should be the sum of how much over/under budget each individual category is.
     * 
     * If the person is under budget, the result will be negative. If they are over budget, the result will be positive.
     * 
     * @param categories the budget categories with the spend
     * @return the total amount over/under budget
     */
    public static double budgetDifference(List<BudgetCategory> categories) {
        // TODO: You will implement this method in Wave 5
        // Note that this method SHOULD NOT have a print statement.
        // It should instead return the value.

        double totalDifferent = 0;
        for (BudgetCategory eachCategory : categories){
            totalDifferent += eachCategory.differences();
    }
        return totalDifferent;
    }

}


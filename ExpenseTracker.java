
import java.io.*;
        import java.text.*;
        import java.util.*;
        import java.util.stream.Collectors;
import java.util.regex.*;

public class ExpenseTracker {

    private static final String EXPENSES_FILE = "expenses.json";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static List<Expense> expenses = new ArrayList<>();

    static class Expense {
        int id;
        String description;
        double amount;
        String date;

        public Expense(int id, String description, double amount, String date) {
            this.id = id;
            this.description = description;
            this.amount = amount;
            this.date = date;
        }
    }

    public static void main(String[] args) {
        loadExpenses();

        Scanner scanner = new Scanner(System.in);
        String command;

        // Interactive loop for continuous user input
        while (true) {
            System.out.print("Enter command (add/list/delete/summary/exit): ");
            command = scanner.nextLine().trim().toLowerCase();


            if ("exit".equalsIgnoreCase(command)) {
                break; // Exit the loop
            }

            switch (command) {
                case "add":
                    addExpense(scanner);
                    break;
                case "list":
                    listExpenses();
                    break;
                case "delete":
                    deleteExpense(scanner);
                    break;
                case "summary":
                    summary(scanner);
                    break;
                default:
                    System.out.println("Unknown command. Try again.");
            }
            saveExpenses();
        }

        scanner.close();
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        int id = expenses.size() + 1;
        String date = DATE_FORMAT.format(new Date());

        expenses.add(new Expense(id, description, amount, date));
        System.out.println("Expense added successfully (ID: " + id + ")");
    }

    private static void listExpenses() {
        System.out.println("ID  Date       Description          Amount");
        for (Expense expense : expenses) {
            System.out.printf("%-4d %-10s %-20s $%.2f%n", expense.id, expense.date, expense.description, expense.amount);
        }
    }

    private static void deleteExpense(Scanner scanner) {
        System.out.print("Enter ID of expense to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Expense expenseToRemove = null;
        for (Expense expense : expenses) {
            if (expense.id == id) {
                expenseToRemove = expense;
                break;
            }
        }

        if (expenseToRemove != null) {
            expenses.remove(expenseToRemove);
            System.out.println("Expense deleted successfully");
        } else {
            System.out.println("Expense with ID " + id + " not found.");
        }
    }

    private static void summary(Scanner scanner) {
        System.out.print("Enter month (1-12) for summary or press Enter for all: ");
        String monthInput = scanner.nextLine();

        double total = 0;
        if (!monthInput.isEmpty()) {
            int month = Integer.parseInt(monthInput);
            total = expenses.stream()
                    .filter(expense -> getMonth(expense.date) == month)
                    .mapToDouble(expense -> expense.amount)
                    .sum();
            System.out.printf("Total expenses for month %d: $%.2f%n", month, total);
        } else {
            total = expenses.stream().mapToDouble(expense -> expense.amount).sum();
            System.out.printf("Total expenses: $%.2f%n", total);
        }
    }

    private static int getMonth(String date) {
        try {
            Date parsedDate = DATE_FORMAT.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            return calendar.get(Calendar.MONTH) + 1; // Month is 0-based, hence +1
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void loadExpenses() {
        File file = new File(EXPENSES_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String description = fields[1];
                double amount = Double.parseDouble(fields[2]);
                String date = fields[3];
                expenses.add(new Expense(id, description, amount, date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveExpenses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXPENSES_FILE))) {
            for (Expense expense : expenses) {
                writer.write(expense.id + "," + expense.description + "," + expense.amount + "," + expense.date);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

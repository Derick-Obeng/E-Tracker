# E-Tracker


Sure! Here’s a short README for your Java-based Expense Tracker application:

---

# Expense Tracker

A simple command-line application to track personal expenses, built in Java. This program allows users to add, list, delete, and summarize expenses. The expenses are stored in a file (`expenses.json`) to persist across program runs.

## Features

- **Add Expense**: Add a new expense with a description and amount.
- **List Expenses**: View all expenses with their details (ID, description, amount, and date).
- **Delete Expense**: Delete an expense by its ID.
- **Summary**: View the total amount of all expenses, or filter by a specific month.

## Commands

- `add`: Add a new expense.
    ```bash
    add
    ```
    Prompts you to enter the description and amount of the expense.

- `list`: Lists all expenses.
    ```bash
    list
    ```
    Shows all expenses with ID, date, description, and amount.

- `delete`: Delete an expense by ID.
    ```bash
    delete
    ```
    Prompts you to enter the ID of the expense you wish to delete.

- `summary`: View the total expenses, or filter by month.
    ```bash
    summary
    ```
    Optionally, enter a month (1-12) to filter the summary by month.

- `exit`: Exit the program.
    ```bash
    exit
    ```
    Ends the interactive session.

## Setup

### Prerequisites

- Java 8 or higher installed.

### Running the Application

1. **Clone or Download the repository**.

2. **Compile the Java file**:
   ```bash
   javac ExpenseTracker.java
   ```

3. **Run the application**:
   ```bash
   java ExpenseTracker
   ```

### Example Usage

```bash
Enter command (add/list/delete/summary/exit): add
Enter description: Coffee
Enter amount: 5
Expense added successfully (ID: 1)

Enter command (add/list/delete/summary/exit): list
ID  Date       Description          Amount
1   2024-01-13 Coffee               $5.00

Enter command (add/list/delete/summary/exit): summary
Enter month (1-12) for summary or press Enter for all: 
Total expenses: $5.00

Enter command (add/list/delete/summary/exit): exit
```

## File Storage

Expenses are stored in a file called `expenses.json` in the same directory. Each expense entry is stored in a comma-separated format, with the following fields:
- **ID**: A unique identifier for the expense.
- **Description**: A brief description of the expense.
- **Amount**: The amount spent.
- **Date**: The date the expense was added.

## License

This project is open source and available under the MIT License.

---

This README should give users a clear understanding of how to use the application and set it up. Let me know if you need any changes or additional information!

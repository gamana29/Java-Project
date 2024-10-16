import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ExpenseTracker {
    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, Expense> expenses = new HashMap<>();

    public static void main(String[] args) {
        // Sample user creation
        createUser("sample_user", "sample_password");

        // Dynamic user authentication
        String username = JOptionPane.showInputDialog(null, "Create username:");
        String password = JOptionPane.showInputDialog(null, "Create password:");
        createUser(username, password);

        // Sample expense addition for demonstration
        addExpense("sample_user");

        // Dynamic expense addition for the authenticated user
        while (true) {
            String userUsername = JOptionPane.showInputDialog(null, "Enter your username:");
            String userPassword = JOptionPane.showInputDialog(null, "Enter your password:");
            if (authenticateUser(userUsername, userPassword)) {
                JOptionPane.showMessageDialog(null, "User authenticated successfully.");

                // Prompt user to enter expense details
                String description = JOptionPane.showInputDialog(null, "Enter expense description:");
                double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter expense amount:"));
                String date = JOptionPane.showInputDialog(null, "Enter expense date (YYYY-MM-DD):");
                String category = JOptionPane.showInputDialog(null, "Enter expense category:");
                Expense expense = new Expense(description, amount, date, category);

                // Add the expense to the expenses HashMap
                expenses.put(userUsername, expense);
                JOptionPane.showMessageDialog(null, "Expense added successfully.");

                // Display the expense report for the authenticated user
                generateExpenseReport(userUsername);
            } else {
                JOptionPane.showMessageDialog(null, "Authentication failed. Please try again.");
            }
        }
    }

    private static void createUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    private static boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    private static void addExpense(String username) {
        // Sample expense addition for demonstration
        Expense expense = new Expense("Groceries", 50.0, "2024-04-06", "Food");
        expenses.put(username, expense);
    }

    private static void generateExpenseReport(String username) {
        Expense expense = expenses.get(username);
        if (expense != null) {
            JOptionPane.showMessageDialog(null, "Expense Report:\n" +
                    "Description: " + expense.getDescription() +
                    "\nAmount: " + expense.getAmount() +
                    "\nDate: " + expense.getDate() +
                    "\nCategory: " + expense.getCategory());
        } else {
            JOptionPane.showMessageDialog(null, "No expense found for this user.");
        }
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Expense {
    private String description;
    private double amount;
    private String date;
    private String category;

    public Expense(String description, double amount, String date, String category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }
}

class CustomDialogExample {
    public static void main(String[] args) {
        // Create a custom dialog with colored background
        JDialog dialog = new JDialog();
        dialog.setSize(300, 200);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(Color.BLUE); // Set background color

        // Add sparkles animation (optional)
        JLabel sparklesLabel = new JLabel("✨ Sparkles ✨");
        sparklesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dialog.add(sparklesLabel, BorderLayout.CENTER);

        // Add OK button to close the dialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Close the dialog on button click
            }
        });
        dialog.add(okButton, BorderLayout.SOUTH);

        // Set dialog visibility
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null); // Center the dialog on screen
        dialog.setVisible(true);
    }
}

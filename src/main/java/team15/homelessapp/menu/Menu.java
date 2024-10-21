package team15.homelessapp.menu;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import team15.homelessapp.util.HibernateUtil;
import team15.homelessapp.model.ServiceCategory;

public class Menu {

    private boolean isDatabaseSetup = false;

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!isDatabaseSetup) {
                System.out.println("1. Setup Database");
                System.out.println("2. Exit");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        setupDatabase();  // Call method to setup database
                        break;
                    case 2:
                        HibernateUtil.shutdown();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("1. Create Service Category");
                System.out.println("2. View All Service Categories");
                System.out.println("3. Update Service Category");
                System.out.println("4. Delete Service Category");
                System.out.println("5. Exit");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createServiceCategory();
                        break;
                    case 2:
                        viewAllServiceCategories();
                        break;
                    case 3:
                        updateServiceCategory();
                        break;
                    case 4:
                        deleteServiceCategory();
                        break;
                    case 5:
                        HibernateUtil.shutdown();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    // Method to setup the database
    public void setupDatabase() {
        System.out.println("Setting up the database...");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        System.out.println("Database setup complete!");
        isDatabaseSetup = true;
    }

    // Create Service Category
    public void createServiceCategory() {
        Scanner scanner = new Scanner(System.in);
        String categoryName = "";
        String categoryDescription = "";

        while (categoryName.isEmpty()) {
            System.out.print("Enter Category Name (cannot be empty): ");
            categoryName = scanner.nextLine().trim();
            if (categoryName.isEmpty()) {
                System.out.println("Error: Category name cannot be empty. Please try again.");
            }
        }

        while (categoryDescription.isEmpty()) {
            System.out.print("Enter Category Description (cannot be empty): ");
            categoryDescription = scanner.nextLine().trim();
            if (categoryDescription.isEmpty()) {
                System.out.println("Error: Category description cannot be empty. Please try again.");
            }
        }

        ServiceCategory category = new ServiceCategory();
        category.setCategory_name(categoryName);
        category.setCategory_description(categoryDescription);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(category);
        tx.commit();
        session.close();
        System.out.println("Service Category Created!");
    }

    // View all Service Categories
    public void viewAllServiceCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ServiceCategory> categories = session.createQuery("from ServiceCategory", ServiceCategory.class).list();
        session.close();

        if (categories.isEmpty()) {
            System.out.println("No Service Categories found.");
        } else {
            System.out.println("Service Categories:");
            for (ServiceCategory category : categories) {
                System.out.println("ID: " + category.getCategory_ID() + ", Name: " + category.getCategory_name() + ", Description: " + category.getCategory_description());
            }
        }
    }

    // Update an existing Service Category
    public void updateServiceCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Category ID to update: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Session session = HibernateUtil.getSessionFactory().openSession();
        ServiceCategory category = session.get(ServiceCategory.class, categoryId);

        if (category == null) {
            System.out.println("Service Category with ID " + categoryId + " not found.");
            session.close();
            return;
        }

        System.out.print("Enter new Category Name (current: " + category.getCategory_name() + "): ");
        String newName = scanner.nextLine();
        System.out.print("Enter new Category Description (current: " + category.getCategory_description() + "): ");
        String newDescription = scanner.nextLine();

        category.setCategory_name(newName);
        category.setCategory_description(newDescription);

        Transaction tx = session.beginTransaction();
        session.update(category);
        tx.commit();
        session.close();
        System.out.println("Service Category Updated!");
    }

    // Delete a Service Category
    public void deleteServiceCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Category ID to delete: ");
        int categoryId = scanner.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        ServiceCategory category = session.get(ServiceCategory.class, categoryId);

        if (category == null) {
            System.out.println("Service Category with ID " + categoryId + " not found.");
            session.close();
            return;
        }

        Transaction tx = session.beginTransaction();
        session.delete(category);
        tx.commit();
        session.close();
        System.out.println("Service Category Deleted!");
    }
}

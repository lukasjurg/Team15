package team15.homelessapp.menu;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import team15.homelessapp.util.HibernateUtil;
import team15.homelessapp.model.Service;
import team15.homelessapp.model.City;
import team15.homelessapp.model.ServiceCategory;

public class Menu {

    private boolean isDatabaseSetup = false;  // Flag to track if database setup is complete

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!isDatabaseSetup) {
                System.out.println("1. Setup Database");
                System.out.println("2. Exit");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();

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
                System.out.println("5. Create Service");
                System.out.println("6. View All Services");
                System.out.println("7. Update Service");
                System.out.println("8. Delete Service");
                System.out.println("9. Create City");
                System.out.println("10. Exit");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();

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
                        createService();
                        break;
                    case 6:
                        viewAllServices();
                        break;
                    case 7:
                        updateService();
                        break;
                    case 8:
                        deleteService();
                        break;
                    case 9:
                        createCity();
                        break;
                    case 10:
                        HibernateUtil.shutdown();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    // -------------------- Database Setup --------------------
    public void setupDatabase() {
        System.out.println("Setting up the database...");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        System.out.println("Database setup complete!");
        isDatabaseSetup = true;  // Mark database as setup
    }

    // Service Category CRUD

    public void createServiceCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Service Category Name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter Service Category Description: ");
        String categoryDescription = scanner.nextLine();

        ServiceCategory category = new ServiceCategory();
        category.setCategory_name(categoryName);
        category.setCategory_description(categoryDescription);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(category);
        tx.commit();
        session.close();
        System.out.println("Service Category Created Successfully!");
    }

    public void viewAllServiceCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ServiceCategory> categories = session.createQuery("from ServiceCategory", ServiceCategory.class).list();
        session.close();

        if (categories.isEmpty()) {
            System.out.println("No Service Categories found.");
        } else {
            System.out.println("Service Categories:");
            for (ServiceCategory category : categories) {
                System.out.println("ID: " + category.getCategory_ID() + ", Name: " + category.getCategory_name() +
                        ", Description: " + category.getCategory_description());
            }
        }
    }

    public void updateServiceCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Service Category ID to update: ");
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
        System.out.println("Service Category Updated Successfully!");
    }

    public void deleteServiceCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Service Category ID to delete: ");
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
        System.out.println("Service Category Deleted Successfully!");
    }

    // Service CRUD Operations

    public void createService() {
        Scanner scanner = new Scanner(System.in);

        List<City> cities = getAllCities();
        if (cities.isEmpty()) {
            System.out.println("No cities available. Please create a city first.");
            return;
        }
        System.out.println("Available Cities:");
        for (City city : cities) {
            System.out.println("ID: " + city.getCity_ID() + ", Name: " + city.getCity_name());
        }
        System.out.print("Select City ID: ");
        int cityId = scanner.nextInt();
        scanner.nextLine();

        City selectedCity = getCityById(cityId);
        if (selectedCity == null) {
            System.out.println("Invalid City ID.");
            return;
        }

        List<ServiceCategory> categories = getAllServiceCategories();
        if (categories.isEmpty()) {
            System.out.println("No Service Categories available. Please create a service category first.");
            return;
        }
        System.out.println("Available Service Categories:");
        for (ServiceCategory category : categories) {
            System.out.println("ID: " + category.getCategory_ID() + ", Name: " + category.getCategory_name());
        }
        System.out.print("Select Service Category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        ServiceCategory selectedCategory = getServiceCategoryById(categoryId);
        if (selectedCategory == null) {
            System.out.println("Invalid Service Category ID.");
            return;
        }

        // Create Service
        System.out.print("Enter Service Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Service Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Service Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter Operating Hours: ");
        String operatingHours = scanner.nextLine();

        Service service = new Service();
        service.setName(name);
        service.setAddress(address);
        service.setContact_number(contactNumber);
        service.setOperating_hours(operatingHours);
        service.setCity(selectedCity);
        service.setCategory(selectedCategory);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(service);
        tx.commit();
        session.close();
        System.out.println("Service Created Successfully!");
    }

    public void viewAllServices() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Service> services = session.createQuery("from Service", Service.class).list();
        session.close();

        if (services.isEmpty()) {
            System.out.println("No services found.");
        } else {
            System.out.println("Services:");
            for (Service service : services) {
                System.out.println("ID: " + service.getService_ID() + ", Name: " + service.getName() +
                        ", Address: " + service.getAddress() + ", City: " + service.getCity().getCity_name() +
                        ", Category: " + service.getCategory().getCategory_name());
            }
        }
    }

    public void updateService() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Service ID to update: ");
        int serviceId = scanner.nextInt();
        scanner.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Service service = session.get(Service.class, serviceId);

        if (service == null) {
            System.out.println("Service with ID " + serviceId + " not found.");
            session.close();
            return;
        }

        System.out.print("Enter new Service Name (current: " + service.getName() + "): ");
        String newName = scanner.nextLine();
        System.out.print("Enter new Address (current: " + service.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        System.out.print("Enter new Contact Number (current: " + service.getContact_number() + "): ");
        String newContact = scanner.nextLine();
        System.out.print("Enter new Operating Hours (current: " + service.getOperating_hours() + "): ");
        String newHours = scanner.nextLine();

        service.setName(newName);
        service.setAddress(newAddress);
        service.setContact_number(newContact);
        service.setOperating_hours(newHours);

        Transaction tx = session.beginTransaction();
        session.update(service);
        tx.commit();
        session.close();
        System.out.println("Service Updated Successfully!");
    }

    public void deleteService() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Service ID to delete: ");
        int serviceId = scanner.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Service service = session.get(Service.class, serviceId);

        if (service == null) {
            System.out.println("Service with ID " + serviceId + " not found.");
            session.close();
            return;
        }

        Transaction tx = session.beginTransaction();
        session.delete(service);
        tx.commit();
        session.close();
        System.out.println("Service Deleted Successfully!");
    }

    // City Creation
    public void createCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter City Name: ");
        String cityName = scanner.nextLine();

        City city = new City();
        city.setCity_name(cityName);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(city);
        tx.commit();
        session.close();
        System.out.println("City Created Successfully!");
    }

    // Helper Methods

    private List<City> getAllCities() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<City> cities = session.createQuery("from City", City.class).list();
        session.close();
        return cities;
    }

    private City getCityById(int cityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        City city = session.get(City.class, cityId);
        session.close();
        return city;
    }

    private List<ServiceCategory> getAllServiceCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ServiceCategory> categories = session.createQuery("from ServiceCategory", ServiceCategory.class).list();
        session.close();
        return categories;
    }

    private ServiceCategory getServiceCategoryById(int categoryId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ServiceCategory category = session.get(ServiceCategory.class, categoryId);
        session.close();
        return category;
    }
}

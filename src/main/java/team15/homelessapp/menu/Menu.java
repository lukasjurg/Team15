package team15.homelessapp.menu;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import team15.homelessapp.model.*;
import team15.homelessapp.util.HibernateUtil;

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

                switch (choice) {
                    case 1:
                        setupDatabase();
                        break;
                    case 2:
                        HibernateUtil.shutdown();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Select a category:");
                System.out.println("1. Service CRUD Operations");
                System.out.println("2. Service Category CRUD Operations");
                System.out.println("3. City CRUD Operations");
                System.out.println("4. User CRUD Operations");
                System.out.println("5. Role CRUD Operations");
                System.out.println("6. Feedback CRUD Operations");
                System.out.println("7. Exit");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        showServiceOperations(scanner);
                        break;
                    case 2:
                        showServiceCategoryOperations(scanner);
                        break;
                    case 3:
                        showCityOperations(scanner);
                        break;
                    case 4:
                        showUserOperations(scanner);
                        break;
                    case 5:
                        showRoleOperations(scanner);
                        break;
                    case 6:
                        showFeedbackOperations(scanner);
                        break;
                    case 7:
                        HibernateUtil.shutdown();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }



            }
        }
    }

    private void showServiceOperations(Scanner scanner) {
        System.out.println("Service CRUD Operations:");
        System.out.println("1. Create Service");
        System.out.println("2. View All Services");
        System.out.println("3. Update Service");
        System.out.println("4. Delete Service");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createService();
                break;
            case 2:
                viewAllServices();
                break;
            case 3:
                updateService();
                break;
            case 4:
                deleteService();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void showServiceCategoryOperations(Scanner scanner) {
        System.out.println("Service Category CRUD Operations:");
        System.out.println("1. Create Service Category");
        System.out.println("2. View All Service Categories");
        System.out.println("3. Update Service Category");
        System.out.println("4. Delete Service Category");
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
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void showCityOperations(Scanner scanner) {
        System.out.println("City CRUD Operations:");
        System.out.println("1. Create City");
        System.out.println("2. View All Cities");
        System.out.println("3. Update City");
        System.out.println("4. Delete City");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createCity();
                break;
            case 2:
                viewAllCities();
                break;
            case 3:
                updateCity();
                break;
            case 4:
                deleteCity();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void showUserOperations(Scanner scanner) {
        System.out.println("User CRUD Operations:");
        System.out.println("1. Create User");
        System.out.println("2. View All Users");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createUser();
                break;
            case 2:
                viewAllUsers();
                break;
            case 3:
                updateUser();
                break;
            case 4:
                deleteUser();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void showRoleOperations(Scanner scanner) {
        System.out.println("Role CRUD Operations:");
        System.out.println("1. Create Role");
        System.out.println("2. View All Roles");
        System.out.println("3. Update Role");
        System.out.println("4. Delete Role");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createRole();
                break;
            case 2:
                viewAllRoles();
                break;
            case 3:
                updateRole();
                break;
            case 4:
                deleteRole();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void showFeedbackOperations(Scanner scanner) {
        System.out.println("Feedback CRUD Operations:");
        System.out.println("1. Create Feedback");
        System.out.println("2. View All Feedback");
        System.out.println("3. Update Feedback");
        System.out.println("4. Delete Feedback");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createFeedback();
                break;
            case 2:
                viewAllFeedback();
                break;
            case 3:
                updateFeedback();
                break;
            case 4:
                deleteFeedback();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    public void setupDatabase() {
        System.out.println("Setting up the database...");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        System.out.println("Database setup complete!");
        isDatabaseSetup = true;
    }

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
        scanner.nextLine();

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

    public void viewAllCities() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<City> cities = session.createQuery("from City", City.class).list();
        session.close();

        if (cities.isEmpty()) {
            System.out.println("No cities found.");
        } else {
            System.out.println("Cities:");
            for (City city : cities) {
                System.out.println("ID: " + city.getCity_ID() + ", Name: " + city.getCity_name());
            }
        }
    }

    public void updateCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter City ID to update: ");
        int cityId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Session session = HibernateUtil.getSessionFactory().openSession();
        City city = session.get(City.class, cityId);

        if (city == null) {
            System.out.println("City with ID " + cityId + " not found.");
            session.close();
            return;
        }

        System.out.print("Enter new City Name (current: " + city.getCity_name() + "): ");
        String newName = scanner.nextLine();

        city.setCity_name(newName);

        Transaction tx = session.beginTransaction();
        session.update(city);
        tx.commit();
        session.close();
        System.out.println("City Updated Successfully!");
    }

    public void deleteCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter City ID to delete: ");
        int cityId = scanner.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        City city = session.get(City.class, cityId);

        if (city == null) {
            System.out.println("City with ID " + cityId + " not found.");
            session.close();
            return;
        }

        Transaction tx = session.beginTransaction();
        session.delete(city);
        tx.commit();
        session.close();
        System.out.println("City Deleted Successfully!");
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        List<UserRole> roles = getAllRoles();
        if (roles.isEmpty()) {
            System.out.println("No roles available. Please create a role first.");
            return;
        }
        System.out.println("Available Roles:");
        for (UserRole role : roles) {
            System.out.println("ID: " + role.getRole_ID() + ", Name: " + role.getRole_name());
        }
        System.out.print("Select Role ID: ");
        int roleId = scanner.nextInt();
        scanner.nextLine();

        UserRole selectedRole = getRoleById(roleId);
        if (selectedRole == null) {
            System.out.println("Invalid Role ID.");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(selectedRole);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        System.out.println("User Created Successfully!");
    }

    public void viewAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("from User", User.class).list();
        session.close();

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users:");
            for (User user : users) {
                System.out.println("ID: " + user.getUser_ID() + ", Username: " + user.getUsername() +
                        ", Email: " + user.getEmail() + ", Role: " + user.getRole().getRole_name());
            }
        }
    }

    public void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userId);

        if (user == null) {
            System.out.println("User with ID " + userId + " not found.");
            session.close();
            return;
        }

        System.out.print("Enter new Username (current: " + user.getUsername() + "): ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new Password (current: " + user.getPassword() + "): ");
        String newPassword = scanner.nextLine();
        System.out.print("Enter new Email (current: " + user.getEmail() + "): ");
        String newEmail = scanner.nextLine();

        List<UserRole> roles = getAllRoles();
        System.out.println("Available Roles:");
        for (UserRole role : roles) {
            System.out.println("ID: " + role.getRole_ID() + ", Name: " + role.getRole_name());
        }
        System.out.print("Select Role ID (current: " + user.getRole().getRole_name() + "): ");
        int roleId = scanner.nextInt();
        scanner.nextLine();

        UserRole selectedRole = getRoleById(roleId);
        if (selectedRole == null) {
            System.out.println("Invalid Role ID.");
            return;
        }

        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);
        user.setRole(selectedRole);

        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
        System.out.println("User Updated Successfully!");
    }

    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userId);

        if (user == null) {
            System.out.println("User with ID " + userId + " not found.");
            session.close();
            return;
        }

        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
        session.close();
        System.out.println("User Deleted Successfully!");
    }

    public void createRole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Role Name: ");
        String roleName = scanner.nextLine();

        UserRole role = new UserRole();
        role.setRole_name(roleName);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(role);
        tx.commit();
        session.close();
        System.out.println("Role Created Successfully!");
    }

    public void viewAllRoles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<UserRole> roles = session.createQuery("from UserRole", UserRole.class).list();
        session.close();

        if (roles.isEmpty()) {
            System.out.println("No roles found.");
        } else {
            System.out.println("Roles:");
            for (UserRole role : roles) {
                System.out.println("ID: " + role.getRole_ID() + ", Name: " + role.getRole_name());
            }
        }
    }

    public void updateRole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Role ID to update: ");
        int roleId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Session session = HibernateUtil.getSessionFactory().openSession();
        UserRole role = session.get(UserRole.class, roleId);

        if (role == null) {
            System.out.println("Role with ID " + roleId + " not found.");
            session.close();
            return;
        }

        System.out.print("Enter new Role Name (current: " + role.getRole_name() + "): ");
        String newRoleName = scanner.nextLine();

        role.setRole_name(newRoleName);

        Transaction tx = session.beginTransaction();
        session.update(role);
        tx.commit();
        session.close();
        System.out.println("Role Updated Successfully!");
    }

    public void deleteRole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Role ID to delete: ");
        int roleId = scanner.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        UserRole role = session.get(UserRole.class, roleId);

        if (role == null) {
            System.out.println("Role with ID " + roleId + " not found.");
            session.close();
            return;
        }

        Transaction tx = session.beginTransaction();
        session.delete(role);
        tx.commit();
        session.close();
        System.out.println("Role Deleted Successfully!");
    }

    public void createFeedback() {
        Scanner scanner = new Scanner(System.in);

        // Select User
        List<User> users = getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users available. Please create a user first.");
            return;
        }
        System.out.println("Available Users:");
        for (User user : users) {
            System.out.println("ID: " + user.getUser_ID() + ", Username: " + user.getUsername());
        }
        System.out.print("Select User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User selectedUser = getUserById(userId);
        if (selectedUser == null) {
            System.out.println("Invalid User ID.");
            return;
        }

        // Select Service
        List<Service> services = getAllServices();
        if (services.isEmpty()) {
            System.out.println("No services available. Please create a service first.");
            return;
        }
        System.out.println("Available Services:");
        for (Service service : services) {
            System.out.println("ID: " + service.getService_ID() + ", Name: " + service.getName());
        }
        System.out.print("Select Service ID: ");
        int serviceId = scanner.nextInt();
        scanner.nextLine();

        Service selectedService = getServiceById(serviceId);
        if (selectedService == null) {
            System.out.println("Invalid Service ID.");
            return;
        }

        // Enter Rating
        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        // Create Feedback
        Feedback feedback = new Feedback();
        feedback.setUser(selectedUser);
        feedback.setService(selectedService);
        feedback.setRating(rating);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(feedback);
        tx.commit();
        session.close();
        System.out.println("Feedback Created Successfully!");
    }

    public void viewAllFeedback() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Feedback> feedbackList = session.createQuery("from Feedback", Feedback.class).list();
        session.close();

        if (feedbackList.isEmpty()) {
            System.out.println("No feedback found.");
        } else {
            System.out.println("Feedback Entries:");
            for (Feedback feedback : feedbackList) {
                System.out.println("ID: " + feedback.getFeedback_ID() +
                        ", User: " + feedback.getUser().getUsername() +
                        ", Service: " + feedback.getService().getName() +
                        ", Rating: " + feedback.getRating());
            }
        }
    }

    public void updateFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Feedback ID to update: ");
        int feedbackId = scanner.nextInt();
        scanner.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Feedback feedback = session.get(Feedback.class, feedbackId);

        if (feedback == null) {
            System.out.println("Feedback with ID " + feedbackId + " not found.");
            session.close();
            return;
        }

        System.out.print("Enter new Rating (1-5, current: " + feedback.getRating() + "): ");
        int newRating = scanner.nextInt();
        scanner.nextLine();

        feedback.setRating(newRating);

        Transaction tx = session.beginTransaction();
        session.update(feedback);
        tx.commit();
        session.close();
        System.out.println("Feedback Updated Successfully!");
    }

    public void deleteFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Feedback ID to delete: ");
        int feedbackId = scanner.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Feedback feedback = session.get(Feedback.class, feedbackId);

        if (feedback == null) {
            System.out.println("Feedback with ID " + feedbackId + " not found.");
            session.close();
            return;
        }

        Transaction tx = session.beginTransaction();
        session.delete(feedback);
        tx.commit();
        session.close();
        System.out.println("Feedback Deleted Successfully!");
    }


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

    private List<UserRole> getAllRoles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<UserRole> roles = session.createQuery("from UserRole", UserRole.class).list();
        session.close();
        return roles;
    }

    private UserRole getRoleById(int roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserRole role = session.get(UserRole.class, roleId);
        session.close();
        return role;
    }

    private List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("from User", User.class).list();
        session.close();
        return users;
    }

    private User getUserById(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userId);
        session.close();
        return user;
    }

    private Service getServiceById(int serviceId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Service service = session.get(Service.class, serviceId);
        session.close();
        return service;
    }

    private List<Service> getAllServices() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Service> services = session.createQuery("from Service", Service.class).list();
        session.close();
        return services;
    }
}

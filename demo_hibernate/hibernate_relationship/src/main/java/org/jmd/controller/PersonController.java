package org.jmd.controller;

import org.jmd.dao.aadhar_dao;
import org.jmd.dao.person_dao;
import org.jmd.model.Aadhar;
import org.jmd.model.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PersonController {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        new PersonController().run();
    }

    private void run() {
        System.out.println("---------------------------- Welcome to Aadhaar management system -----------------------------------\n");

        try (Scanner scanner = new Scanner(System.in);
             person_dao personDao = new person_dao();
             aadhar_dao aadharDao = new aadhar_dao()) {

            boolean running = true;
            while (running) {
                printMenu();
                int choice = readInt(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        createPerson(scanner, personDao);
                        break;
                    case 2:
                        listPersons(personDao);
                        break;
                    case 3:
                        viewPersonById(scanner, personDao);
                        break;
                    case 4:
                        updatePerson(scanner, personDao);
                        break;
                    case 5:
                        deletePerson(scanner, personDao);
                        break;
                    case 6:
                        createAadhar(scanner, aadharDao);
                        break;
                    case 7:
                        listAadhars(aadharDao);
                        break;
                    case 8:
                        viewAadharById(scanner, aadharDao);
                        break;
                    case 9:
                        updateAadhar(scanner, aadharDao);
                        break;
                    case 10:
                        deleteAadhar(scanner, aadharDao);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add Person with Aadhar");
        System.out.println("2. View All Persons");
        System.out.println("3. View Person By ID");
        System.out.println("4. Update Person");
        System.out.println("5. Delete Person");
        System.out.println("6. Add Aadhar");
        System.out.println("7. View All Aadhars");
        System.out.println("8. View Aadhar By Number");
        System.out.println("9. Update Aadhar");
        System.out.println("10. Delete Aadhar");
        System.out.println("0. Exit");
    }

    private void createPerson(Scanner scanner, person_dao personDao) {
        System.out.println("\n--- Add Person ---");
        String name = readRequired(scanner, "Enter name: ");
        String email = readRequired(scanner, "Enter email: ");
        String address = readRequired(scanner, "Enter address: ");
        LocalDateTime dob = readDateTime(scanner, "Enter date of birth (yyyy-MM-dd HH:mm:ss): ");

        Person person = new Person();
        person.setName(name);
        person.setEmail(email);

        Aadhar aadhar = new Aadhar();
        aadhar.setAddress(address);
        aadhar.setDob(dob);

        Person saved = personDao.savePerson(person, aadhar);
        System.out.println("Saved person with ID: " + saved.getId());
        if (saved.getAadhar() != null) {
            System.out.println("Generated Aadhar Number: " + saved.getAadhar().getAadharNumber());
        }
    }

    private void listPersons(person_dao personDao) {
        System.out.println("\n--- All Persons ---");
        List<Person> persons = personDao.getAllPersons();
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
            return;
        }
        for (Person person : persons) {
            printPerson(person);
            System.out.println();
        }
    }

    private void viewPersonById(Scanner scanner, person_dao personDao) {
        Long id = readLong(scanner, "Enter person ID: ");
        Person person = personDao.getPersonById(id);
        if (person == null) {
            System.out.println("Person not found.");
            return;
        }
        printPerson(person);
    }

    private void updatePerson(Scanner scanner, person_dao personDao) {
        Long id = readLong(scanner, "Enter person ID to update: ");
        Person existing = personDao.getPersonById(id);
        if (existing == null) {
            System.out.println("Person not found.");
            return;
        }

        System.out.println("Press Enter to keep the current value.");
        String name = readOptional(scanner, "Enter new name [" + existing.getName() + "]: ");
        String email = readOptional(scanner, "Enter new email [" + existing.getEmail() + "]: ");

        Person updatedPerson = new Person();
        updatedPerson.setName(isBlank(name) ? existing.getName() : name);
        updatedPerson.setEmail(isBlank(email) ? existing.getEmail() : email);

        Aadhar updatedAadhar = null;
        if (existing.getAadhar() != null) {
            String address = readOptional(scanner, "Enter new address [" + existing.getAadhar().getAddress() + "]: ");
            String dobInput = readOptional(scanner, "Enter new dob (yyyy-MM-dd HH:mm:ss) [" + formatDateTime(existing.getAadhar().getDob()) + "]: ");

            updatedAadhar = new Aadhar();
            updatedAadhar.setAadharNumber(existing.getAadhar().getAadharNumber());
            updatedAadhar.setAddress(isBlank(address) ? existing.getAadhar().getAddress() : address);
            updatedAadhar.setDob(isBlank(dobInput) ? existing.getAadhar().getDob() : parseDateTime(dobInput));
        }

        Person updated = personDao.updatePerson(id, updatedPerson, updatedAadhar);
        if (updated == null) {
            System.out.println("Update failed. Person not found.");
            return;
        }
        System.out.println("Person updated successfully.");
        printPerson(updated);
    }

    private void deletePerson(Scanner scanner, person_dao personDao) {
        Long id = readLong(scanner, "Enter person ID to delete: ");
        boolean deleted = personDao.deletePerson(id);
        System.out.println(deleted ? "Person deleted successfully." : "Person not found.");
    }

    private void createAadhar(Scanner scanner, aadhar_dao aadharDao) {
        System.out.println("\n--- Add Aadhar ---");
        long aadharNumber = readLong(scanner, "Enter aadhar number: ");
        String address = readRequired(scanner, "Enter address: ");
        LocalDateTime dob = readDateTime(scanner, "Enter date of birth (yyyy-MM-dd HH:mm:ss): ");

        Aadhar aadhar = new Aadhar();
        aadhar.setAadharNumber(aadharNumber);
        aadhar.setAddress(address);
        aadhar.setDob(dob);

        Aadhar saved = aadharDao.saveAadhar(aadhar);
        System.out.println("Saved Aadhar Number: " + saved.getAadharNumber());
    }

    private void listAadhars(aadhar_dao aadharDao) {
        System.out.println("\n--- All Aadhars ---");
        List<Aadhar> aadhars = aadharDao.getAllAadhars();
        if (aadhars.isEmpty()) {
            System.out.println("No aadhars found.");
            return;
        }
        for (Aadhar aadhar : aadhars) {
            printAadhar(aadhar);
            System.out.println();
        }
    }

    private void viewAadharById(Scanner scanner, aadhar_dao aadharDao) {
        Long id = readLong(scanner, "Enter aadhar number: ");
        Aadhar aadhar = aadharDao.getAadharById(id);
        if (aadhar == null) {
            System.out.println("Aadhar not found.");
            return;
        }
        printAadhar(aadhar);
    }

    private void updateAadhar(Scanner scanner, aadhar_dao aadharDao) {
        Long id = readLong(scanner, "Enter aadhar number to update: ");
        Aadhar existing = aadharDao.getAadharById(id);
        if (existing == null) {
            System.out.println("Aadhar not found.");
            return;
        }

        String address = readOptional(scanner, "Enter new address [" + existing.getAddress() + "]: ");
        String dobInput = readOptional(scanner, "Enter new dob (yyyy-MM-dd HH:mm:ss) [" + existing.getDob() + "]: ");

        Aadhar updated = new Aadhar();
        updated.setAddress(isBlank(address) ? existing.getAddress() : address);
        updated.setDob(isBlank(dobInput) ? existing.getDob() : parseDateTime(dobInput));

        Aadhar saved = aadharDao.updateAadhar(id, updated);
        if (saved == null) {
            System.out.println("Update failed. Aadhar not found.");
            return;
        }
        System.out.println("Aadhar updated successfully.");
        printAadhar(saved);
    }

    private void deleteAadhar(Scanner scanner, aadhar_dao aadharDao) {
        Long id = readLong(scanner, "Enter aadhar number to delete: ");
        boolean deleted = aadharDao.deleteAadhar(id);
        System.out.println(deleted ? "Aadhar deleted successfully." : "Aadhar not found.");
    }

    private void printPerson(Person person) {
        System.out.println("Person ID: " + person.getId());
        System.out.println("Name: " + person.getName());
        System.out.println("Email: " + person.getEmail());
        if (person.getAadhar() != null) {
            printAadhar(person.getAadhar());
        }
    }

    private void printAadhar(Aadhar aadhar) {
        System.out.println("Aadhar Number: " + aadhar.getAadharNumber());
        System.out.println("Address: " + aadhar.getAddress());
        System.out.println("DOB: " + aadhar.getDob());
    }

    private String readRequired(Scanner scanner, String prompt) {
        while (true) {
            String value = readOptional(scanner, prompt);
            if (!isBlank(value)) {
                return value;
            }
            System.out.println("This value is required.");
        }
    }

    private String readOptional(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readOptional(scanner, prompt));
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private Long readLong(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Long.parseLong(readOptional(scanner, prompt));
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid long number.");
            }
        }
    }

    private LocalDateTime readDateTime(Scanner scanner, String prompt) {
        while (true) {
            String input = readRequired(scanner, prompt);
            try {
                return parseDateTime(input);
            } catch (DateTimeParseException ex) {
                System.out.println("Please enter date/time in format yyyy-MM-dd HH:mm:ss");
            }
        }
    }

    private LocalDateTime parseDateTime(String input) {
        return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime == null ? "" : DATE_TIME_FORMATTER.format(dateTime);
    }
}

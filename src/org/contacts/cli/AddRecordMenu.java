package org.contacts.cli;

import org.contacts.contact.OrganizationContact;
import org.contacts.contact.PersonContact;

class AddRecordMenu extends CommandLineMenu {

    protected AddRecordMenu() {
        commandLineMenuState = CommandLineMenuState.ADD;
    }

    @Override
    protected void show() {
        System.out.print("Enter the type (person, organization): ");
        String action = scanner.nextLine().toLowerCase();
        switch (action) {
            case "person":
                currentContact = new PersonContact();
                PersonContact personContact = (PersonContact) currentContact;
                System.out.print("Enter the name: ");
                personContact.setName(scanner.nextLine());
                System.out.print("Enter the surname: ");
                personContact.setSurname(scanner.nextLine());
                System.out.print("Enter the birth date: ");
                personContact.setBirthDate(scanner.nextLine());
                System.out.print("Enter the gender: ");
                personContact.setGender(scanner.nextLine());
                System.out.print("Enter the number: ");
                personContact.setNumber(scanner.nextLine());
                contactsManager.addContact(currentContact);
                commandLineMenuState = CommandLineMenuState.MENU;
                break;
            case "organization":
                currentContact = new OrganizationContact();
                OrganizationContact orgContact = (OrganizationContact) currentContact;
                System.out.print("Enter the organization name: ");
                orgContact.setName(scanner.nextLine());
                System.out.print("Enter the address: ");
                orgContact.setAddress(scanner.nextLine());
                System.out.print("Enter the number: ");
                orgContact.setNumber(scanner.nextLine());
                contactsManager.addContact(currentContact);
                commandLineMenuState = CommandLineMenuState.MENU;
                break;
        }
        System.out.println("Record added.");
        System.out.println();
    }
}

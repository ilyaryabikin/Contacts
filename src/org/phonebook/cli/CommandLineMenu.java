package org.phonebook.cli;

import org.phonebook.contact.Contact;
import org.phonebook.contact.ContactsManager;

import java.util.List;
import java.util.Scanner;

abstract class CommandLineMenu {
    protected static ContactsManager contactsManager = ContactsManager.getInstance();
    protected static Contact currentContact;
    protected static List<Contact> currentQueryList;
    protected static Scanner scanner;
    protected static CommandLineMenuState commandLineMenuState = CommandLineMenuState.MENU;

    protected abstract void show();

    protected void setCurrentContact(int index) {
        currentContact = contactsManager.getByIndex(index - 1);
    }

    protected void setCurrentContactFromQuery(int index) {
        currentContact = currentQueryList.get(index - 1);
    }

    protected CommandLineMenuState getState() {
        return commandLineMenuState;
    }

    protected void setScanner(Scanner scanner) {
        CommandLineMenu.scanner = scanner;
    }

    protected void removeCurrentContact() {
        contactsManager.deleteContact(currentContact);
    }

    protected void printListByQuery(String query) {
        currentQueryList = contactsManager.getByQuery(query);
        if (currentQueryList.size() == 0) {
            System.out.println("No matching results.");
        } else {
            System.out.printf("Found %d results:\n", currentQueryList.size());
            int counter = 1;
            for (Contact contact : currentQueryList) {
                System.out.println(counter + ". " + contact.getFullName());
                counter++;
            }
        }
    }

    protected void printList() {
        currentQueryList = contactsManager.getContacts();
        if (currentQueryList.size() == 0) {
            return;
        }
        int counter = 1;
        for (Contact contact : currentQueryList) {
            System.out.println(counter + ". " + contact.getFullName());
            counter++;
        }
    }
}

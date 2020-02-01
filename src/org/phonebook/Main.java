package org.phonebook;

import org.phonebook.cli.CommandLineMenuInvoker;
import org.phonebook.contact.ContactsManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if (hasArgument(args)) {
            ContactsManager.setStorageFile(args[0]);
            System.out.println("Contacts will be stored in " + args[0] + ". If file doesn't exist it will be created.");
        } else {
            System.out.println("Contacts will not be saved. To save or load contacts from file run program with filename argument.");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        CommandLineMenuInvoker commandLineMenuInvoker = new CommandLineMenuInvoker(scanner);
        commandLineMenuInvoker.invoke();
    }

    public static boolean hasArgument(String[] args) {
        if (args.length != 0) {
            return !args[0].isEmpty();
        } else {
            return false;
        }
    }
}

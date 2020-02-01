package org.contacts.cli;

import org.contacts.exception.IllegalFieldNameException;

class RecordMenu extends CommandLineMenu {

    protected RecordMenu() {
        commandLineMenuState = CommandLineMenuState.RECORD;
    }

    @Override
    protected void show() {
        System.out.print(commandLineMenuState + "Enter action (edit, delete, menu): ");
        String action = scanner.nextLine().toLowerCase();
        switch (action) {
            case "edit":
                System.out.print("Select a field (" + (String.join(", ", currentContact.getChangingFields().keySet())) + "): ");
                String key = scanner.nextLine().toLowerCase();
                System.out.print("Enter " + key + ": ");
                String newValue = scanner.nextLine();
                try {
                    currentContact.changeField(key, newValue);
                    contactsManager.updateContact(currentContact);
                    System.out.println("Saved.");
                } catch (IllegalFieldNameException e) {
                    e.getMessage();
                }
                System.out.println(currentContact);
                System.out.println();
                break;
            case "delete":
                removeCurrentContact();
                System.out.println("Deleted.");
                System.out.println();
                commandLineMenuState = CommandLineMenuState.MENU;
                break;
            case "menu":
                System.out.println();
                commandLineMenuState = CommandLineMenuState.MENU;
                break;
        }
    }
}

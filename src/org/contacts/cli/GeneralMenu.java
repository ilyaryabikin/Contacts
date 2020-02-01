package org.contacts.cli;

class GeneralMenu extends CommandLineMenu {

    protected GeneralMenu() {
        commandLineMenuState = CommandLineMenuState.MENU;
    }

    @Override
    protected void show() {
        System.out.print(commandLineMenuState + "Enter action (add, list, search, count, exit): ");
        String action = scanner.nextLine().toLowerCase();
        switch (action) {
            case "add":
                commandLineMenuState = CommandLineMenuState.ADD;
                break;
            case "count":
                System.out.printf("The Phone Book has %d records.\n", contactsManager.countContacts());
                System.out.println();
                break;
            case "search":
                System.out.print("Enter search query: ");
                printListByQuery(scanner.nextLine());
                System.out.println();
                commandLineMenuState = CommandLineMenuState.SEARCH;
                break;
            case "list":
                printList();
                if (currentQueryList.size() == 0) {
                    System.out.println("No records found. Try to add some.");
                    commandLineMenuState = CommandLineMenuState.MENU;
                } else {
                    commandLineMenuState = CommandLineMenuState.LIST;
                }
                System.out.println();
                break;
            case "exit":
                commandLineMenuState = CommandLineMenuState.EXIT;
                break;
        }
    }
}

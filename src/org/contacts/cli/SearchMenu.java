package org.contacts.cli;

class SearchMenu extends CommandLineMenu {

    protected SearchMenu() {
        commandLineMenuState = CommandLineMenuState.SEARCH;
    }

    @Override
    protected void show() {
        System.out.print(commandLineMenuState + "Enter action ([number], back, again): ");
        String action = scanner.nextLine().toLowerCase();
        switch (action) {
            case "again":
                System.out.print("Enter search query: ");
                printListByQuery(scanner.nextLine());
                System.out.println();
                break;
            case "back":
                System.out.println();
                commandLineMenuState = CommandLineMenuState.MENU;
                break;
            default:
                if (action.matches("\\d+")) {
                    setCurrentContactFromQuery(Integer.parseInt(action));
                    System.out.println(currentContact);
                    System.out.println();
                    commandLineMenuState = CommandLineMenuState.RECORD;
                }
        }
    }
}

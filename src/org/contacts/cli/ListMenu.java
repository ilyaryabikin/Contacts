package org.contacts.cli;

class ListMenu extends CommandLineMenu {

    protected ListMenu() {
        commandLineMenuState = CommandLineMenuState.LIST;
    }

    @Override
    protected void show() {
        System.out.print(commandLineMenuState + "Enter action ([number], back): ");
        String action = scanner.nextLine().toLowerCase();
        switch (action) {
            case "back":
                System.out.println();
                commandLineMenuState = CommandLineMenuState.MENU;
                break;
            default:
                if (action.matches("\\d+")) {
                    setCurrentContact(Integer.parseInt(action));
                    System.out.println(currentContact);
                    System.out.println();
                    commandLineMenuState = CommandLineMenuState.RECORD;
                }
        }
    }
}

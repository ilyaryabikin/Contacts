package org.phonebook.cli;

import java.util.Scanner;

public class CommandLineMenuInvoker {
    private CommandLineMenu commandLineMenu;

    public CommandLineMenuInvoker(Scanner scanner) {
        commandLineMenu = new GeneralMenu();
        commandLineMenu.setScanner(scanner);
    }

    public void invoke() {
        do {
            commandLineMenu.show();
            switch (commandLineMenu.getState()) {
                case MENU:
                    commandLineMenu = new GeneralMenu();
                    break;
                case ADD:
                    commandLineMenu = new AddRecordMenu();
                    break;
                case LIST:
                    commandLineMenu = new ListMenu();
                    break;
                case RECORD:
                    commandLineMenu = new RecordMenu();
                    break;
                case SEARCH:
                    commandLineMenu = new SearchMenu();
                    break;
                case EXIT:
                    break;
            }
        } while (!commandLineMenu.getState().equals(CommandLineMenuState.EXIT));
    }
}

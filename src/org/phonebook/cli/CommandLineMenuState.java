package org.phonebook.cli;

enum CommandLineMenuState {
    MENU("[menu] "),
    SEARCH("[search] "),
    RECORD("[record] "),
    LIST("[list] "),
    ADD("[add] "),
    EXIT("[exit] ");

    private String statePrefix;

    CommandLineMenuState(String statePrefix) {
        this.statePrefix = statePrefix;
    }


    @Override
    public String toString() {
        return statePrefix;
    }
}

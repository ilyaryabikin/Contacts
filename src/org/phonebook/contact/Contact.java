package org.phonebook.contact;

import org.phonebook.exception.IllegalFieldNameException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    protected static final String NO_DATA = "[no data]";

    protected String number = "";
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeEdited;

    protected Contact() {
        this.timeCreated = LocalDateTime.now();
        this.timeEdited = timeCreated;
    }

    public String getNumber() {
        if (number != null) {
            if (!number.isEmpty()) {
                return number;
            }
        }
        return NO_DATA;
    }

    public void setNumber(String number) {
        if (checkNumber(number)) {
            this.number = number;
        } else {
            this.number = "";
        }
        timeEdited = LocalDateTime.now();
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeEdited() {
        return timeEdited;
    }

    protected LocalDateTime setTimeEdited() {
        return LocalDateTime.now();
    }

    private static boolean checkNumber(String number) {
        Pattern phoneNumberPattern = Pattern.compile("\\+?\\w?[\\s-]?(\\(\\w{2,}\\)|\\w{2,}[\\s-]\\(\\w{2,}\\)|\\w{2,})?([\\s-][\\w]{2,})*", Pattern.CASE_INSENSITIVE);
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(number);
        return phoneNumberMatcher.matches();
    }

    public abstract String getFullName();

    public abstract Map<String, String> getChangingFields();

    public abstract void changeField(String field, String value) throws IllegalFieldNameException;

    public abstract String getFieldValue(String field) throws IllegalFieldNameException;

    @Override
    public String toString() {
        return String.format("Number: %s\nTime created: %s\nTime last edit: %s",
                getNumber(), timeCreated, timeEdited);
    }
}

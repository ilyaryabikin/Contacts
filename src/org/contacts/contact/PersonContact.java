package org.contacts.contact;

import org.contacts.exception.IllegalFieldNameException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PersonContact extends Contact {

    private String name;
    private String surname;
    private String gender;
    private LocalDate birthDate;

    public String getName() {
        if (name != null) {
            if (!name.isEmpty()) {
                return name;
            }
        }
        return NO_DATA;
    }

    public void setName(String name) {
        this.name = name;
        setTimeEdited();
    }

    public String getSurname() {
        if (surname != null) {
            if (!surname.isEmpty()) {
                return surname;
            }
        }
        return NO_DATA;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        setTimeEdited();
    }

    public String getGender() {
        if (gender != null) {
            if (!gender.isEmpty()) {
                return gender;
            }
        }
        return NO_DATA;
    }

    public void setGender(String gender) {
        this.gender = gender;
        setTimeEdited();
    }

    public String getBirthDate() {
        if (birthDate != null) {
            return birthDate.toString();
        }
        return NO_DATA;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        setTimeEdited();
    }

    public void setBirthDate(String string) {
        try {
            birthDate = LocalDate.parse(string);
            setTimeEdited();
        } catch (DateTimeParseException e) {
            e.getMessage();
        }
    }

    @Override
    public String getFullName() {
        return name + " " + surname;
    }

    @Override
    public Map<String, String> getChangingFields() {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("name", getName());
        fields.put("surname", getSurname());
        fields.put("birth", getBirthDate());
        fields.put("gender", getGender());
        fields.put("number", getNumber());
        return fields;
    }

    @Override
    public void changeField(String field, String value) throws IllegalFieldNameException {
        Map<String, String> fields = getChangingFields();
        if (!fields.containsKey(field)) {
            throw new IllegalFieldNameException("This field doesn't exist in the class");
        }
        switch (field.toLowerCase()) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "gender":
                setGender(value);
                break;
            case "birthdate":
                setBirthDate(value);
                break;
            case "number":
                setNumber(value);
                break;
        }
    }

    @Override
    public String getFieldValue(String field) throws IllegalFieldNameException {
        Map<String, String> fields = getChangingFields();
        if (!fields.containsKey(field)) {
            throw new IllegalFieldNameException("This field doesn't exist in the class");
        }
        return fields.get(field);
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\n%s",
                getName(), getSurname(), getBirthDate(), getGender(), super.toString());
    }
}

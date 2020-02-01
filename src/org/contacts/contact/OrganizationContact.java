package org.contacts.contact;

import org.contacts.exception.IllegalFieldNameException;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrganizationContact extends Contact {
    private String name;
    private String address;

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
    }

    public String getAddress() {
        if (address != null) {
            if (!address.isEmpty()) {
                return address;
            }
        }
        return NO_DATA;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public Map<String, String> getChangingFields() {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("name", getName());
        fields.put("address", getAddress());
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
            case "address":
                setAddress(value);
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
        return String.format("Organization name: %s\nAddress: %s\n%s",
                getName(), getAddress(), super.toString());
    }
}

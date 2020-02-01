package org.contacts.contact;

import org.contacts.util.SerializationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactsManager {
    private static ContactsManager instance;
    private static String fileName;
    private static StorageMode storageMode = StorageMode.TEMPORARY;
    private List<Contact> contacts;

    private ContactsManager() throws IOException, ClassNotFoundException {
        if (storageMode.equals(StorageMode.TEMPORARY)) {
            contacts = new ArrayList<>();
        } else {
            contacts = (ArrayList<Contact>) SerializationUtils.deserialize(fileName);
        }
    }

    public static void setStorageFile(String fileName) {
        ContactsManager.storageMode = StorageMode.SERIALIZATION;
        ContactsManager.fileName = fileName;
    }

    public static ContactsManager getInstance() {
        if (instance == null) {
            try {
                instance = new ContactsManager();
            } catch (IOException | ClassNotFoundException e) {
                e.getMessage();
                System.exit(-1);
            }
        }
        return instance;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public int countContacts() {
        return contacts.size();
    }

    public List<Contact> getByQuery(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        List<Contact> querySublist = new ArrayList<>();
        Matcher matcher;
        for (Contact contact : contacts) {
            matcher = pattern.matcher(contact.toString());
            if (matcher.find()) {
                querySublist.add(contact);
            }
        }
        querySublist.sort(Comparator.comparing(Contact::getFullName));
        return querySublist;
    }

    public Contact getByIndex(int index) {
        return contacts.get(index);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        if (storageMode.equals(StorageMode.SERIALIZATION)) {
            try {
                SerializationUtils.serialize(contacts, fileName);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    public void updateContact(Contact contact) {
        contacts.set(contacts.indexOf(contact), contact);
        if (storageMode.equals(StorageMode.SERIALIZATION)) {
            try {
                SerializationUtils.serialize(contacts, fileName);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
        if (storageMode.equals(StorageMode.SERIALIZATION)) {
            try {
                SerializationUtils.serialize(contacts, fileName);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}

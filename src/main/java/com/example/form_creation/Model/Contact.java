package com.example.form_creation.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Objects;

@Component
public class Contact {
    String Name;
    String number;
    String  Email, Message;

    public Contact() {
    }

    public Contact(String Name, String number, String Email, String Message) {
        this.Name = Name;
        this.number = number;
        this.Email = Email;
        this.Message = Message;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Contact Name(String Name) {
        setName(Name);
        return this;
    }

    public Contact number(String number) {
        setNumber(number);
        return this;
    }

    public Contact Email(String Email) {
        setEmail(Email);
        return this;
    }

    public Contact Message(String Message) {
        setMessage(Message);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(Name, contact.Name) && Objects.equals(number, contact.number) && Objects.equals(Email, contact.Email) && Objects.equals(Message, contact.Message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, number, Email, Message);
    }

    @Override
    public String toString() {
        return "{" +
            " Name='" + getName() + "'" +
            ", number='" + getNumber() + "'" +
            ", Email='" + getEmail() + "'" +
            ", Message='" + getMessage() + "'" +
            "}";
    }
}

package org.example.models;

public class RecipientInfo {
    public String recipientName;
    public String recipientEmail;
    public String recipientPassword;

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientPassword() {
        return recipientPassword;
    }

    public void setRecipientPassword(String recipientPassword) {
        this.recipientPassword = recipientPassword;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}

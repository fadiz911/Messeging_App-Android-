package com.example.chatapplication.services.model;

public class Chats {
    private String receiverId;
    private String senderId;
    private String message;
    private String timestamp;
    private boolean seen;
    private String fileName; // New field for file name
    private String fileSize; // New field for file size
    private boolean isFile;  // New field to indicate if the message is a file

    // Constructor for text messages
    public Chats(String receiverId, String senderId, String message, String timestamp, boolean seen) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.message = message;
        this.timestamp = timestamp;
        this.seen = seen;
        this.isFile = false; // Default to false for text messages
    }

    // Constructor for file messages
    public Chats(String receiverId, String senderId, String fileName, String fileSize, String timestamp, boolean seen) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.timestamp = timestamp;
        this.seen = seen;
        this.isFile = true; // Mark as a file message
    }

    // Default constructor (required for Firebase or other serialization libraries)
    public Chats() {
    }

    // Getters and setters
    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }
}
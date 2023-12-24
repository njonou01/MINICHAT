package business.general;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String body;
    private String sender;
    private LocalDateTime date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Message(String sender) {
        date = LocalDateTime.now();
        this.sender = sender;
        this.body = "";
    }

    public Message(String sender, String body) {
        date = LocalDateTime.now();
        this.sender = sender;
        this.body = body;
    }

    public Message(String sender, String body, String date) {
        try {
            this.date = LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.sender = sender;
        this.body = body;

    }

    public int length() {
        return body.length();
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date.toString();
    }

    public void setBody(String body) {
        this.body = body;
        this.date = LocalDateTime.now();
    }

    public String getSender() {
        return sender;
    }

}

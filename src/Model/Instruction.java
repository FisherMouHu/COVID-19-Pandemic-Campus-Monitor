package Model;

import java.sql.Timestamp;

public class Instruction {
    private String id;
    private String type;
    private String title;
    private String content;
    private String email;
    private String publisher;
    private Timestamp time;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getEmail() {
        return email;
    }

    public String getPublisher() {
        return publisher;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

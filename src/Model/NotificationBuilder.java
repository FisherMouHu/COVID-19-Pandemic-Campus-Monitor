package Model;

import java.sql.Timestamp;

public class NotificationBuilder implements InstructionBuilder {
    private Instruction instruction;

    public NotificationBuilder() {
        this.instruction = new Instruction();
    }

    @Override
    public void buildId(String id) {
        instruction.setId(id);
    }

    @Override
    public void buildType() {
        instruction.setType("Notification");
    }

    @Override
    public void buildTitle(String title) {
        instruction.setTitle(title);
    }

    @Override
    public void buildContent(String content) {
        instruction.setContent(content);
    }

    @Override
    public void buildEmail(String email) {
        instruction.setEmail(email);
    }

    @Override
    public void buildPublisher(String publisher) {
        instruction.setPublisher(publisher);
    }

    @Override
    public Instruction getInstruction() {
        return instruction;
    }

    @Override
    public void buildTime(Timestamp time) {
        instruction.setTime(time);
    }
}

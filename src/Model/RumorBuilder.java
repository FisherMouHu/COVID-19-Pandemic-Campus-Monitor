package Model;

import java.sql.Timestamp;

public class RumorBuilder implements InstructionBuilder {
    private Instruction instruction;

    public RumorBuilder() {
        this.instruction = new Instruction();
    }

    @Override
    public void buildId(String id) {
        instruction.setId(id);
    }

    @Override
    public void buildType() {
        instruction.setType("Rumor");
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
    public void buildTime(Timestamp time) {
        instruction.setTime(time);
    }

    @Override
    public Instruction getInstruction() {
        return instruction;
    }
}


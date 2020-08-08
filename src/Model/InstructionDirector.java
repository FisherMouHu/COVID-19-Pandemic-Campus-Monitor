package Model;

import java.sql.Timestamp;

public class InstructionDirector {
    private InstructionBuilder instructionBuilder;

    public InstructionDirector(InstructionBuilder instructionBuilder) {
        this.instructionBuilder = instructionBuilder;
    }

    public Instruction getInstruction() {
        return this.instructionBuilder.getInstruction();
    }

    public void constructInstruction(String id, String title, String content, String email, String publisher, Timestamp time) {
        this.instructionBuilder.buildId(id);
        this.instructionBuilder.buildType();
        this.instructionBuilder.buildTitle(title);
        this.instructionBuilder.buildContent(content);
        this.instructionBuilder.buildEmail(email);
        this.instructionBuilder.buildPublisher(publisher);
        this.instructionBuilder.buildTime(time);
    }
}

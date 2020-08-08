package Model;

import java.sql.Timestamp;

public interface InstructionBuilder {
    public void buildId(String id);

    public void buildType();

    public void buildTitle(String title);

    public void buildContent(String content);

    public void buildEmail(String email);

    public void buildPublisher(String publisher);

    public void buildTime(Timestamp time);

    public Instruction getInstruction();
}

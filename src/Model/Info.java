package Model;

public class Info {
    private String id;
    private String school;
    private int confirmedNum;
    private int infectedNum;
    private int curedNum;
    private int deathNum;

    public Info(String school, int confirmedNum, int infectedNum, int curedNum, int deathNum) {
        this.school = school;
        this.confirmedNum = confirmedNum;
        this.infectedNum = infectedNum;
        this.curedNum = curedNum;
        this.deathNum = deathNum;
    }

    public String getSchool() {
        return school;
    }

    public int getConfirmedNum() {
        return confirmedNum;
    }

    public int getInfectedNum() {
        return infectedNum;
    }

    public int getCuredNum() {
        return curedNum;
    }

    public int getDeathNum() {
        return deathNum;
    }
}

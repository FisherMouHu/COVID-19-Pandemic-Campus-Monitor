package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class UserViewInitiator {
    private Connection connection;
    protected Statement statement;

    public final void init() {
        // Connect to the Database
        connectDB();

        initMainPanel();

        initOthers();
    }

    private void connectDB() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_pandemic?useSSL=false&serverTimezone=UTC";
        String name = "root";
        String pwd = "19951124";

        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(url, name, pwd);

            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver cannot be Found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMainPanel() {
        initInfo();
    }

    public abstract void initInfo();

    public abstract void initOthers();
}

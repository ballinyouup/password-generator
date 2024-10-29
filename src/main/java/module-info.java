module com.passly.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires java.base;
    requires mysql.connector.j;
    requires java.sql;
    requires java.naming;
    requires io.github.cdimascio.dotenv.java;

    opens com.passly.app to javafx.fxml;
    exports com.passly.app;
}
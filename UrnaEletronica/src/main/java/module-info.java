module com.example.UrnaEletronica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.edu.principal to javafx.fxml;
    exports br.edu.principal;
}
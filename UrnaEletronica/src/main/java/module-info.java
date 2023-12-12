module com.example.UrnaEletronica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.edu.principal to javafx.fxml;
    exports br.edu.principal;
    exports br.edu.uteis;
    opens br.edu.uteis to javafx.fxml;
    exports br.edu.telas;
    opens br.edu.telas to javafx.fxml;
}
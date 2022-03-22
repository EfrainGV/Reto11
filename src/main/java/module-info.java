module co.edu.udea {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.udea to javafx.fxml;
    exports co.edu.udea;
}

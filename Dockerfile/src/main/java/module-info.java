module cs.yogesh.dockerfile {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs.yogesh.dockerfile to javafx.fxml;
    exports cs.yogesh.dockerfile;
}
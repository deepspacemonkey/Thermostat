module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens app to javafx.fxml;
    opens view to javafx.fxml;
    exports app;
}
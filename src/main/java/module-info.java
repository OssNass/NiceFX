module NiceFX{
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    exports io.github.ossnass.nicefx;
    opens io.github.ossnass.nicefx to javafx.fxml;
}
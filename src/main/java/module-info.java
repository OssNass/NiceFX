module NiceFX{
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires io.github.classgraph;
    exports io.github.ossnass.nicefx;
    opens io.github.ossnass.nicefx to javafx.fxml;
    exports io.github.ossnass.nicefx.actions;
    opens io.github.ossnass.nicefx.actions to javafx.fxml;
    exports io.github.ossnass.nicefx.types;
    opens io.github.ossnass.nicefx.types to javafx.fxml;
    exports io.github.ossnass.nicefx.types.exceptions;
    opens io.github.ossnass.nicefx.types.exceptions to javafx.fxml;
}
package io.github.ossnass.nicefx;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Represents the basic controller of the NiceFX library.
 * 
 * Must have {@link ControllerInfo} annotation present, otherwise an
 * {@link ControllerNotAnnotatedException} will be thrown during initialization.
 * 
 * In order for the controller to work few setups in the FXML must presents:
 * <ol>
 * <li>The main pane where all the nodes resides must extend the
 * {@link javafx.scene.layout.Pane} and have the id of <b>root</b></li>
 * <li>If this controller gets its own stage, in the language file you must have
 * the key <b>STAGE.ControllerName.TITLE</b> present with value, check
 * {@link language} for more info</li>
 * </ol>
 * 
 * @author Ossama Nasser <ossnass@gmail.com>
 */
public abstract class Controller {
	@FXML
	protected Pane root;
	protected SimpleStringProperty icon;
	protected SimpleStringProperty extraCSSFile;

	protected SimpleObjectProperty<Scene> scene;
	protected SimpleObjectProperty<Stage> stage;

	@FXML
	protected ResourceBundle language;

	@FXML
	public void initialize(ResourceBundle language) {
		this.language = language;
		userInit();
	}

	protected void userInit() {

	}

	void onStageChange(ObservableValue<Stage> observable, Stage oldValue, Stage newValue) {
		if (oldValue != null) {
			oldValue.setScene(null);
		}
		if (newValue != null && scene.isNotNull().get()) {
			newValue.setScene(scene.get());
		}
	}

	void onSceneChange(ObservableValue<Scene> observable, Scene oldValue, Scene newValue) {
		if (oldValue != null) {
			oldValue.setRoot(null);
		}
		if (newValue != null) {
			newValue.setRoot(root);
			if (stage.isNotNull().get()) {
				stage.get().setScene(newValue);
			}
		}

	}

	void onIconChange(ObservableValue<String> observable, String oldValue, String newValue) {
		if (stage.isNotNull().get()) {
			stage.get().getIcons().clear();
//			stage.get().getIcons().add(newValue);
		}
	}

	void onExtraCSSChange(ObservableValue<String> observable, String oldValue, String newValue) {

	}

	public Controller() {
		// Prevents the creation of the controller if is not annotated with
		// ContollerInfo
		if (!this.getClass().isAnnotationPresent(ControllerInfo.class)) {
			throw new ControllerNotAnnotatedException(this.getClass().getCanonicalName());
		}
		icon = new SimpleStringProperty(this.getClass().getAnnotation(ControllerInfo.class).Icon());
		icon.addListener(this::onIconChange);
		extraCSSFile = new SimpleStringProperty(this.getClass().getAnnotation(ControllerInfo.class).ExtraCSS());
		extraCSSFile.addListener(this::onExtraCSSChange);
		stage = new SimpleObjectProperty<Stage>();
		stage.addListener(this::onStageChange);
		scene = new SimpleObjectProperty<Scene>();
		scene.addListener(this::onSceneChange);

	}

	/**
	 * Returns the current Root Pane of the scene.
	 * 
	 * In order for the root pane must be defined with ID=root in the FXML file
	 * 
	 * @return the current Root Pane of the scene.
	 */
	public Pane getRoot() {
		return this.root;
	}

	/**
	 * Returns The current Icon of the stage as a {@link SimpleStringProperty}.
	 * 
	 * @return The current Icon of the stage as a {@link SimpleStringProperty}.
	 */
	public SimpleStringProperty IconProperty() {
		return this.icon;
	}

	/**
	 * Returns The current Icon of the stage.
	 * 
	 * @return The current Icon of the stage.
	 */
	public String getIcon() {
		return this.icon.get();
	}

	/**
	 * Changes the current icon of the stage
	 * 
	 * @param Icon the new icon of the stage
	 * @return the current controller
	 */
	public Controller setIcon(String Icon) {
		this.icon.set(Icon);
		return this;
	}

	/**
	 * Returns The current extra CSS file of the scene as a
	 * {@link SimpleStringProperty}.
	 * 
	 * @return The current extra CSS file of the scene as a
	 *         {@link SimpleStringProperty}.
	 */
	public SimpleStringProperty ExtraCSSFileProperty() {
		return this.extraCSSFile;
	}

	/**
	 * Returns The current extra CSS file of the scene.
	 * 
	 * @return The current extra CSS file of the scene.
	 */
	public String getExtraCSSFile() {
		return this.extraCSSFile.get();
	}

	/**
	 * Changes the extra CSS file the scene.
	 * 
	 * @param ExtraCSSFile the new CSS file of the scene.
	 * @return the current controller.
	 */
	public Controller setExtraCSSFile(String ExtraCSSFile) {
		this.extraCSSFile.set(ExtraCSSFile);
		return this;
	}

	public SimpleObjectProperty<Scene> sceneProperty() {
		return this.scene;
	}

	public Scene getScene() {
		return this.scene.get();
	}

	public Controller setScene(Scene scene) {
		this.scene.set(scene);
		return this;
	}

	public SimpleObjectProperty<Stage> stageProperty() {
		return this.stage;
	}

	public Stage getStage() {
		return this.stage.get();
	}

	public Controller setStage(Stage stage) {
		this.stage	.set(stage);
		return this;
	}

}
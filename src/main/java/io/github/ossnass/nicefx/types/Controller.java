package io.github.ossnass.nicefx.types;

import java.util.ResourceBundle;

import io.github.ossnass.nicefx.Language;
import io.github.ossnass.nicefx.types.exceptions.ControllerNotAnnotatedException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
 * the key <b>STAGE.ControllerName.TITLE</b> present with the value, check
 * {@link io.github.ossnass.nicefx.Language} for more info</li>
 * </ol>
 * 
 * @author Ossama Nasser <ossnass@gmail.com>
 */
public abstract class Controller {
	/**
	 * The main node of the GUI
	 */
	@FXML
	protected Pane root;
	/**
	 * The icon of the stage
	 */
	protected SimpleStringProperty icon;
	/**
	 * This stage extra CSS file
	 */
	protected SimpleStringProperty extraCSSFile;

	/**
	 * The current scene on which the nodes are drawn
	 */
	protected SimpleObjectProperty<Scene> scene;
	/**
	 * The stage in which everything is shown
	 */
	protected SimpleObjectProperty<Stage> stage;

	private String Id;

	/**
	 * The language data
	 */
	@FXML
	protected ResourceBundle language;

	@FXML
	private void initialize(ResourceBundle language) {
		this.language = language;
		userInit();
	}

	/**
	 * Must be overridden by the user to add custom initialization code which will be run after the internal initilization code
	 */
	protected void userInit() {

	}

	/**
	 * The id of the controller
	 * @return the id of the controller
	 */
	public String getId() {
		return Id;
	}

	/**
	 * Changes the Id of the controller
	 * @param id the new id of the controller
	 * @return A controller
	 * @throws IllegalStateException if trying to change an already set Id
	 *
	 */
	public Controller setId(String id) {
		if(Id!=null)
			throw new IllegalStateException("Cannot change controller Id");
		Id = id;
		return this;
	}

	/**
	 * The codes gets executed when changing the stage
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 */
	void onStageChange(ObservableValue<? extends Stage> observable, Stage oldValue, Stage newValue) {
		if (oldValue != null) {
			oldValue.setScene(null);
		}
		if (newValue != null && scene.isNotNull().get()) {
			newValue.setScene(scene.get());
			newValue.setTitle(language.getString(String.format(Language.WINDOW_TITLE,Id)));
		}
	}

	/**
	 * The codes gets executed when changing the scene
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 */
	void onSceneChange(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
		if (oldValue != null) {
			oldValue.setRoot(null);
		}
		if (newValue != null) {
			newValue.setRoot(root);
			if (stage.isNotNull().get()) {
				stage.get().setScene(newValue);
				newValue.getStylesheets();
			}
		}

	}

	/**
	 * The codes gets executed when changing the icon
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 */
	void onIconChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (stage.isNotNull().get()) {
			stage.get().getIcons().clear();
//			stage.get().getIcons().add(newValue);
		}
	}

	/**
	 * The codes gets executed when changing the CSS file
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 */
	void onExtraCSSChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {

	}

	/**
	 * Creates a new Controller
	 */
	public Controller() {
		// Prevents the creation of the controller if is not annotated with
		// ContollerInfo
		if (!this.getClass().isAnnotationPresent(ControllerInfo.class)) {
			throw new ControllerNotAnnotatedException(this.getClass().getCanonicalName());
		}
		icon = new SimpleStringProperty(this.getClass().getAnnotation(ControllerInfo.class).icon());
		icon.addListener(this::onIconChange);
		extraCSSFile = new SimpleStringProperty(this.getClass().getAnnotation(ControllerInfo.class).extraCSS());
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

	/**
	 * Returns the current scene property
	 * @returnthe current scene property
	 */

	public SimpleObjectProperty<Scene> sceneProperty() {
		return this.scene;
	}

	/**
	 * Returns the current scene
	 * @return the current scene
	 */
	public Scene getScene() {
		return this.scene.get();
	}

	/**
	 * Changes the current scene
	 * @param scene the new scene
	 * @return the Controller after chaning the scene
	 */
	public Controller setScene(Scene scene) {
		this.scene.set(scene);
		return this;
	}

	/**
	 * Returns the current stage property
	 * @return the current stage property
	 */
	public SimpleObjectProperty<Stage> stageProperty() {
		return this.stage;
	}

	/**
	 * Returns the current stage
	 * @return the current stage
	 */
	public Stage getStage() {
		return this.stage.get();
	}

	/**
	 * Changes the current stage
	 * @param stage the new stage
	 * @return the Controller after chaning the stage
	 */
	public Controller setStage(Stage stage) {
		this.stage.set(stage);
		return this;
	}

}
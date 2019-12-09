package dad.javafx.conocimiento;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.clases.Conocimiento;
import dad.javafx.clases.Nivel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;

public class NuevoConocimientoController extends Dialog<Conocimiento> implements Initializable {
	
	private ButtonType CREAR_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);
	
	private Conocimiento conocimiento = new Conocimiento(null, null);

    @FXML
    private BorderPane view;

    @FXML
    private TextField denominacionText;

    @FXML
    private ComboBox<Nivel> nivelCombo;

    @FXML
    private Button xButton;

    @FXML
    void onXAction(ActionEvent event) {
    	nivelCombo.getSelectionModel().clearSelection();
    }
    
    public NuevoConocimientoController() {
    	super();
		setTitle("Nuevo conocimiento");
		getDialogPane().getButtonTypes().addAll(
				CREAR_BUTTON_TYPE,
				ButtonType.CANCEL
			);
		getDialogPane().setContent(loadContent("/fxml/NuevoConocimientoDialog.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	Conocimiento con = new Conocimiento(conocimiento.getDenominacion(), conocimiento.getNivel());
		        return con;
		    }
		    return null;
		});
	}
    
	private Node loadContent(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			loader.setController(this);
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conocimiento.denominacionProperty().bind(denominacionText.textProperty());
		conocimiento.nivelProperty().bind(nivelCombo.valueProperty());
		
		nivelCombo.getItems().addAll(Nivel.values());
		
		Node crearButton = getDialogPane().lookupButton(CREAR_BUTTON_TYPE);
		crearButton.disableProperty().bind(
				conocimiento.denominacionProperty().isEmpty()
				);
	}

}

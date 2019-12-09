package dad.javafx.contacto;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.clases.Telefono;
import dad.javafx.clases.TipoTelefono;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class NuevoTelefonoDialogController extends Dialog<Telefono> implements Initializable {
	
	private ButtonType CREAR_BUTTON_TYPE = new ButtonType("Añadir", ButtonData.OK_DONE);
	
	// model
	
	private Telefono telefono = new Telefono(null, null);
	
	// view
	
    @FXML
    private BorderPane view;

    @FXML
    private TextField numTxt;

    @FXML
    private ComboBox<TipoTelefono> typeCb;

	public NuevoTelefonoDialogController() {
		super();
		setTitle("Nuevo teléfono");
		setHeaderText("Introduzca el nuevo número de teléfono.");
		getDialogPane().getButtonTypes().addAll(
				CREAR_BUTTON_TYPE,
				ButtonType.CANCEL
			);
		getDialogPane().setContent(loadContent("/fxml/NuevoTelefonoDialog.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	Telefono nuevoTelefono = new Telefono(telefono.getNumero(),telefono.getTipo());
		        return nuevoTelefono;
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
		telefono.numeroProperty().bind(numTxt.textProperty());
		telefono.tipoProperty().bind(typeCb.valueProperty());
		
		typeCb.getItems().addAll(TipoTelefono.values());
		
		Node crearButton = getDialogPane().lookupButton(CREAR_BUTTON_TYPE);
		crearButton.disableProperty().bind(
				telefono.numeroProperty().isEmpty()
				.or(telefono.tipoProperty().isNull())
				);
	}

}
package dad.javafx.experiencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.clases.Experiencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ButtonBar.ButtonData;

public class NuevaExperienciaDialogController extends Dialog<Experiencia> implements Initializable {
	
	private ButtonType CREAR_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);
	
	private Experiencia experiencia = new Experiencia(null, null, null, null);

    @FXML
    private BorderPane view;

    @FXML
    private TextField denominacionText;

    @FXML
    private TextField empleadorText;

    @FXML
    private DatePicker desdeDatePicker;

    @FXML
    private DatePicker hastaDatePicker;
    
    public NuevaExperienciaDialogController() {
    	super();
		setTitle("Nueva experiencia");
		getDialogPane().getButtonTypes().addAll(
				CREAR_BUTTON_TYPE,
				ButtonType.CANCEL
			);
		getDialogPane().setContent(loadContent("/fxml/NuevaExperienciaDialog.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	Experiencia ex = new Experiencia(experiencia.getDesde(), experiencia.getHasta(), experiencia.getDenominacion(), experiencia.getEmpleador());
		        return ex;
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
		experiencia.denominacionProperty().bind(denominacionText.textProperty());
		experiencia.empleadorProperty().bind(empleadorText.textProperty());
		experiencia.desdeProperty().bind(desdeDatePicker.valueProperty());
		experiencia.hastaProperty().bind(hastaDatePicker.valueProperty());
		
		Node crearButton = getDialogPane().lookupButton(CREAR_BUTTON_TYPE);
		crearButton.disableProperty().bind(
				experiencia.denominacionProperty().isEmpty()
				.or(experiencia.empleadorProperty().isEmpty())
				.or(experiencia.desdeProperty().isNull())
				.or(experiencia.hastaProperty().isNull())
				);
	}

}

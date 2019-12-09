package dad.javafx.formacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.clases.Titulo;
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

public class NuevoTituloDialogController extends Dialog<Titulo> implements Initializable {
	
	private ButtonType CREAR_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);
	
	private Titulo titulo = new Titulo(null, null, null, null);

    @FXML
    private BorderPane view;

    @FXML
    private TextField denominacionText;

    @FXML
    private TextField organizadorText;

    @FXML
    private DatePicker desdeDatePicker;

    @FXML
    private DatePicker hastaDatePicker;
    
    public NuevoTituloDialogController() {
    	super();
		setTitle("Nuevo título");
		getDialogPane().getButtonTypes().addAll(
				CREAR_BUTTON_TYPE,
				ButtonType.CANCEL
			);
		getDialogPane().setContent(loadContent("/fxml/NuevoTituloDialog.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	Titulo tit = new Titulo(titulo.getDesde(), titulo.getHasta(), titulo.getDenominacion(), titulo.getOrganizador());
		        return tit;
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
		titulo.denominacionProperty().bind(denominacionText.textProperty());
		titulo.organizadorProperty().bind(organizadorText.textProperty());
		titulo.desdeProperty().bind(desdeDatePicker.valueProperty());
		titulo.hastaProperty().bind(hastaDatePicker.valueProperty());
		
		Node crearButton = getDialogPane().lookupButton(CREAR_BUTTON_TYPE);
		crearButton.disableProperty().bind(
				titulo.denominacionProperty().isEmpty()
				.or(titulo.organizadorProperty().isEmpty())
				.or(titulo.desdeProperty().isNull())
				.or(titulo.hastaProperty().isNull())
				);
	}

}

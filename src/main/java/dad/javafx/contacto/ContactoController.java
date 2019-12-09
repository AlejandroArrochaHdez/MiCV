package dad.javafx.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.clases.Contacto;
import dad.javafx.clases.Email;
import dad.javafx.clases.Telefono;
import dad.javafx.clases.TipoTelefono;
import dad.javafx.clases.Web;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ContactoController implements Initializable {
	
	private ListProperty<Telefono> telList = new SimpleListProperty<Telefono>(FXCollections.observableList(new ArrayList<Telefono>()));
	private ListProperty<Email> emailList = new SimpleListProperty<Email>(FXCollections.observableList(new ArrayList<Email>()));
	private ListProperty<Web> webList = new SimpleListProperty<Web>(FXCollections.observableList(new ArrayList<Web>()));
	
    @FXML
    private BorderPane view;

    @FXML
    private TableView<Telefono> telefonoTable;

    @FXML
    private TableColumn<Telefono, String> numeroColumn;

    @FXML
    private TableColumn<Telefono, TipoTelefono> tipoColumn;

    @FXML
    private TableView<Email> emailTabla;

    @FXML
    private TableColumn<Email, String> emailColumn;

    @FXML
    private TableView<Web> websTable;

    @FXML
    private TableColumn<Web, String> urlColumn;
    
    public ContactoController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
    public BorderPane getView() {
		return view;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		numeroColumn.setCellValueFactory(v -> v.getValue().numeroProperty());
		tipoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		emailColumn.setCellValueFactory(v -> v.getValue().direccionProperty());
		urlColumn.setCellValueFactory(v -> v.getValue().urlProperty());
		
		emailTabla.setEditable(true);
		telefonoTable.setEditable(true);
		websTable.setEditable(true);
		
		numeroColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		tipoColumn.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
		emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		urlColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		telefonoTable.itemsProperty().bind(telList);
		emailTabla.itemsProperty().bind(emailList);
		websTable.itemsProperty().bind(webList);

	}

    @FXML
    void onAñadirEmailButton(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Nueva web");
    	dialog.setHeaderText("Crear una nueva dirección de correo");
    	dialog.setContentText("E-mail: ");

    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent() && !result.get().isEmpty()){
    		emailTabla.getItems().add(new Email(result.get()));
    	}
    }

    @FXML
    void onAñadirTelefonoButton(ActionEvent event) {
    	NuevoTelefonoDialogController dialog = new NuevoTelefonoDialogController();

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imgs/cv64x64.png").toString()));

		Optional<Telefono> telefono = dialog.showAndWait();

		if (telefono.isPresent()) {
			 telefonoTable.getItems().add(telefono.get());
		}
    }

    @FXML
    void onAñadirWebButton(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog("http://");
    	dialog.setTitle("Nueva web");
    	dialog.setHeaderText("Crear una nueva dirección web");
    	dialog.setContentText("URL:");

    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent() && !result.get().isEmpty()){
    		websTable.getItems().add(new Web(result.get()));
    	}
    }

    @FXML
    void onEliminarEmailButton(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("Eliminar");
		alert.setContentText("¿Estás seguro de que quieres eliminar el email?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			emailTabla.getItems().remove(emailTabla.getSelectionModel().getSelectedIndex());
		} else {
		}
    	

    }

    @FXML
    void onEliminarTelefonoButton(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("Eliminar");
		alert.setContentText("¿Estás seguro de que quieres eliminar el teléfono?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			telefonoTable.getItems().remove(telefonoTable.getSelectionModel().getSelectedIndex());
		} else {
		}
    	
    }

    @FXML
    void onEliminarWebButton(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("Eliminar");
		alert.setContentText("¿Estás seguro de que quieres eliminar la web?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			websTable.getItems().remove(websTable.getSelectionModel().getSelectedIndex());
		} else {
		}
    	

    }
	
	public void setContacto(Contacto contacto) {
		telefonoTable.getItems().clear();
		emailTabla.getItems().clear();
		websTable.getItems().clear();
		telefonoTable.getItems().addAll(contacto.getTelefonos());
		emailTabla.getItems().addAll(contacto.getEmails());
		websTable.getItems().addAll(contacto.getWebs());
		
	}
	
	public Contacto getContacto() {
		Contacto c = new Contacto(telList, emailList, webList);
		return c;
	}
	

}

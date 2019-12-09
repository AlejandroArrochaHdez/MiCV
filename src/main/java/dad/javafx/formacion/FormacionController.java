package dad.javafx.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.clases.Titulo;
import dad.javafx.utils.LocalDateTableCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FormacionController implements Initializable{
	
    @FXML
    private GridPane view;

    @FXML
    private TableView<Titulo> formacionTable;

    @FXML
    private TableColumn<Titulo, LocalDate> desdeColumn;

    @FXML
    private TableColumn<Titulo, LocalDate> hastaColumn;

    @FXML
    private TableColumn<Titulo, String> denominacionColumn;

    @FXML
    private TableColumn<Titulo, String> organizadorColumn;
	
    public FormacionController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
    public GridPane getView() {
		return view;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());
		
		formacionTable.setEditable(true);
		desdeColumn.setCellFactory(LocalDateTableCell::new);
		hastaColumn.setCellFactory(LocalDateTableCell::new);
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
	}
	
    @FXML
    void onAñadirAction(ActionEvent event) {
    	NuevoTituloDialogController dialog = new NuevoTituloDialogController();

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imgs/cv64x64.png").toString()));

		Optional<Titulo> titulo = dialog.showAndWait();

		if (titulo.isPresent()) {
			 formacionTable.getItems().add(titulo.get());
		}
    }
    
    @FXML
    void onEliminarAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("Eliminar");
		alert.setContentText("¿Estás seguro de que quieres eliminar la formación?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			formacionTable.getItems().remove(formacionTable.getSelectionModel().getSelectedIndex());
		} else {
		}
    	
    }

}

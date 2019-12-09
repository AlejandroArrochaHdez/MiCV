package dad.javafx.experiencia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.clases.Experiencia;
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

public class ExperienciaController implements Initializable {
	
    @FXML
    private GridPane view;

    @FXML
    private TableView<Experiencia> experienciaTable;

    @FXML
    private TableColumn<Experiencia, LocalDate> desdeColumn;

    @FXML
    private TableColumn<Experiencia, LocalDate> hastaColumn;

    @FXML
    private TableColumn<Experiencia, String> denominacionColumn;

    @FXML
    private TableColumn<Experiencia, String> empleadorColumn;
    
    public ExperienciaController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
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
		empleadorColumn.setCellValueFactory(v -> v.getValue().empleadorProperty());
		
		experienciaTable.setEditable(true);
		desdeColumn.setCellFactory(LocalDateTableCell::new);
		hastaColumn.setCellFactory(LocalDateTableCell::new);
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

	}

    @FXML
    void onAñadirAction(ActionEvent event) {
    	NuevaExperienciaDialogController dialog = new NuevaExperienciaDialogController();

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imgs/cv64x64.png").toString()));

		Optional<Experiencia> experiencia = dialog.showAndWait();

		if (experiencia.isPresent()) {
			experienciaTable.getItems().add(experiencia.get());
		}
    }
    
    @FXML
    void onEliminarAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("Salir");
		alert.setContentText("¿Eliminar seguro de que quieres eliminar la experiencia?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			experienciaTable.getItems().remove(experienciaTable.getSelectionModel().getSelectedIndex());
		} else {
		}
    	
    }

}

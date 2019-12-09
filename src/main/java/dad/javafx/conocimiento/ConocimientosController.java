package dad.javafx.conocimiento;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.clases.Conocimiento;
import dad.javafx.clases.Idioma;
import dad.javafx.clases.Nivel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConocimientosController implements Initializable {
	
    @FXML
    private GridPane view;

    @FXML
    private TableView<Conocimiento> conocimientosTable;

    @FXML
    private TableColumn<Conocimiento, String> denominacionColumn;

    @FXML
    private TableColumn<Conocimiento, Nivel> nivelColumn;
    
    public ConocimientosController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());
		
		conocimientosTable.setEditable(true);
		
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));

	}
	
    @FXML
    void onAñadirConocimientoAction(ActionEvent event) {
    	NuevoConocimientoController dialog = new NuevoConocimientoController();

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imgs/cv64x64.png").toString()));

		Optional<Conocimiento> experiencia = dialog.showAndWait();

		if (experiencia.isPresent()) {
			conocimientosTable.getItems().add(experiencia.get());
		}
    }

    @FXML
    void onAñadirIdiomaAction(ActionEvent event) {
    	NuevoIdiomaController dialog = new NuevoIdiomaController();

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imgs/cv64x64.png").toString()));

		Optional<Idioma> idioma = dialog.showAndWait();

		if (idioma.isPresent()) {
			conocimientosTable.getItems().add(idioma.get());
		}
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("Eliminar");
		alert.setContentText("¿Estás seguro de que quieres eliminar el conocimiento?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
	    	conocimientosTable.getItems().remove(conocimientosTable.getSelectionModel().getSelectedIndex());
		} else {
		}
    }
    
    public GridPane getView() {
		return view;
	}

}

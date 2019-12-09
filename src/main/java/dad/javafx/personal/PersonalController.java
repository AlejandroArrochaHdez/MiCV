package dad.javafx.personal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.clases.Nacionalidad;
import dad.javafx.clases.Personal;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {
	
	private List<String> choices;
	
	private ListProperty<Nacionalidad> nList = new SimpleListProperty<Nacionalidad>(FXCollections.observableList(new ArrayList<Nacionalidad>()));
	
	@FXML
	private GridPane view;

	@FXML
	private ListView<Nacionalidad> nacionalidadListView;

	@FXML
	private Button masButton;

	@FXML
	private Button menosButton;

	@FXML
	private TextField dniText;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField apellidosText;

	@FXML
	private TextField codigoPostalText;

	@FXML
	private TextArea direccionArea;

	@FXML
	private TextField localidadText;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private DatePicker nacimientoPicker;

	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cargarPaises();
		paisCombo.getSelectionModel().selectFirst();
		
		nacionalidadListView.itemsProperty().bind(nList);
	}

	public void cargarPaises() {
		String csvFile = "paises.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
			while ((line = br.readLine()) != null) {
				String[] datos = line.split(cvsSplitBy);
				paisCombo.getItems().add(datos[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void cargarNacionalidades() {
		String csvFile = "nacionalidades.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
			while ((line = br.readLine()) != null) {
				String[] datos = line.split(cvsSplitBy);
				choices.add(datos[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	void onMasAction(ActionEvent event) {
		choices = new ArrayList<>();
		cargarNacionalidades();

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Nueva nacionalidad");
		dialog.setHeaderText("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Nacionalidad n = new Nacionalidad(result.get());
			nacionalidadListView.getItems().add(n);
		}
	}

	@FXML
	void onMenosAction(ActionEvent event) {
		nacionalidadListView.getItems().remove(nacionalidadListView.getSelectionModel().getSelectedIndex());
	}
	
	public void setPersonal(Personal personal) {
		dniText.setText(personal.getIdentificacion());
		nombreText.setText(personal.getNombre());
		apellidosText.setText(personal.getApellidos());
		nacimientoPicker.setValue(personal.getFechaNacimiento());
		direccionArea.setText(personal.getDireccion());
		codigoPostalText.setText(personal.getCodigoPostal());
		localidadText.setText(personal.getLocalidad());
		paisCombo.setValue(personal.getPais());
		nacionalidadListView.getItems().clear();
		nacionalidadListView.getItems().addAll(personal.getNacionalidades());

	}

	
	public Personal getPersonal() {
		Personal p = new Personal(dniText.getText(),nombreText.getText(), apellidosText.getText()
				,nacimientoPicker.getValue(), direccionArea.getText(), codigoPostalText.getText()
				,localidadText.getText(), paisCombo.getSelectionModel().getSelectedItem(), nList);
		return p;
	}

}

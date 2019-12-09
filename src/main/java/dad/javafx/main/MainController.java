package dad.javafx.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.clases.CV;
import dad.javafx.clases.Contacto;
import dad.javafx.clases.Personal;
import dad.javafx.conocimiento.ConocimientosController;
import dad.javafx.contacto.ContactoController;
import dad.javafx.experiencia.ExperienciaController;
import dad.javafx.formacion.FormacionController;
import dad.javafx.personal.PersonalController;
import dad.javafx.utils.JAXBUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	
	private boolean nuevo = true;
	
	private String rutaAbrir = "C:\\Users\\Alejandro\\Downloads\\nuevo.xml";

	private ConocimientosController conocimientosController = new ConocimientosController();

	private ExperienciaController experienciaController = new ExperienciaController();

	private FormacionController formacionController = new FormacionController();

	private PersonalController personalController = new PersonalController();

	private ContactoController contactoController = new ContactoController();

	@FXML
	private BorderPane view;

	@FXML
	private MenuItem nuevoMenuItem;

	@FXML
	private MenuItem abrirMenuItem;

	@FXML
	private MenuItem guardarMenuItem;

	@FXML
	private MenuItem guardarComoMenuItem;

	@FXML
	private MenuItem salirMenuItem;

	@FXML
	private MenuItem acerdaDeMenuItem;

	@FXML
	private Tab personalTab;

	@FXML
	private Tab contactoTab;

	@FXML
	private Tab formacionTab;

	@FXML
	private Tab experienciaTab;

	@FXML
	private Tab conocimientosTab;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conocimientosTab.setContent(conocimientosController.getView());
		experienciaTab.setContent(experienciaController.getView());
		formacionTab.setContent(formacionController.getView());
		contactoTab.setContent(contactoController.getView());
		personalTab.setContent(personalController.getView());

		nuevoMenuItem.setGraphic(new ImageView("/imgs/nuevo.gif"));
		guardarMenuItem.setGraphic(new ImageView("/imgs/guardar.gif"));
		abrirMenuItem.setGraphic(new ImageView("/imgs/abrir.gif"));

	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onAbrirAction(ActionEvent event) throws Exception {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Abrir");
		dialog.setHeaderText("Abrir CV");
		dialog.setContentText("Ruta:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			rutaAbrir = result.get();
			File f = new File(result.get());
			CV cv = JAXBUtil.load(CV.class, f);
			cargarDatos(cv);
			nuevo = false;
		}
	}

	private void cargarDatos(CV cv) {
		personalController.setPersonal(cv.getPersonal());
		contactoController.setContacto(cv.getContacto());

	}

	@FXML
	void onGuardarAction(ActionEvent event) {
		if (!nuevo) {
			File f = new File(rutaAbrir);
			CV cv = new CV();
			cv.setPersonal(personalController.getPersonal());
			cv.setContacto(contactoController.getContacto());
			try {
				JAXBUtil.save(cv, f);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			onGuardarComoAction(event);
		}
	}

	@FXML
	void onGuardarComoAction(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Guardar como");
		dialog.setHeaderText("Guardar CV");
		dialog.setContentText("Ruta + nombre:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			File f = new File(result.get());
			CV cv = new CV();
			cv.setPersonal(personalController.getPersonal());
			cv.setContacto(contactoController.getContacto());
			try {
				JAXBUtil.save(cv, f);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		Personal personal = new Personal();
		Contacto contacto = new Contacto();
		personalController.setPersonal(personal);
		contactoController.setContacto(contacto);
		nuevo = true;
	}

	@FXML
	void onSalirAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Salir");
		alert.setHeaderText("Salir");
		alert.setContentText("¿Estás seguro de que quieres salir del programa?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Platform.exit();
		} else {
		}
	}

	@FXML
	void onAcercaDeAction(ActionEvent event) {

	}

}

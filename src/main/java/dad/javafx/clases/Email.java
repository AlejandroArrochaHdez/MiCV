package dad.javafx.clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Email {
	
	private StringProperty direccion = new SimpleStringProperty();
	
	public Email() {
		// TODO Auto-generated constructor stub
	}
	
	public Email(String direc) {
		direccion.set(direc);
	}

	public final StringProperty direccionProperty() {
		return this.direccion;
	}
	
	@XmlAttribute
	public final String getDireccion() {
		return this.direccionProperty().get();
	}
	

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}
	
	
	
	
}

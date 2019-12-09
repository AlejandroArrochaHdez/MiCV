package dad.javafx.clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Conocimiento {
	
	private StringProperty denominacion = new SimpleStringProperty();
	private ObjectProperty<Nivel> nivel = new SimpleObjectProperty<Nivel>();
	
	public Conocimiento() {
		// TODO Auto-generated constructor stub
	}
	
	public Conocimiento(String den, Nivel niv) {
		denominacion.set(den);
		nivel.set(niv);
	}

	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}
	
	@XmlAttribute
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}
	

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	

	public final ObjectProperty<Nivel> nivelProperty() {
		return this.nivel;
	}
	
	@XmlAttribute
	public final Nivel getNivel() {
		return this.nivelProperty().get();
	}
	

	public final void setNivel(final Nivel nivel) {
		this.nivelProperty().set(nivel);
	}
	
	
	
	
}

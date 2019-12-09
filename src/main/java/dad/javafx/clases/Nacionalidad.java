package dad.javafx.clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Nacionalidad {
	
	StringProperty denominacion = new SimpleStringProperty();
	
	public Nacionalidad() {
		// TODO Auto-generated constructor stub
	}
	
	public Nacionalidad(String denom) {
		denominacion.set(denom);
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDenominacion();
	}
}

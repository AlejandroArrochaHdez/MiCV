package dad.javafx.clases;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import dad.javafx.utils.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Titulo {
	private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<LocalDate>();
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty organizador = new SimpleStringProperty();
	
	public Titulo() {
		// TODO Auto-generated constructor stub
	}
	
	public Titulo(LocalDate des, LocalDate has, String den, String org) {
		desde.set(des);
		hasta.set(has);
		denominacion.set(den);
		organizador.set(org);
	}

	public final ObjectProperty<LocalDate> desdeProperty() {
		return this.desde;
	}
	
	@XmlAttribute
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public final LocalDate getDesde() {
		return this.desdeProperty().get();
	}
	

	public final void setDesde(final LocalDate desde) {
		this.desdeProperty().set(desde);
	}
	

	public final ObjectProperty<LocalDate> hastaProperty() {
		return this.hasta;
	}
	
	@XmlAttribute
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public final LocalDate getHasta() {
		return this.hastaProperty().get();
	}
	

	public final void setHasta(final LocalDate hasta) {
		this.hastaProperty().set(hasta);
	}
	

	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}
	

	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}
	

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	

	public final StringProperty organizadorProperty() {
		return this.organizador;
	}
	

	public final String getOrganizador() {
		return this.organizadorProperty().get();
	}
	

	public final void setOrganizador(final String organizador) {
		this.organizadorProperty().set(organizador);
	}
	


	
}

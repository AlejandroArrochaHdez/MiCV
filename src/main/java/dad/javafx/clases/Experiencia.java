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
public class Experiencia {
	private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<LocalDate>();
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty empleador = new SimpleStringProperty();
	
	public Experiencia() {
		// TODO Auto-generated constructor stub
	}
	
	public Experiencia(LocalDate des, LocalDate has, String den, String emp) {
		desde.set(des);
		hasta.set(has);
		denominacion.set(den);
		empleador.set(emp);
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
	
	@XmlAttribute
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}
	

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	

	public final StringProperty empleadorProperty() {
		return this.empleador;
	}
	
	@XmlAttribute
	public final String getEmpleador() {
		return this.empleadorProperty().get();
	}
	

	public final void setEmpleador(final String empleador) {
		this.empleadorProperty().set(empleador);
	}
	
	
}

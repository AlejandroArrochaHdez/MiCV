package dad.javafx.clases;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

@XmlRootElement
public class CV {
	
	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>();
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>();
	private ObjectProperty<Titulo> formacion = new SimpleObjectProperty<Titulo>();
	private ObjectProperty<Experiencia> experiencias = new SimpleObjectProperty<Experiencia>();
	private ObjectProperty<Conocimiento> habilidades = new SimpleObjectProperty<Conocimiento>();
	
	public CV() {
		// TODO Auto-generated constructor stub
	}
	
	public CV(Personal pers, Contacto cont, Titulo tit, Experiencia exp, Conocimiento cono) {
		personal.set(pers);
		contacto.set(cont);
		formacion.set(tit);
		experiencias.set(exp);
		habilidades.set(cono);
	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}
	
	@XmlElement
	public final Personal getPersonal() {
		return this.personalProperty().get();
	}
	

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}
	

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	
	@XmlElement
	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}
	

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	

	public final ObjectProperty<Titulo> formacionProperty() {
		return this.formacion;
	}
	
	@XmlElement
	public final Titulo getFormacion() {
		return this.formacionProperty().get();
	}
	

	public final void setFormacion(final Titulo formacion) {
		this.formacionProperty().set(formacion);
	}
	

	public final ObjectProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}
	
	@XmlElement
	public final Experiencia getExperiencias() {
		return this.experienciasProperty().get();
	}
	

	public final void setExperiencias(final Experiencia experiencias) {
		this.experienciasProperty().set(experiencias);
	}
	

	public final ObjectProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}
	
	@XmlElement
	public final Conocimiento getHabilidades() {
		return this.habilidadesProperty().get();
	}
	

	public final void setHabilidades(final Conocimiento habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
	


}

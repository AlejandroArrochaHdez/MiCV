package dad.javafx.clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Web {
	
	private StringProperty url = new SimpleStringProperty();
	
	public Web() {
		// TODO Auto-generated constructor stub
	}
	
	public Web(String url) {
		this.url.set(url);
	}

	public final StringProperty urlProperty() {
		return this.url;
	}
	
	@XmlAttribute
	public final String getUrl() {
		return this.urlProperty().get();
	}
	

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}
	
	
	

}

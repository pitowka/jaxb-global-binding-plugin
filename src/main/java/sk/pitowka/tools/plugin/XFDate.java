package sk.pitowka.tools.plugin;

public class XFDate implements XmlFragment{
	private final String type;
	private final String adapter;
	
	public XFDate() {
		this("", "");
	}
	
	public XFDate(String type, String adapter) {
		this.type = type;
		this.adapter = adapter;
	}
	
	public XFDate withType(String newType) {
		return new XFDate(newType, this.adapter);
	}

	public XFDate withAdapter(String newAdapter) {
		return new XFDate(this.type, newAdapter);
	}

	@Override
	public String xmlFragment() {
		return !type.isEmpty() || !adapter.isEmpty() ?  String.format("<xjc:javaType name=\"%s\" xmlType=\"xs:date\" adapter=\"%s\"/>", type, adapter) : "";
	}
}

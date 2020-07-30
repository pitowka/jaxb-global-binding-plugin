package sk.pitowka.tools.plugin;

public class DateBindings implements XmlFragment{
	private final XmlFragment date;
	private final XmlFragment time;
	private final XmlFragment dateTime;
	
	public DateBindings() {
		this(new XFDate(), new XFDate(), new XFDate());
	}
	
	public DateBindings(XmlFragment date, XmlFragment time, XmlFragment dateTime) {
		this.date = date;
		this.time = time;
		this.dateTime = dateTime;
	}

	@Override
	public String xmlFragment() {
		return String.format("%s %s %s", date.xmlFragment(), time.xmlFragment(), dateTime.xmlFragment());
	}
}

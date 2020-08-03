package sk.pitowka.tools.plugin;

import java.io.ByteArrayInputStream;

import org.xml.sax.InputSource;

import com.sun.tools.xjc.Options;

public class PABindFile implements ParseArgument{
	private final ParseArgument delegate;
	
	public PABindFile() {
		this(new PAGlobalBinding());
	}
	
	private PABindFile(ParseArgument delegate) {
		this.delegate = delegate;
	}

	@Override
	public ParseArgument parseArgument(Options opt, String[] args, int i) {
		ParseArgument retVal = new PABindFile(delegate.parseArgument(opt, args, i));
		if(retVal.parsedArgument() != 0) {
			opt.compatibilityMode = Options.EXTENSION;
			InputSource is = new InputSource(new ByteArrayInputStream(retVal.xmlFragment().getBytes()));
			is.setSystemId("GlobalBindings");
			opt.addBindFile(is);
		}
		
		return retVal;
	}

	@Override
	public int parsedArgument() {
		return delegate.parsedArgument();
	}

	@Override
	public String xmlFragment() {
		return new StringBuilder()
			.append("<jxb:bindings\n" + 
					"    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" + 
					"    xmlns:jxb=\"http://java.sun.com/xml/ns/jaxb\"\n" + 
					"    xmlns:xjc=\"http://java.sun.com/xml/ns/jaxb/xjc\"\n" + 
					"    jxb:extensionBindingPrefixes=\"xjc\"\n" + 
					"    version=\"2.1\">\n")
			.append("<jxb:globalBindings>\n")
			.append(delegate.xmlFragment())
			.append("</jxb:globalBindings>\n").append("</jxb:bindings>\n")
			.toString();			
	}
}

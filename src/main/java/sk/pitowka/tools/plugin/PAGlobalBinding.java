package sk.pitowka.tools.plugin;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.xml.sax.InputSource;

import com.sun.tools.xjc.Options;

import sk.pitowka.tools.xjc.plugin.dates.PADates;
import sk.pitowka.tools.xjc.plugin.serializable.PASerializable;
import sk.pitowka.tools.xjc.plugin.simple.PASimple;

public class PAGlobalBinding implements ParseArgument{
	private final int parsedArgs;
	private final List<ParseArgument> pas;
	
	public PAGlobalBinding() {
		this(0, new PASimple(), new PASerializable(), PADates.DATE, PADates.TIME, PADates.DATE_TIME);
	}
	
	public PAGlobalBinding(int parsedArgs, ParseArgument... pas) {
		this(parsedArgs, Arrays.asList(pas));
	}

	public PAGlobalBinding(int parsedArgs, List<ParseArgument> pas) {
		this.parsedArgs = parsedArgs;
		this.pas = pas;
	}

	// TODO prekodit, nie je to pekne ani nestovatelne
	// pozor na na to, ze ked mam dane argumenty na konci, tak uz dalej ist nechcem a teda count != 0
	@Override
	public ParseArgument parseArgument(Options opt, String[] args, int i) {
		if("-Xgb".equals(args[i]) && parsedArgs == 0) {
			return new PAGlobalBinding(1, pas).parseArgument(opt, args, i + 1);
		}else if(parsedArgs != 0) {
			List<ParseArgument> newPas = pas.stream()
				.map(p -> p.parseArgument(opt, args, i))
				.collect(Collectors.toList());
			int count = newPas.stream().mapToInt(ParseArgument::parsedArgument).sum();
			PAGlobalBinding newGB = new PAGlobalBinding(parsedArgs + count, newPas);
			
			if(count == 0 || args.length <= i + count) {	// apply xmFragment
				opt.compatibilityMode = Options.EXTENSION;
				InputSource is = new InputSource(new ByteArrayInputStream(newGB.asXmlFragment().xmlFragment().getBytes()));
				is.setSystemId("GlobalBindings");
				opt.addBindFile(is);
			}
			
			return count != 0 && args.length > i + count ? newGB.parseArgument(opt, args, i + count) : newGB;
		}else {
			return this;
		}
	}
	
	@Override
	public XmlFragment asXmlFragment() {
		StringBuilder sb = new StringBuilder()
			.append("<jxb:bindings\n" + 
					"    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" + 
					"    xmlns:jxb=\"http://java.sun.com/xml/ns/jaxb\"\n" + 
					"    xmlns:xjc=\"http://java.sun.com/xml/ns/jaxb/xjc\"\n" + 
					"    jxb:extensionBindingPrefixes=\"xjc\"\n" + 
					"    version=\"2.1\"\n" + 
					">")
			.append("<jxb:globalBindings>\n");
		
		pas.stream().map(ParseArgument::asXmlFragment).map(XmlFragment::xmlFragment).forEach(sb::append);
		
		sb.append("</jxb:globalBindings>\n").append("</jxb:bindings>\n");
		
		return () -> sb.toString();
	}

	@Override
	public int parsedArgument() {
		return parsedArgs;
	}
}
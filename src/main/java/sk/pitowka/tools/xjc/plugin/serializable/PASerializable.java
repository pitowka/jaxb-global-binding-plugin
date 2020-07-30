package sk.pitowka.tools.xjc.plugin.serializable;

import com.sun.tools.xjc.Options;

import sk.pitowka.tools.plugin.ParseArgument;
import sk.pitowka.tools.plugin.XmlFragment;

// TODO tieto by sa dali dat ako boolean  ale zmienit nazov premennej
public class PASerializable implements ParseArgument {
	private final int parsedArgs;
	private final boolean isSerializable;
	
	public PASerializable() {
		this(0, false);
	}
	
	private PASerializable(int parsedArgs, boolean isSerializable) {
		this.parsedArgs = parsedArgs;
		this.isSerializable = isSerializable;
	}

	@Override
	public ParseArgument parseArgument(Options opt, String[] args, int i) {
		return "-serializable".equals(args[i]) ? new PASerializable(1, true) : new PASerializable(0, isSerializable);
	}

	@Override
	public XmlFragment asXmlFragment() {
		return () -> isSerializable ? "<xjc:serializable/>\n" : "";
	}
	
	@Override
	public int parsedArgument() {
		return parsedArgs;
	}
}

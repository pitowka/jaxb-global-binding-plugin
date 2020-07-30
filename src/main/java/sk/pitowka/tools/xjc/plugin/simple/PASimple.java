package sk.pitowka.tools.xjc.plugin.simple;

import com.sun.tools.xjc.Options;

import sk.pitowka.tools.plugin.ParseArgument;
import sk.pitowka.tools.plugin.XmlFragment;

public class PASimple implements ParseArgument {
	
	private final int parsedArgs;
	private final boolean isSimple;
	
	
	public PASimple() {
		this(0, false);
	}
	
	private PASimple(int parsedArgs, boolean isSimple) {
		this.parsedArgs = parsedArgs;
		this.isSimple = isSimple;
	}

	@Override
	public ParseArgument parseArgument(Options opt, String[] args, int i) {
		return "-simple".equals(args[i]) ? new PASimple(1, true) : new PASimple(0, isSimple);
	}
	
	@Override
	public XmlFragment asXmlFragment() {
		return () -> isSimple ? "<xjc:simple/>\n" : "";
	}

	@Override
	public int parsedArgument() {
		return parsedArgs;
	}
}

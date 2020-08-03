package sk.pitowka.tools.plugin;

import com.sun.tools.xjc.Options;

public interface ParseArgument {
	ParseArgument parseArgument(Options opt, String[] args, int i);
	
	int parsedArgument();
	
	String xmlFragment();
}

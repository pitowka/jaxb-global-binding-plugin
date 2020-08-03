package sk.pitowka.tools.plugin;

import java.io.IOException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.Outline;

public class XjcGlobalBindingPlugin extends Plugin{

	@Override
	public String getOptionName() {
		return "Xglobal-binding";
	}
	
	
	@Override
	public int parseArgument(Options opt, String[] args, int i) throws BadCommandLineException, IOException {
		return new PABindFile().parseArgument(opt, args, i).parsedArgument();
	}
	
	// TODO prepisat to na PAGlobalBinding metodu, lebo ona bude vediet, co vsetko sa robi
	@Override
	public String getUsage() {
		return new StringBuilder()
			.append("Usage: -Xgb").append("\n").append("\n")
			.append("Enable some of global binding features").append("\n").append("\n")
			.append("Options:").append("\n").append("\n")
			
			.append("\t").append("-simple").append("\n")
			.append("\t").append("\t").append("<xjc:simple/>").append("\n").append("\n")
			
			.append("\t").append("-serializable").append("\n")
			.append("\t").append("\t").append("<xjc:serializable/>").append("\n").append("\n")
			
			.append("\t").append("-no-element-property").append("\n")
			.append("\t").append("\t").append("<xjc:generateElementProperty>false</xjc:generateElementProperty>").append("\n").append("\n")
			
			.append("\t").append("-date-type").append("\n")
			.append("\t").append("\t").append("xjc:javaType name=value").append("\n")
			.append("\t").append("-date-adapter").append("\n")
			.append("\t").append("\t").append("xjc:javaType adapter=value").append("\n").append("\n")
			
			.append("\t").append("-dateTime-type").append("\n")
			.append("\t").append("\t").append("xjc:javaType name=value").append("\n")
			.append("\t").append("-dateTime-adapter").append("\n")
			.append("\t").append("\t").append("xjc:javaType adapter=value").append("\n").append("\n")
			
			.append("\t").append("-time-type").append("\n")
			.append("\t").append("\t").append("xjc:javaType name=value").append("\n")
			.append("\t").append("-time-adapter").append("\n")
			.append("\t").append("\t").append("xjc:javaType adapter=value").append("\n").append("\n")
			
			
			.toString();
	}

	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler) throws SAXException {
		return true;
	}
}

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
		return "Xgb";
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
			.append("Enable some of global binding features as cli options.").append("\n").append("\n")
			.append("Options:").append("\n").append("\n")
			
			.append("\t").append("-simple").append("\n")
			.append("\t").append("\t").append("add  <xjc:simple/>").append("\n").append("\n")
			
			.append("\t").append("-serializable").append("\n")
			.append("\t").append("\t").append("add <xjc:serializable/>").append("\n").append("\n")
			
			.append("\t").append("-no-element-property").append("\n")
			.append("\t").append("\t").append("add <xjc:generateElementProperty>false</xjc:generateElementProperty>").append("\n").append("\n")
			
			.append("\t").append("-date-type dateType").append("\n")
			.append("\t").append("\t").append("for xjc:javaType xmlType=\"xs:date\" set name=dateType").append("\n")
			.append("\t").append("-date-adapter dateTypeAdapter").append("\n")
			.append("\t").append("\t").append("for xjc:javaType xmlType=\"xs:date\" set adapter=dateTypeAdapter").append("\n").append("\n")
			
			.append("\t").append("-dateTime-type dateTimeType").append("\n")
			.append("\t").append("\t").append("for xjc:javaType xmlType=\"xs:dateTime\" set name=dateTimeType").append("\n")
			.append("\t").append("-dateTime-adapter dateTimeAdapter").append("\n")
			.append("\t").append("\t").append("for xjc:javaType xmlType=\"xs:dateTime\" set adapter=dateTimeAdapter").append("\n").append("\n")
			
			.append("\t").append("-time-type timeType").append("\n")
			.append("\t").append("\t").append("for xjc:javaType xmlType=\"xs:time\" set name=timeType").append("\n")
			.append("\t").append("-time-adapter timeAdapter").append("\n")
			.append("\t").append("\t").append("for xjc:javaType xmlType=\"xs:time\" set adapter=timeAdapter").append("\n").append("\n")
			
			.append("E.g.:").append("\t").append("-Xgb\\").append("\n\t")
			.append("-simple\\").append("\n\t")
			.append("-serializable\\").append("\n\t")
			.append("-date-type java.time.LocalDate -date-adapter package.XmlDateAdapter\\").append("\n\t")
			.append("-time-type java.time.LocalTime -time-adapter package.XmlTimeAdapter\\").append("\n\t")
			.append("-dateTime-type java.time.LocalDateTime -dateTime-adapter package.XmlDateTimeAdapter").append("\n").append("\n")
			
			.toString();
	}

	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler) throws SAXException {
		return true;
	}
}

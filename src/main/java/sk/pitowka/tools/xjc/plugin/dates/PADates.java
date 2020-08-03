package sk.pitowka.tools.xjc.plugin.dates;

import com.sun.tools.xjc.Options;

import sk.pitowka.tools.plugin.ParseArgument;

public class PADates implements ParseArgument {
	public static final PADates DATE = new PADates("xs:date", "-date-type", "-date-adapter");
	public static final PADates TIME = new PADates("xs:time", "-time-type", "-time-adapter");
	public static final PADates DATE_TIME = new PADates("xs:dateTime", "-dateTime-type", "-dateTime-adapter");
	
	private final int parsedArgs;
	private final String xsType;
	private final String typeKey;
	private final String adapterKey;
	private final String type;
	private final String adapter;
	
	private PADates(String xsType, String typeKey, String adapterKey) {
		this(0, xsType, typeKey, adapterKey, "", "");
	}
	
	private PADates(int parsedArgs, String xsType, String typeKey, String adapterKey, String type, String adapter) {
		this.parsedArgs = parsedArgs;
		this.xsType = xsType;
		this.typeKey = typeKey;
		this.adapterKey = adapterKey;
		this.type = type;
		this.adapter = adapter;
	}

	@Override
	public ParseArgument parseArgument(Options opt, String[] args, int i) {
		if(typeKey.equals(args[i])) {
			if(args.length <= i + 1 ) {
				throw new RuntimeException();
			}
			return withType(args[i+1]);
		}else if(adapterKey.equals(args[i])) {
			if(args.length <= i + 1 ) {
				throw new RuntimeException();
			}
			return withAdapter(args[i+1]);
		}else {
			return withZeroParsed();
		}
	}
	
	public PADates withType(String newTypeValue) {
		return new PADates(2, xsType, typeKey, adapterKey, newTypeValue, adapter);
	}
	
	public PADates withAdapter(String newAdapter) {
		return new PADates(2, xsType, typeKey, adapterKey, type, newAdapter);
	}
	
	public PADates withZeroParsed() {
		return new PADates(0, xsType, typeKey, adapterKey, type, adapter);
	}
	
	@Override
	public String xmlFragment() {
		return String.format("<xjc:javaType name=\"%s\" xmlType=\"%s\" adapter=\"%s\"/>\n", type, xsType, adapter);
	}

	@Override
	public int parsedArgument() {
		return parsedArgs;
	}
}

package sk.pitowka.tools.plugin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	private PAGlobalBinding(int parsedArgs, ParseArgument... pas) {
		this(parsedArgs, Arrays.asList(pas));
	}

	private PAGlobalBinding(int parsedArgs, List<ParseArgument> pas) {
		this.parsedArgs = parsedArgs;
		this.pas = pas;
	}

	// TODO prekodit, nie je to pekne ani nestovatelne
	// pozor na na to, ze ked mam dane argumenty na konci, tak uz dalej ist nechcem a teda count != 0
	
	// -Xgb moze byt dalsie trieda, ktora to bude delegovat na tuto, kde bude len to z else if() a else
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
			
			return count != 0 && args.length > i + count ? newGB.parseArgument(opt, args, i + count) : newGB;
		}else {
			return this;
		}
	}
	
	@Override
	public String xmlFragment() {
		return pas.stream().map(ParseArgument::xmlFragment).collect(Collectors.joining());
	}

	@Override
	public int parsedArgument() {
		return parsedArgs;
	}
}
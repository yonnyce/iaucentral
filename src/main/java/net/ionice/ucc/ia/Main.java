package net.ionice.ucc.ia;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		Console console = System.console();
		if (console == null && !GraphicsEnvironment.isHeadless()) {
			String filename = Main.class.getProtectionDomain().getCodeSource().getLocation()
					.toString().substring(6);
			Runtime.getRuntime()
					.exec(new String[] { "cmd", "/c", "start", "cmd", "/k", "java -jar \"" + filename + "\"" });
		} else {
			
			System.out.println("Program has ended, please type 'exit' to close the console");
		}
	}

}

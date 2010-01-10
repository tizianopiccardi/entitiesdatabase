package entitiesdb.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import testing.PutExamples;

import entitiesdb.gui.MainGUI;
import entitiesdb.query.QueryManager;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Main.consoleInterface();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void consoleInterface() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
	    String query = ""; 
		
	    System.out.println("=======================");
	    System.out.println("Entities DB - Console");
	    System.out.println("=======================\n");
	    
		boolean exit = false;
		while (!exit) {
			
			System.out.print("> ");
			query = br.readLine();
			switch (Commands.toEnum(query)) {
				case GUI:			new MainGUI(QueryManager.getDao()); break;
				case HELP:			System.out.println(Commands.getString());break;
				case EXIT:			exit = true; break;
				case ADD_EXAMPLES:	PutExamples.putExamples(QueryManager.getDao()); break;
				case DUMP: 			System.out.println(QueryManager.getDao().toString()); break;
				default:			System.out.println("\n"+QueryManager.query(query));
			}	
			
			
		}
		
		QueryManager.dao.close();
		System.out.println("Bye...");
		System.exit(0);
		
	}
	
	
	
	
	public static enum Commands {

		GUI, EXIT, DUMP, HELP, ADD_EXAMPLES, _QUERY;

		public static Commands toEnum(String str) {
			try {
				return valueOf(str.toUpperCase());
			} catch (Exception ex) {
				return _QUERY;
			}
		}
		
		public static String getString() {
			String out = "List of the commands:\n";
			Commands [] cList = Commands.values();
			for (int i = 0 ; i < cList.length ; i++)
				if (cList[i]!=_QUERY)
				out+=cList[i].name() + " | ";
			return out + "$x(...) :- $x(...)";
		}
		
	}
	
	

}

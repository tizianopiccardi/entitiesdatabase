package entitiesdb.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		
	    System.out.println("Entities DB - Console");
	    
		boolean exit = false;
		while (!exit) {
			
			System.out.print("> ");
			query = br.readLine();
			switch (Commands.toEnum(query)) {
				case GUI:		new MainGUI(QueryManager.getDao()); break;
				case HELP:		System.out.println(Commands.getString());break;
				case EXIT:		exit = true; break;
				case DUMP: 		System.out.println(QueryManager.getDao().toString()); break;
				default:		System.out.println(QueryManager.query(query));
			}
			
			
		}
		
		QueryManager.dao.close();
		System.out.println("Bye...");
		
	}
	
	
	
	
	public static enum Commands {

		GUI, EXIT, DUMP, HELP, _QUERY;

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

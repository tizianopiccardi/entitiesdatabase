package entitiesdb.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import testing.FillDb;
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
			//e.printStackTrace();
		}
		
	}
	
	
	public static void consoleInterface() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
	    String query = ""; 
		
	    System.out.println("=======================");
	    System.out.println("Entities DB - Console");
	    System.out.println("=======================");
	    
	    
	    System.out.print("Opening DB... ");
	    QueryManager.getDao();
	    System.out.println("Done!");
	    System.out.println("Type help for the commands list\n");
		boolean exit = false;
		while (!exit) {
			
			System.out.print("> ");
			query = br.readLine();
			if (!query.equals(""))
			switch (Commands.toEnum(query)) {
				case GUI:			new MainGUI(QueryManager.getDao()); break;
				case HELP:			System.out.println(Commands.getString());break;
				case EXIT:			exit = true; break;
				case ADD_EXAMPLES:	PutExamples.putExamples(QueryManager.getDao()); break;
				case DUMP: 			System.out.println(QueryManager.getDao().toString()); break;
				case PUT_RANDOM: 	System.out.print("\tNumber of entity? ");
									int entityNumber = Integer.valueOf(br.readLine());
									System.out.print("\tNumber of attribute for each entity? ");
									int attributeNumber = Integer.valueOf(br.readLine());
									FillDb.putExamples(QueryManager.getDao(), entityNumber, attributeNumber);
									break;
				case COUNT:			System.out.println(QueryManager.getDao().countToString()); break;
				default:			
					long start = System.currentTimeMillis();
					System.out.println("\n"+QueryManager.query(query));
					System.out.println("Response in: " + (System.currentTimeMillis()-start) + "ms");
			}	
			
			
		}
		
		QueryManager.dao.close();
		System.out.println("Bye...");
		System.exit(0);
		
	}
	
	
	
	
	public static enum Commands {

		GUI, EXIT, DUMP, HELP, ADD_EXAMPLES, PUT_RANDOM, COUNT, _QUERY;

		public static Commands toEnum(String str) {
			try {
				return valueOf(str.toUpperCase());
			} catch (Exception ex) {
				return _QUERY;
			}
		}
		
		public static String getString() {
			String out = "Commands list:\n";
			Commands [] cList = Commands.values();
			for (int i = 0 ; i < cList.length ; i++)
				if (cList[i]!=_QUERY)
				out+=cList[i].name() + " | ";
			return out + "$x(...) :- $x(...)";
		}
		
	}
	
	

}

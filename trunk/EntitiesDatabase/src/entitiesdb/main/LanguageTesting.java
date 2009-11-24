package entitiesdb.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.StringReader;


import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.lexer.Lexer;
import entitiesdb.language.node.Start;
import entitiesdb.language.parser.Parser;

public class LanguageTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
		String query = "";
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(reader);
		
		
		//Prendi la query da linea di comando...
		System.out.print("Datalog > ");
		query = in.readLine();
		
		//o scrivila qui:
		//query = "I7(name:$x) :- JB(lives:TRC(name:'Trento', locatedIn:TR, country:IT), married:CC), I5(name:$x, works_in: $y) ? $x='John', $y=JB(lives:'Trento')";
		
		DepthFirstAdapter code = new DepthFirstAdapter();
		
		Parser p = new Parser(new Lexer(new PushbackReader(new StringReader(query))));

		Start tree;
		
			tree = p.parse();
			tree.apply(code);
			System.out.println("SINTAX OK!");
		} catch (Exception e) {
			System.out.println("SINTAX ERROR ON: \n\t" +e.getMessage());
			
		} 
		
		
	}

}

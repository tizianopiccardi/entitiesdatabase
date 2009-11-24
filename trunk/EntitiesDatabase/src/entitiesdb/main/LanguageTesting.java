package entitiesdb.main;

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
		String query = "abc():-";
		
		DepthFirstAdapter code = new DepthFirstAdapter();
		
		Parser p = new Parser(new Lexer(new PushbackReader(new StringReader(query))));

		Start tree;
		try {
			tree = p.parse();
			tree.apply(code);
			System.out.println("SINTAX OK!");
		} catch (Exception e) {
			System.out.println("SINTAX ERROR ON: \n\t" +e.getMessage());
			
		} 
		
		
	}

}

package entitiesdb.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.StringReader;
import entitiesdb.dao.JEDao;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.lexer.Lexer;
import entitiesdb.language.node.Start;
import entitiesdb.language.parser.Parser;
import entitiesdb.query.evaluators.QueryEngine;


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
		

		
		query = "$x(has_attribute:$y):- $x(lives: $y) ? $x=$z";
		//query = ":- $a(lives: $x, married: I1, works: $y)";
		
		JEDao.open();
		
		DepthFirstAdapter code = new QueryEngine();
		
		Parser p = new Parser(new Lexer(new PushbackReader(new StringReader(query))));

		Start tree;
		
			tree = p.parse();
			tree.apply(code);
			System.out.println("SINTAX OK!");
			
		JEDao.close();
		
		} catch (Exception e) {
			System.out.println("SINTAX ERROR ON: \n\t" +e.getMessage());
			e.printStackTrace();
		} 

		
		
	}

}


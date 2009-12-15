package entitiesdb.main;

import java.io.File;
import java.io.PushbackReader;
import java.io.StringReader;

import entitiesdb.dao.EntitiesDAO;
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
		

		
		query = "$x(has_attribute:$y):- $x($lives: $y) ? $x=$z";
		
		
		EntitiesDAO dao = new EntitiesDAO(new File("db/"));
		
		QueryEngine code = new QueryEngine(dao);
		Parser p = new Parser(new Lexer(new PushbackReader(new StringReader(query))));

		Start tree;
		
		tree = p.parse();
		tree.apply(code);
		System.out.println("SINTAX OK!");
		
		
		dao.close();

		
		} catch (Exception e) {
			System.out.println("SINTAX ERROR ON: \n\t" +e.getMessage());
			e.printStackTrace();
		} 

		
		
	}

}


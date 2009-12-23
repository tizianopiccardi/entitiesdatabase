package entitiesdb.main;

import java.io.File;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.language.lexer.Lexer;
import entitiesdb.language.lexer.LexerException;
import entitiesdb.language.node.Start;
import entitiesdb.language.parser.Parser;
import entitiesdb.language.parser.ParserException;
import entitiesdb.query.evaluators.QueryEngine;


public class LanguageTesting {

	/**
	 * @param args
	 * @throws LexerException 
	 * @throws ParserException 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		
		
		try {
			
		String query = "";
		

		query = "$x(lives:$y, works:$z, alive: 'YES'):- $x(lives: $y, works:$z) ? $y = TN";
		//query = "$x(lives:$y, works:$z):- $x(lives: $y, works:$z) ";
		//query = ">ABC(p: 'fcdd', sdf: QWE, ds: 'ddfdf')";
		
		EntitiesDAO dao = new EntitiesDAO(new File("db/"));
		
		QueryEngine code = new QueryEngine(dao);
		Parser p = new Parser(new Lexer(new PushbackReader(new StringReader(query))));

		Start tree;
		
		tree = p.parse();
		tree.apply(code);
		System.out.println("SINTAX OK!");
		
		
		dao.close();

		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ParserException e) {
			System.out.println("Parser ERROR ON: \n\t" +e.getMessage());
			e.printStackTrace();
		}
		catch (LexerException e) {
			System.out.println("Lexer ERROR ON: \n\t" +e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}

		
		
	}

}


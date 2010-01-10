package entitiesdb.query;

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

public class QueryManager {

	public static EntitiesDAO dao = new EntitiesDAO(new File("db/"));
	
	public static EntitiesDAO getDao() {
		return dao;
	}
	public static ResultSet query(String datalog) {
		
		ResultSet out = null;
		try {

			QueryEngine engine = new QueryEngine(dao);
			Parser p = new Parser(new Lexer(new PushbackReader(new StringReader(datalog))));

			Start tree = p.parse();
			tree.apply(engine);
			
			out = engine.getResultSet();
			
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
		
		return out;
	}
	
}

package entitiesdb.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.StringReader;


import entitiesdb.dao.DaoException;
import entitiesdb.dao.JEDao;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.lexer.Lexer;
import entitiesdb.language.node.Start;
import entitiesdb.language.parser.Parser;
import entitiesdb.query.QueryEngine;

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
		
		/*
		//Prendi la query da linea di comando...
		//System.out.print("Datalog > ");
		//query = in.readLine();
		
		//Abitanti maschi di trento con nome
		query = "TNM(id:$x, name: $z) :- $x( lives: $y(name: 'Trento'), sex: 'M', name: $z )";
		
		//Tutti gli id degli sposati
		query = "MAR(id:$x) :- $x(married: $y)";

		//Chi ha un lavoro
		query = "W(id: $x) :- $x(works: $y)";

		//Trova chi si chiama John, vive a Trento e ha ruolo abc
		query = "$x :- $x(name: 'John', lives: $y), $z(role: 'abc') ? $z=$y , $y='Trento'";
		query = "$x :- $x(name: 'John', lives: 'Trento', role: 'abc'), $z(role: 'abc')";

		//Trova chi si chiama John e NON vive a Trento
		query = "$x :- $x(name: 'John', lives: $y) ? $y!='Trento'";

		//Tutti gli oggetti da uomo che costano più di 100
		query = "$x(descr: $k) :- $x(price: $y, description: $k, category: $z(for: MALE)) ? $y > 100";
		
		//query = "$x :- $x(name: $y)";
		
		//query = "$x :- $x(name: 'John', lives: TN)";*/
		
		query = "> ID3(name: 'Pincopallo', lives: TN, abc: 'ciao')";
		
		JEDao dao = new JEDao(new File("db/"));
		//dao.open();
		
		DepthFirstAdapter code = new QueryEngine(dao);
		
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

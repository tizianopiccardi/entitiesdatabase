package entitiesdb.main;

import entitiesdb.language.lexer.LexerException;
import entitiesdb.language.parser.ParserException;
import entitiesdb.query.QueryManager;


public class LanguageTesting {

	/**
	 * @param args
	 * @throws LexerException 
	 * @throws ParserException 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub

			
		String query = "";
		

		//query = "$x(lives:$y, works:$z, alive: 'YES'):- $x(lives: $y, works:$z) ? $y = $z";
		//query = "$x(lives:$y, works:$z, alive: 'YES'):- $x(lives: $y, works:$z) ? $y = TN";
		//query = "$x(lives:$y, works:$z, alive: 'YES'):- $x(lives: $y, works:$z) ? $y = TN, $z = H1";
		//query = "$x(lives:$y, works:$z, alive: $t):-  $x(lives: $y, works:$z), $a($c:$k), $g(lives:$t) ? $y=$t";
		//query = "$x(lives:$y, works:$z):- $x(lives: $y, works:$z) ";
		//query = ">ABC(p: 'fcdd', sdf: QWE, ds: 'ddfdf')";
		//query = ">I2(works: TN)";
		query = "ENTRIES(id:$x, attribute: $y, value: $z):-  $x($y:$z)";
		
		System.out.println(QueryManager.query(query));
		
		QueryManager.dao.close();
		
	}

}


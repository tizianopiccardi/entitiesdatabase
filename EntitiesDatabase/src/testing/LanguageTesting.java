package testing;

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
		

		PutExamples.putExamples(QueryManager.getDao());
		
		query = "$x($a:$b):- $x(lives: $y, works:$z, $a:$b)";
		//query = "$x(lives:$y, works:$z, alive: 'YES'):- $x(lives: $y, works:$z) ? $y = TN";
		//query = "$x(lives:$y, works:$z, alive: 'YES'):- $x(lives: $y, works:$z) ? $y = TN, $z = H1";
		//query = "$x(lives:$y, works:$z, alive: $t):-  $x(lives: $y, works:$z), $a($c:$k), $g(lives:$t) ? $y=$t";
		//query = "$x(lives:$y, works:$z):- $x(lives: $y, works:$z) ";
		//query = "+ ABC(p: 'fcdd', sdf: QWE, ds: 'ddfdf')";
		//query = "+ I2(works: TN)";
		
		//query = "$x :- $x(year: '1978', author: 'Miller')";
		
		//query = "$x(lives:$y, is_director_of: $z) :-  $x( works: $z(director_is: $j), lives:$y ) ? $j=$x";
		//query = "$x(lives:$y, is_director_of: $z) :-  $x( lives:$y, $w: $z(director_is: $j(married:I2)) )";
		//query = "$x(lives:$y, is_director_of: $z) :-  $x( works: $z(director_is: $j), lives:$y ) ? $j=$x";
		//query = "I2($x:$y) :- I2($x:$y)";
		//query = "ENTRIES(id:$x, attribute: $y, value: $z):-  $x($y:$z) | $x+";

		//query = "$x(lives:$y, is_director_of: $z) :-  $x( works: $z(director_is: $j), lives:$y ) ? $j=$x";
		//query = "$x(is_director_of: $z) :-  $x( works: $z(director_is: $j) ) ? $j=$x";
		//query = "$x(lives:$y, is_director_of: $z) :-  $x( lives:$y, works: $z(director_is: $j(married:I2)) )";
		
		//query = "$x(lives:$y, is_director_of: $z) :-  $x( works: $z(director_is: $j), lives:$y ) ? $j=$x | $x+";
		
		
		//query = "$x :- $x(year: '1983', author: 'Miller', title:'Entity model for dummies') ";
		//query = "$x :- $x(year: '1978', author: 'Miller', title:'Entity model for dummies') ";
		//query = "%$x :- $x(year: '1978', author: 'Miller', publisher: $z(city:MI, owner: $a(married:$s, name:'abc',z:a)))";
		query = "%$x :- $x(year: '1978', author: 'Miller', publisher: $z(city:MI, a:asd))";
		
		//query = "$x():- $x(lives: $y, works:$y) ";
		
		//query = "%$x :- $x(year: '1978', author: 'Miller', title: $x)";
		//query = "%$x :- $x(year: '1978', author: 'Miller', publisher: $z(city:MI, owner: $a(married:$s)))";
		//query = "- I2";
		//System.out.println(QueryManager.query(query));
		//query = "%$x :- $x(name: $a, city: $a, married:$d)";
		//query = "$x()! :- $x()";
		//System.out.println(query);
		System.out.println(QueryManager.query(query));	
		
		QueryManager.dao.close();
		
	}

}












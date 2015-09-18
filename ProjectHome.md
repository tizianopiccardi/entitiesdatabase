An entity model database engine.

Entry: <entityId, attributeName, value>
Query: Datalog

Engine: BerkeleyDB
Language: Java

```

Commands list:

help: shows the commands list
gui: opens a graphic interface
exit: closes the application
add_examples: fills the database with example data
dump: prints the database content
put_random: generate random data to fill the database
cout: prints the number of entities stored
$x(...):-$x(...): Follow some examples

(Like in a shell, you can get your last command with the up arrow key)
======================================================

Examples of queries

Insert:

	Add a new empty entity ID
		+ E1
		
	Add a new empty entity ID if it does not already exist and then add the attributes:
		+ E1(name: 'Ricky', surname: 'Smith')

Delete:

	Destroy an entity (itself and each references)
		- E1
		
	Delete an attribute
		- E1(name: 'Ricky')
		
	Delete all attributes of one entity
		- E1($x:$y)
	
Query:

	Easy queries	
		$x() :- $x()
		$x(lives: $y):- $x(lives: $y, works:$z)
	
	The list of the entities on descendant order (| $x-) where each ID will appear one time (!)
		$x() ! :- $x() | x-
		
	Cartesian product
		$x(lives:$y, works:$z):-  $x(lives: $y, works:$z), $a($b:$c)
		
	With conditions
		$x(is_director_of: $z) :-  $x( works: $z(director_is: $j) ) ? $j = $x
		$x(is_director_of: $z) :-  $x( works: $z(director_is: $j) ) ? $j = $x, $x = 'I1'
	
	Complex Query
		$x(lives:$y, is_director_of: $z) ! :-  $x( works: $z(director_is: $j), lives:$y ) ? $j=$x | $x+

		
Approximate Query:

		% $x :- $x(year: '1978', author: 'Miller')
		% $x :- $x(year: '1978', author: 'Miller', publisher: $y(city:MI, owner: $z(married:$k, name:'abc')))
```
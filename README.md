# CS310 Programming Assignment 02
## Tree Dictionary 

Many data (e.g, records of movies, songs, video games, etc) come with keywords so these data can be eaily found using keywords. 
You are tasked to implement a binary tree structure that allows querys composed of one or multiple keywords.
For example, you can find pokemons using their attacts as illustrated below.

```
Welcome to CS310 PA02: Tree Dictionary
Read 124 records from json/pokemon.json
Options:
	f: find records
	p: print the tree
	q: quit
> f
> Provide keywords (seprate by ,): thundershock, spark
> Found 2 records
> ... [Magnemite, (tackle, thundershock, spark)]
> ... [Magneton, (tackle, thundershock, spark, zap cannon)]
```

Note, we use an external library called **json-simple-1.1.1.jar**. Therefore, to compile the code from command line, you need:
```
javac -cp .:json-simple-1.1.1.jar cs310pa2.java
```

To run the code, you need:
```
java -cp .:json-simple-1.1.1.jar  cs310pa2 -name name -keyword moves -max 10 json/pokemon.json
```

A script, called ["run"](./run), helps compile and run the examples. 

## Examples

- [Example with 12 Pokemons](examples/pokemon-01.txt)
- [Example with 124 Pokemons](examples/pokemon-02.txt)
- [Example with 12 movies using genres](examples/movie-genre-01.txt)
- [Example with 369 movies using genres](examples/movie-genre-02.txt)
- [Example with 12 movies using actors](examples/movie-actors-01.txt)
- [Example with 369 movies using actors](examples/movie-actors-02.txt)
- [Example with Animal Crossing](examples/animal-crossing.txt)
- [Example with Tech Books](examples/tech-book.txt)


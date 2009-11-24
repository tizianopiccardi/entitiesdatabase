cd src\entitiesdb\language\analysis\
rm *.*
cd ..\lexer
rm *.*
cd ..\node
rm *.*
cd ..\parser
rm *.*

cd ..\..\..\..

java -jar sablecc.jar src\language.sablecc
pause
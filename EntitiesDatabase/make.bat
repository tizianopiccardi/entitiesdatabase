cd src\entitiesdb\language\analysis\
rm *.*
del *.*
cd ..\lexer
rm *.*
del *.*
cd ..\node
rm *.*
del *.*
cd ..\parser
rm *.*
del *.*

cd ..\..\..\..

java -jar sablecc.jar src\language.sablecc
pause
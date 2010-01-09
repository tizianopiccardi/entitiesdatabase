cd src\entitiesdb\language\analysis\
rm *.*
del *.* /Q
cd ..\lexer
rm *.*
del *.* /Q
cd ..\node
rm *.*
del *.* /Q
cd ..\parser
rm *.*
del *.* /Q

cd ..\..\..\..

java -jar sablecc.jar src\language.sablecc
pause
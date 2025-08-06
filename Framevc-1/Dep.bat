SET "projet=framevc-1"
SET "projet_mere=Framevc-1"
SET "librairies=lib"
SET "librairies_temp=%projet%/lib"
SET "classFile=src/class"
SET "java_dir=src/org/springcopy"
@REM SET "test_dir=../test/lib"
SET "test_dir=D:\cour\web-dynamique\TICKETING\ticket\lib"
SET "page=src/org/page"

@REM javac -parameters  --release 20  -cp "%librairies%/"*;"%librairies_temp%" -d "%projet%" "%java_dir%/core/"*.java "%java_dir%/annote/"*.java "%java_dir%/exception/"*.java 
javac -parameters  -cp "%librairies%/"*;"%librairies_temp%" -d "%projet%" "%java_dir%/core/"*.java "%java_dir%/annote/"*.java "%java_dir%/exception/"*.java 
xcopy "%page%" "%projet%/org/page" /e /i /c /h /y


cd "%projet%"
jar -cvf "../%projet%.jar" org/*

copy "..\%projet%.jar" "%test_dir%"

pause
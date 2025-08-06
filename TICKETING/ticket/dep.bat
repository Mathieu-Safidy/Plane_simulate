@echo off
setlocal EnableDelayedExpansion

SET "projet=seo"
SET "lib_path=lib"
SET "path_class=src"
SET "webbapps=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
SET "xml_path=web\xml"
SET "config_path=web\config"
SET "file_temp=temp"
SET "temp_lib=temp\WEB-INF\lib"
SET "temp_class=temp\WEB-INF\classes"
SET "temp_xml=temp\WEB-INF"
SET "page_web=web\page"
SET "assets=web\assets"
SET "config=src\config"

xcopy "%lib_path%" "%temp_lib%" /e /i /c /h /y
xcopy "%xml_path%" "%temp_xml%" /e /i /c /h /y
xcopy "%page_web%" "%file_temp%/page" /e /i /c /h /y
xcopy "%assets%" "%file_temp%/assets" /e /i /c /h /y
@REM xcopy "%temp_class%" "%config_path%" /e /i /c /h /y
xcopy "%config%" "%file_temp%/WEB-INF/classes/config" /e /i /c /h /y

@REM javac -parameters -cp "%lib_path%/"*;"%temp_lib%" -d "%temp_class%" "%path_class%/cont/"*.java
@REM "C:\Program Files\Java\jdk-22\bin\javac.exe" -parameters --release 20 -cp "%lib_path%/"*;"%temp_lib%" -d "%temp_class%" "%path_class%/entity/**/"*.java "%path_class%/cont/"*.java
@REM "C:\Program Files\Java\jdk-22\bin\javac.exe" -verbose -parameters --release 20 -cp "%lib_path%/"*;"%temp_lib%" -d "%temp_class%" "%path_class%/cont/Acciel.java"
set "FILES_LIST=files.txt"  

rem Nettoyer le fichier files.txt s'il existe
if exist %FILES_LIST% del %FILES_LIST%

rem Lister tous les fichiers .java dans PATH_CLASS et remplacer les \ par \\
for /r "%PATH_CLASS%" %%f in (*.java) do (
    set "file=%%f"
    rem Remplacer \ par \\
    set "file=!file:\=\\!"
    echo !file! >> %FILES_LIST%
)
@REM set "CLASSPATH="
@REM for %%j in ("%LIB_PATH%\*.jar") do (
@REM     if defined CLASSPATH (
@REM         set "CLASSPATH=!CLASSPATH!;%%~fj"
@REM     ) else (
@REM         set "CLASSPATH=%%~fj"
@REM     )
@REM )

@REM rem Ajout des JARs du dossier TEMP_LIB (chemins absolus)
@REM for %%j in ("%TEMP_LIB%\*.jar") do (
@REM     set "CLASSPATH=!CLASSPATH!;%%~fj"
@REM )

dir %lib_path%
dir %temp_lib%

@REM jar tf temp\WEB-INF\lib\framevc-1.jar
rem Compilation des fichiers Java avec le classpath construit
javac -parameters -cp "%lib_path%\*;%temp_lib%\*" -d "%TEMP_CLASS%" @files.txt
cd %file_temp%

jar -cvf "%webbapps%/%projet%.war" WEB-INF/* page/* assets/* 

cd ..

@REM rmdir /s /q %file_temp%

pause

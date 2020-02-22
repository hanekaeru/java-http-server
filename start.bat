cls
@echo off
echo Compilation...
javac Serveur.java
echo Compilation finished!
echo Launching program...
echo ===
set /p port=Choose a port for the HTTP Server:
echo Server started on port %port%
java Serveur %port%
pause
    
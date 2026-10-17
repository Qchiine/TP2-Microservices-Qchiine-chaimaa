@echo off
title TP-Spring-IOC
mode con: cols=92 lines=54
chcp 65001 >nul
set "JAVA_HOME=C:\Program Files\Java\jdk-17"
set "MAVEN_OPTS=-Dfile.encoding=UTF-8"
cd /d "%~dp0"
echo.
echo ================================================================
echo   TP 2 - Spring IoC : compilation puis execution
echo ================================================================
call mvn -B -q clean compile
if errorlevel 1 goto erreur
call mvn -B -q exec:java
goto fin
:erreur
echo.
echo La compilation a echoue.
:fin
echo.
pause
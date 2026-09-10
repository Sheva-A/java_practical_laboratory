@echo off
chcp 65001 > nul
java -jar "%~dp0target\converter.jar"
pause

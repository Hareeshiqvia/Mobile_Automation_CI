set mypath=%cd%
@echo %mypath%
java -jar %mypath%/jars/selenium-server-standalone-2.52.0.jar -role hub http://127.0.0.1:4444/grid/console
pause
set mypath=%cd%
@echo %mypath%
java -cp %mypath%\jars\*;%mypath%\bin org.testng.TestNG testng.xml
pause
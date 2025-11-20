@echo off
echo Compiling Smart Bank Pro...
javac -cp src src\BankAccountTest.java src\Account.java src\Sav_acct.java src\Curr_acct.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Running Smart Bank Pro Test...
java -cp src BankAccountTest

pause


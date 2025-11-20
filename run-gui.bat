@echo off
echo Compiling Smart Bank Pro GUI...
javac -cp src src\BankingGUI.java src\Account.java src\Sav_acct.java src\Curr_acct.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Starting Smart Bank Pro GUI...
java -cp src BankingGUI

pause


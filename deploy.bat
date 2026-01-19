@echo off
echo Building WAR file...
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Copying .env file to webapps/demo...
if exist .env (
    copy .env C:\xampp\tomcat\webapps\demo\.env >nul 2>&1
    echo .env file copied successfully.
) else (
    echo WARNING: .env file not found in demo directory!
)

echo.
echo Copying WAR to Tomcat webapps...
copy target\demo.war C:\xampp\tomcat\webapps\demo.war
echo.
echo Deployment complete! Restart Tomcat in XAMPP Control Panel.
pause

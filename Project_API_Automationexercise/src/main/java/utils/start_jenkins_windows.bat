@echo off
REM Change to Jenkins folder
cd /d "G:\Soft Setup\Jenkins"

REM Temporarily set JAVA_HOME to JDK 21 (adjust the path if needed)
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

REM Step 3: Start Jenkins in background
start "Jenkins Server" cmd /c "java -jar jenkins.war --httpPort=9191"

REM Step 4: Wait for Jenkins to be up (check localhost:9191)
echo Waiting for Jenkins to start...
:waitloop
timeout /t 3 >nul
curl -s http://localhost:9191/login >nul 2>&1
if errorlevel 1 (
    goto waitloop
)

REM Step 5: Open browser when Jenkins is reachable
start http://localhost:9191/
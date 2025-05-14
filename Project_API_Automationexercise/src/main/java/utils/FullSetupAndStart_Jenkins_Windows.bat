@echo off
SETLOCAL ENABLEDELAYEDEXPANSION

REM === CONFIGURATION ===
set "JDK_URL=https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.zip"
set "JENKINS_URL=https://get.jenkins.io/war-stable/latest/jenkins.war"
set "INSTALL_DIR=%~dp0jenkins_setup"
set "JENKINS_WAR=%INSTALL_DIR%\jenkins.war"

REM === CREATE FOLDERS ===
mkdir "%INSTALL_DIR%" 2>nul

REM === STEP 1: DOWNLOAD AND EXTRACT JDK 21 ===
echo Downloading JDK 21...
curl -L -o "%INSTALL_DIR%\jdk21.zip" "%JDK_URL%"
powershell -Command "Expand-Archive -Force '%INSTALL_DIR%\jdk21.zip' '%INSTALL_DIR%'"

REM === STEP 2: FIND EXTRACTED JDK FOLDER ===
set "JDK_DIR="
for /D %%D in ("%INSTALL_DIR%\jdk-*") do (
    set "JDK_DIR=%%D"
)

REM === STEP 3: SET JAVA PATH TEMPORARILY ===
set "JAVA_HOME=!JDK_DIR!"
set "PATH=!JAVA_HOME!\bin;%PATH%"

REM === STEP 4: DOWNLOAD JENKINS WAR ===
echo Downloading Jenkins WAR...
curl -L -o "%JENKINS_WAR%" "%JENKINS_URL%"

REM === STEP 5: START JENKINS ===
echo Starting Jenkins...
start "Jenkins Server" cmd /c "java -jar \"%JENKINS_WAR%\" --httpPort=9191"

REM === STEP 6: WAIT FOR JENKINS AND OPEN BROWSER ===
echo Waiting for Jenkins to start...
:waitloop
timeout /t 3 >nul
curl -s http://localhost:9191/login >nul 2>&1
if errorlevel 1 (
    goto waitloop
)

start http://localhost:9191/
echo Jenkins is running!

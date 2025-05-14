#!/bin/bash

# === CHECK DEPENDENCIES ===
for cmd in curl tar xdg-open; do
    if ! command -v $cmd &> /dev/null; then
        echo "Error: '$cmd' is not installed. Please install it and rerun the script."
        exit 1
    fi
done

# === CONFIGURATION ===
JDK_URL="https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz"
JENKINS_URL="https://get.jenkins.io/war-stable/latest/jenkins.war"
INSTALL_DIR="$HOME/jenkins_setup"
JENKINS_WAR="$INSTALL_DIR/jenkins.war"

# === CREATE INSTALLATION FOLDER ===
mkdir -p "$INSTALL_DIR" || { echo "Cannot create $INSTALL_DIR"; exit 1; }
cd "$INSTALL_DIR" || exit 1

# === DOWNLOAD & EXTRACT JDK 21 ===
echo "Downloading JDK 21..."
curl -L -o jdk21.tar.gz "$JDK_URL" || { echo "Failed to download JDK"; exit 1; }

echo "Extracting JDK..."
tar -xzf jdk21.tar.gz || { echo "Failed to extract JDK"; exit 1; }
rm -f jdk21.tar.gz

# === DETECT JDK FOLDER ===
JDK_DIR=$(find "$INSTALL_DIR" -maxdepth 1 -type d -name "jdk-21*")
if [ -z "$JDK_DIR" ]; then
    echo "JDK folder not found after extraction."
    exit 1
fi

export JAVA_HOME="$JDK_DIR"
export PATH="$JAVA_HOME/bin:$PATH"

# === VERIFY JAVA ===
echo "Using Java from: $JAVA_HOME"
java -version || { echo "Java not found or failed to run"; exit 1; }

# === DOWNLOAD JENKINS WAR ===
echo "Downloading Jenkins WAR..."
curl -L -o "$JENKINS_WAR" "$JENKINS_URL" || { echo "Failed to download Jenkins"; exit 1; }

# === START JENKINS ===
echo "Starting Jenkins on http://localhost:9191 ..."
nohup java -jar "$JENKINS_WAR" --httpPort=9191 > "$INSTALL_DIR/jenkins.log" 2>&1 &

# === WAIT FOR JENKINS TO BE READY ===
echo "Waiting for Jenkins to start (this may take 30–60 seconds)..."
until curl -s http://localhost:9191/login > /dev/null; do
    sleep 3
done

# === OPEN BROWSER ===
xdg-open http://localhost:9191/ > /dev/null 2>&1 &

echo "✅ Jenkins is running on: http://localhost:9191/"

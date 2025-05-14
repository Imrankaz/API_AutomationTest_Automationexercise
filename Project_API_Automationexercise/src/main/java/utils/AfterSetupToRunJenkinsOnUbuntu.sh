#!/bin/bash

# === UPDATE PACKAGE LIST ===
echo "Updating package list..."
sudo apt update

# === INSTALL curl ===
echo "Installing curl..."
sudo apt install curl -y

# === MAKE jenkins_setup.sh EXECUTABLE ===
chmod +x jenkins_setup_ubuntu.sh

# === RUN jenkins_setup.sh ===
echo "Running Jenkins setup..."
./jenkins_setup_ubuntu.sh

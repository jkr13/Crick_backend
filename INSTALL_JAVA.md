# Installing Java 8 for CrickPulseBackend

## Quick Start (Recommended)

### Step 1: Install Homebrew (if not already installed)
Run in Terminal:
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### Step 2: Install OpenJDK 8
```bash
brew install openjdk@8
```

### Step 3: Add Java to PATH
For Apple Silicon Macs (M1/M2):
```bash
echo 'export PATH="/opt/homebrew/opt/openjdk@8/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

For Intel Macs:
```bash
echo 'export PATH="/usr/local/opt/openjdk@8/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### Step 4: Verify Installation
```bash
java -version
```
You should see output like: `openjdk version "1.8.x"`

### Step 5: Run the Project
```bash
cd /Users/keyur/Project/Crickt_live/CrickPulseBackend
java -jar target/CrickPulseBackend.jar
```

Or if Maven is installed:
```bash
mvn spring-boot:run
```

---

## Alternative: Manual Installation (Without Homebrew)

1. Visit: https://adoptium.net/temurin/releases/?version=8
2. Download macOS .pkg installer (choose your architecture: ARM64 for M1/M2, x64 for Intel)
3. Run the installer
4. Verify with: `java -version`
5. Run the project as shown above

---

## After Installation

Once Java is installed, the application will run on **port 8081**.

You can access:
- API endpoints: http://localhost:8081
- Swagger UI (if configured): http://localhost:8081/swagger-ui.html

# Employee Payroll & HR Management System

## Setup Instructions

### Prerequisites
- XAMPP (MySQL + Tomcat)
- Java JDK 8+
- Maven

### Database Setup

1. Start MySQL in XAMPP Control Panel
2. Open phpMyAdmin (http://localhost/phpmyadmin)
3. Import `sql/schema.sql` to create the database and tables

### Build & Deploy

**Option 1: Using deploy script (Windows)**
```bash
cd demo
deploy.bat
```

**Option 2: Manual steps**
```bash
# Build WAR
mvn clean package -DskipTests

# Copy WAR to Tomcat
copy target\demo.war C:\xampp\tomcat\webapps\demo.war

# Copy .env file (required for encryption keys)
copy .env C:\xampp\tomcat\webapps\demo\.env
```

### Environment Variables

The `.env` file contains encryption keys. It must be copied to `C:\xampp\tomcat\webapps\demo\.env` after deployment.

If `.env` is missing, generate new keys:
```bash
# Generate AES key (32 bytes)
openssl rand -base64 32

# Generate 3DES key (24 bytes)
openssl rand -base64 24

# Generate RSA keypair
openssl genpkey -algorithm RSA -pkeyopt rsa_keygen_bits:2048 -out private.pem
openssl rsa -pubout -in private.pem -out public.pem
# Then base64 encode the DER format keys
```

### Running the Application

1. Start MySQL and Tomcat in XAMPP Control Panel
2. Wait for Tomcat to unpack the WAR (check `C:\xampp\tomcat\webapps\demo\` folder)
3. Open browser: http://localhost:8080/demo/

### Troubleshooting

**404 Error:**
- Ensure Tomcat is running
- Check that `demo.war` exists in `C:\xampp\tomcat\webapps\`
- Verify `.env` file is in `C:\xampp\tomcat\webapps\demo\.env`
- Check Tomcat logs: `C:\xampp\tomcat\logs\catalina.out`

**Database Connection Error:**
- Verify MySQL is running on port 3306
- Check database `payroll_hr_db` exists
- Verify root user has empty password (or update `DbConnection.java`)

**Encryption Key Errors:**
- Ensure `.env` file is in the deployed `demo` folder
- Check that all 4 keys are present: AES_KEY_B64, TDES_KEY_B64, RSA_PUBLIC_KEY_B64, RSA_PRIVATE_KEY_B64

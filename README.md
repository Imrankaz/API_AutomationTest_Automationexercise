--------------------------------
 Project_API_Automationexercise
--------------------------------


This project is built using Java 21 and Maven for API test automation with REST Assured and JUnit 5.
It includes Allure for advanced reporting and support for sending test reports via email.

--------------------------------------------------
DEPENDENCIES USED (Defined in pom.xml)
--------------------------------------------------


Java version: 21
Maven compiler plugin: 21 (source and target)

Dependencies:
-------------
1. Rest Assured - 5.3.2
2. JSON Path - 5.3.0 (used with RestAssured)
3. JUnit Jupiter (JUnit 5) - 5.10.0
4. Java Mail API - 1.6.2
5. Jackson Databind (JSON serialization) - 2.17.0
6. Allure JUnit5 Adapter - 2.28.0
7. Allure RestAssured Integration - 2.27.0
8. ExtentReports - 5.1.1 (for HTML reporting)
9. Flying Saucer PDF (HTML to PDF export) - 9.1.22

Maven Plugins:
---------------
1. Maven Surefire Plugin - 3.0.0-M9
2. Allure Maven Plugin - 2.14.0
3. Maven Surefire Report Plugin - 3.2.5

--------------------------------------------------
COMMANDS TO RUN TESTS AND GENERATE REPORTS
--------------------------------------------------


1. Run only tests:
   mvn clean test

2. Run full lifecycle and generate Allure report:
   mvn clean verify

3. Serve Allure report in default browser (live):
   allure serve allure-results


Note:
- Allure CLI must be installed and added to your system PATH.
- Download Allure CLI from: https://docs.qameta.io/allure/#_installing_a_commandline
--------------------------------------------------
EMAIL REPORTING SETUP
--------------------------------------------------


This project supports **automated email reporting** using JavaMail API.

-----------------------------
OPTION 1: Send Email from IDE
-----------------------------


1. Set up an email utility class using `javax.mail`.
3. Update the Credentials file for Gmail. 
	Add valid senderEmail,
	Add valid appPass,
	Add valid mail_smtp_host,
	Add valid mail_smtp_port,
	Add valid receiverEmail,
	

2. Use the following SMTP settings for Gmail (or adjust for other providers):

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("your-email@gmail.com", "your-app-password");
        }
    });
	Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        //InternetAddress.parse("receiverEmail@example.com") // Email Receiver
                        InternetAddress.parse(Credentials.receiverEmail) // Email Receiver

                );


**Note:**
- If using Gmail, enable 2FA and generate an App Password.
- Allow "Less Secure Apps" if needed (not recommended for production).

---------------------------------
OPTION 2: Send Email from Jenkins
---------------------------------

1. Install **Email Extension Plugin** in Jenkins.

2. Go to:  
   **Manage Jenkins > Configure System > Extended E-mail Notification**

3. Configure:
   - SMTP Server: `smtp.gmail.com`
   - Use SMTP Authentication: ✅
   - User Name: `your-email@gmail.com`
   - Password: App Password or Jenkins Credential
   - SMTP Port: 587
   - Enable TLS: ✅

4. Add **Editable Email Notification** to your Jenkins job (Post-Build Action)

5. Configure:
   - Recipients: your-email@example.com
   - Attachments (optional): `target/allure-report/index.html` or generated PDF
   - Content: Use `$DEFAULT_CONTENT` or custom HTML message
   - Trigger: "Always" or "Unstable/Failure" as needed

6. Make sure the Jenkins job generates the report before sending.

--------------------------------------------------
BEST PRACTICES
--------------------------------------------------


- Always run `mvn clean` before tests to avoid stale results.
- Delete `target/` and `allure-results/` if tests/classes were renamed or removed.
- Use `mvn clean verify` to ensure plugins like Allure run automatically.

--------------------------------------------------

Author: [Md. Imran]  
Project: API Test Automation for https://automationexercise.com
Git Project link: https://github.com/Imrankaz/API_AutomationTest_Automationexercise.git

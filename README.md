# commtest

### In CI

1. I have configured this to run in Github actions against the feature branch for each check in
2. Please look in https://github.com/ShivBala/commtest/actions
3. Report can be found as an attached artifact against the jobs. I have used ExtentReports for reporting.
4. Please find a sample job and attached artifact here https://github.com/ShivBala/commtest/actions/runs/10033573249#artifacts

### To edit and run the tests locally

1. install jdk 11 and maven 
2. make sure the below commands
   - java -version 
   - mvn --version
   returns the versions of Java and Maven respectively
3. Clone the repo from 'https://github.com/ShivBala/commtest.git'
4. Checkout the branch 'feature/SivakumarBalakrishnan-CBATest'
5. Navigate to the project folder in terminal and run the maven command 'mvn clean install' 
6. Open the petApiTests.html located in the target folder for results
7. I used ExtentReports for html reports. Surefire plugg-in is also configured and surefire reports are available

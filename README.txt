This repo contains the automation test suite to validate the web app http://computer-database.herokuapp.com/computers

Instructions to run the suite:

Pre-requisites:
Make sure you have 'Eclipse Java Oxygen' installed on your machine
Have Chrome browser installed.

Checkout Code and configure project:
1. Open your command line or Terminal application and enter the directory where you would like to copy the repository. 
    For example: cd D:\test
2. Clone the repository
     git clone https://github.com/newdaton/test-computer-database.git
3. Open eclipse, create new java project with project source as the "computer-database" folder in the content checked-out in step2. 
4. Right click on the project in eclipse, goto BuildPath > Configure Buildpath. Click "Add External Libraries" and choose libraries from the "libs" folder in the project checked out in step2. Click "Apply and close"

Run the tests:
1. Click on AutomatedTests.java and Right click > Run As Junit test
2. You should see all tests being run and their results in JUnit tab in eclipse.

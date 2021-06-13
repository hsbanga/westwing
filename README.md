Cucucmber BDD UI Automation Framework
=====================================

This framwork has been design with help of TestNG, Cucumber BDD tool with page object model design for web based applications.

Prerequisites
-------------
```
Good Level of Understanding of Cucumber and Java and little bit of TestNG as well.
System Requiriment: Java8, Maven and eclipse should be installed.
Eclipse Requiriment:TestNG and Cucucmber plugin must be installed from eclipse Marketplace.
```

Understand the Project structure
--------------------------------
```
Java Directory(src\test\java)- Contains all the Java class files.
Resources Directory(src\test\resources)- Contains non-java(other) files.
Json Report - inside 'target' folder, generats post execution.
XML report - inside target\testng-cucumber-reports folder, generats post execution.
Masterthought's HTML cucumber Report - inside 'ProjectReports' folder, generats post execution.
```

CLA runtime Parameters
----------------------
```
BROWSER - default: chrome
TEST_ENV - default:prod
IMPLICIT_WAIT - default:0
TAKE_ALL_SCREENSHOTS - default:false
UPLOAD_QMETRY_REPORT - default:false

For Parallel execution provide the 'dataproviderthreadcount' as CLA with maven build command.
If Not Pass 'dataproviderthreadcount' it will execute single instance at once i.e. sequentially.
Example:mvn verify -Ddataproviderthreadcount=10(Number of browser instance need to be run in parallel)

Maven Build command Example: "mvn verify -DBROWSER=firefox -DTEST_ENV=stag"
```
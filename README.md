# QA Automation

This repository contains QA automation for the AppSierra. It uses Selenium, via a local Chrome Driver or 
a Remote Driver that connects to a Selenium Grid.

## Running Java and Maven on host

To run the test on host you'll need to install Java (11+) and Maven. For this reason it's not recommended
unless you know what you're doing. Once you've installed Java, Maven and possibly the Chrome Driver, run test as above.

## Running using your host Chrome installation

To run tests against your host Chrome Browser you'll need to download the latest 
[Chrome Driver](https://chromedriver.chromium.org/downloads) for your platform. If you're running on Linux or 
Mac, ensure the Driver is executable.  

Specify the Chrome Driver when running the test.

```shell script
mvn test -DconfigPath=./env/prod.properties -Dwebdriver.chrome.driver=/path/to/chromedriver -DGroups=GroupName
```
- To Run on a specific environment. Add a property file under `env` folder. Command to run specific env: `-DconfigPath=./env/{environment_name}.properties`
- Can group test cases using tag `@Tag(tag-name)`. Command to run a specific group of test cases: `-DGroups=GroupName`
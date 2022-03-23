package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"pretty",
                "html:target/html-reports/cucumber.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:target/ApiFailedRunner.txt"
        },
        features = "src/test/resources/apiFeatures",
        glue = "api/rawg/stepDefinitions",
        tags = "@apiTest",
        dryRun = false,
        stepNotifications = false,
        monochrome = false,
        publish = true
)
public class ApiRunner {
}

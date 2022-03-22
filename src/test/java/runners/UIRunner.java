package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/html-reports/cucumber.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml"
        },
        features = "src/test/resources/la3ebFeatures",
        glue = "com/la3eb/stepDefinitions",
        tags = "@wip1",
        dryRun = false,
        stepNotifications = false, // to see report gherkin step level
        monochrome = false //
        //publish = true # it is to get online report
)
public class UIRunner {
}

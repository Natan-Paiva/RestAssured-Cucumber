package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//add "tags" to run only selected scenarios , tags = "@DeletePlace"
//select specific feature file, features = "src/test/java/features/file.feature"
@CucumberOptions(features = "src/test/java/features",plugin ="json:target/jsonReports/cucumber-report.json", glue = {"stepDefinitions"})
public class TestRunner {

}

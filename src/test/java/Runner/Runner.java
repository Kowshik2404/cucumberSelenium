package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features", glue = { "Implementation" }, monochrome = true, plugin = {
		"pretty", "html:target/HtmlReports/report.html",
		// "json","json:target/JsonReports/report.json",
		// "pretty","junit:target/XMLReports/reprot.xml",
}, tags = "@Smoke", dryRun = false)

public class Runner {

}

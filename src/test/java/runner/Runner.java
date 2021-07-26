package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    /**
     * This is a runner class that is used to run the test cases
    */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@smoke",        // available tags: @regression , @smoke , @ui , @api
        dryRun = false
)

public class Runner {
}

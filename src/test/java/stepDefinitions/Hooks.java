package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //execute if place_id is null
        StepDefinitions m = new StepDefinitions();
        if(StepDefinitions.placeId == null) {
            m.addPlacePayload("Test3", "French", "Brazil");
            m.userCallsWihtPostHttpRequest("AddPlaceAPI", "POST");
            m.verifyPlace_IdCreatedMapsToUsing("Test3", "GetPlaceAPI");
        }
    }
}

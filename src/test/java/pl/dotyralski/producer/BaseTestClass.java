package pl.dotyralski.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestClass {

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new RiskVerifyController());
    }

}
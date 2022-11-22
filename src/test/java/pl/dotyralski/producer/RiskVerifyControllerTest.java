package pl.dotyralski.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RiskVerifyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldPassValidation() throws Exception {
        //given
        String riskValidateRequest = objectMapper.writeValueAsString(new RiskValidateRequest(4, "IBM"));
        String expectedResponseJson = objectMapper.writeValueAsString(new RiskValidateResponse(Status.OK));

        //expect
        mockMvc.perform(post("/risks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(riskValidateRequest))
                .andExpect(content().json(expectedResponseJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotPassValidation() throws Exception {
        //given
        String riskValidateRequest = objectMapper.writeValueAsString(new RiskValidateRequest(15, "Microsoft"));
        String expectedResponseJson = objectMapper.writeValueAsString(new RiskValidateResponse(Status.NOT_OK));

        //expect
        mockMvc.perform(post("/risks")
                        .content(riskValidateRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseJson))
                .andExpect(status().isOk());
    }
}
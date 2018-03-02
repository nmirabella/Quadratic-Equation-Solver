package com.github.nmirabella.quadraticsolver;

import com.github.nmirabella.quadraticsolver.controllers.SolutionController;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SolutionController.class)
@ActiveProfiles("test")
public class SolutionControllerTests {

    private static final int scale = 10;
    @MockBean
    private SolutionService solutionService;
    @Autowired
    private MockMvc mvc;
    @Value("${scale.min}")
    private int scaleMin;

    @Value("${scale.max}")
    private int scaleMax;

    private BigDecimal createNumber(String s) {
        return new BigDecimal(s).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    @Test
    public void ExpectAIsZeroBadRequest() throws Exception {

        this.mvc.perform(get("/v1/solution?a=0&b=8&c=7").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest()).
                andExpect(
                        jsonPath("$.message").value("The parameter 'a' cannot be zero")
                );


    }

    @Test
    public void ExpectInvalidScaleBadRequest() throws Exception {

        String expectedResponse = "The parameter 'scale' must be >= " + scaleMin + " and " + " <= " + scaleMax;


        this.mvc.perform(get(
                "/v1/solution?a=2&b=8&c=7&scale=" + (scaleMax + 1)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest()).
                andExpect(
                        jsonPath("$.message").value(expectedResponse)
                );

        this.mvc.perform(get(
                "/v1/solution?a=2&b=8&c=7&scale=" + (scaleMin - 1)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest()).
                andExpect(
                        jsonPath("$.message").value(expectedResponse)
                );


    }


}

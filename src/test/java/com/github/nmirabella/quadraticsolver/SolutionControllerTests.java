package com.github.nmirabella.quadraticsolver;

import com.github.nmirabella.quadraticsolver.controllers.SolutionController;
import com.github.nmirabella.quadraticsolver.model.Solution;
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
import java.math.RoundingMode;

import static org.mockito.Mockito.when;
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
        return new BigDecimal(s).setScale(scale, RoundingMode.HALF_UP);
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

    @Test
    public void ExpectMissingOrInvalidParam() throws Exception {

        String expectedResponse[] = {
                "Required BigDecimal parameter 'b' is not present",
                "Missing or invalid parameter - a, b and c are required and must be valid numbers"
        };

        this.mvc.perform(get(
                "/v1/solution?a=2&b&scale=").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest()).
                andExpect(
                        jsonPath("$.message").value(expectedResponse[0])
                );

        this.mvc.perform(get(
                "/v1/solution?a=gh").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest()).
                andExpect(
                        jsonPath("$.message").value(expectedResponse[1])
                );
    }


    @Test
    public void ExpectValidResponse() throws Exception {
        when(solutionService.solve(createNumber("2"), createNumber("4"), createNumber("1"), scale))
                .thenReturn(
                        new Solution(new BigDecimal[]{createNumber("-0.2928932188"), createNumber("-1.7071067812")}, createNumber("8"))
                );


        this.mvc.perform(get(
                "/v1/solution?a=2&b=4&c=1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.discriminant").value(8));


    }

}

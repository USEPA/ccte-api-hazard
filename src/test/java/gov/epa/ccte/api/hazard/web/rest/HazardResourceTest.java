package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ActiveProfiles("test")
@MockitoSettings(strictness = Strictness.WARN)
@WebMvcTest(HazardResource.class)
@ExtendWith(MockitoExtension.class)
class HazardResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JdbcTemplate jdbcTemplate;

    /**
     * Test Case 1: Health endpoint returns 200 OK when database connection is successful
     * This verifies the happy path - when the SELECT 1 query executes without exception
     */
    @Test
    void testHealthEndpoint_WhenDatabaseConnectionSuccessful_ShouldReturn200Ok() throws Exception {
        doNothing().when(jdbcTemplate).execute("SELECT 1 ");

        mockMvc.perform(get("/hazard/health"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    /**
     * Test Case 2: Health endpoint returns 404 NOT_FOUND when DataAccessException occurs
     * This verifies the narrowed exception handling - when database access fails
     */
    @Test
    void testHealthEndpoint_WhenDataAccessExceptionThrown_ShouldReturn404NotFound() throws Exception {
        doThrow(new DataAccessException("Database connection failed") {
        })
                .when(jdbcTemplate).execute("SELECT 1 ");

        mockMvc.perform(get("/hazard/health"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    /**
     * Test Case 3: Health endpoint returns 404 NOT_FOUND when database is unavailable
     * This simulates a real-world scenario where the database is temporarily down
     */
    @Test
    void testHealthEndpoint_WhenDatabaseConnectionFails_ShouldReturn404NotFound() throws Exception {
        doThrow(new DataAccessException("Unable to connect to database host") {
        })
                .when(jdbcTemplate).execute("SELECT 1 ");

        mockMvc.perform(get("/hazard/health"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}

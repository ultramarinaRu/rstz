package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MortgageController.class)
@TestPropertySource(properties = "logging.level.org.springframework.web=DEBUG")
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void rendersForm() throws Exception {
        mockMvc.perform(get("/mortgage"))
                .andExpect(content().string(containsString("кальк")));
    }

    @Test
    public void submitsForm() throws Exception {
        mockMvc.perform(post("/mortgage").param("sum", "12345")
                .param("months", "12")
                .param("percent", "13.1"))
                .andExpect(content().string(containsString("Сумма: 12345")));
    }

}

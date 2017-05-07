import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pe4nik.config.WebConfig;
import pe4nik.dao.TextDao;
import pe4nik.dao.UserDao;
import pe4nik.dao.WordDao;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.jsonentity.LoginUser;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Pe4Nik on 26.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class ControllerTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private WordDao wordDao;

    @Autowired
    private TextDao textDao;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void getAudioFileTest() throws Exception {
        mockMvc.perform(get("/audio/{word}", "hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void gettWordTest() throws Exception {
        mockMvc.perform(get("/word/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getWordsTest() throws Exception {
        mockMvc.perform(get("/words").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserTest() throws Exception {
        mockMvc.perform(get("/user/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getTextTest() throws Exception {
        mockMvc.perform(get("/text/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getTextsTest() throws Exception {
        mockMvc.perform(get("/texts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void checkUserTest() throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        mockMvc.perform(post("/checkuser").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(LoginUser.asJsonString(
                        new LoginUser("wolfgang@amadeus.at",
                                bCryptPasswordEncoder.encode("requiem")))))
                .andExpect(status().isOk()).andExpect(jsonPath("$.value", is("Wrong password")));
    }
    //Ok, mozart
    @Test
    public void saveUserTest() throws Exception {
        Random rand = new Random();
        User user = new User(1L, "ludwig@van.at"+rand.nextInt(1000), "beethoven", "moonlight");
        mockMvc.perform(post("/saveuser").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(LoginUser.asJsonString(user)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.value", is("Ok")));
    }

    @Test
    public void saveTextTest() throws Exception {
        Text text =  new Text(1L, "some text");
        mockMvc.perform(post("/savetext").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(LoginUser.asJsonString(text)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.value", is("Ok")));
    }
}

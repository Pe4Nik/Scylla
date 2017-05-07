package pe4nik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pe4nik.auth.IAuthenticationFacade;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.Word;
import pe4nik.jsonentity.LoginUser;
import pe4nik.jsonentity.RegUser;
import pe4nik.jsonentity.ResponseString;
import pe4nik.service.MyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pe4Nik on 22.03.2017.
 */
@EnableWebMvc
@RestController
public class Controller {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public MyService myService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping(value="/customlogout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = authenticationFacade.getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect";
    }

    @RequestMapping(value = "/getuserlearnedwords")
    @ResponseBody
    public List<Word> getUserLearnedWords() {
        Authentication authentication = authenticationFacade.getAuthentication();
        List<String> list = Arrays.asList(myService.getUserLearnedWords(authentication.getName())
                .split(","));
        return myService.getWords(list.stream().map(Long::parseLong).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/adduserlearnedwords", method = RequestMethod.POST)
    public void addUserLearnedWords(@RequestBody String learnedWords) {
        Authentication authentication = authenticationFacade.getAuthentication();
        myService.addUserLearnedWords(learnedWords, authentication.getName());
    }

    @RequestMapping(value = "/loggedin")
    @ResponseBody
    public List<Object> listLoggedInUsers() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for (final Object principal : allPrincipals) {
            if (principal instanceof User) {
                final User user = (User) principal;

                List<SessionInformation> activeUserSessions =
                        sessionRegistry.getAllSessions(principal,
                                /* includeExpiredSessions */ false); // Should not return null;

                if (!activeUserSessions.isEmpty()) {
                    // Do something with user
                    System.out.println(user);
                }
            }
        }
        System.out.println("Logged in users: " + allPrincipals.size());
        return allPrincipals;
    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public void getTestString()  {
        Authentication authentication = authenticationFacade.getAuthentication();
        System.out.println(authentication.getName());
        //System.out.println(principal.getName());
    }


    @RequestMapping(value = "/audio/{word}")
    @ResponseBody
    public String getAudioFile(@PathVariable String word) {
        return myService.getAudioFile(word);
    }

    @RequestMapping(value = "/word/{id}")
    @ResponseBody
    public Word getWord(@PathVariable Long id) {
        return myService.getWord(id);
    }




    @RequestMapping(value = "/words")
    @ResponseBody
    public List<Word> getAllWords() {
        return myService.getAllWords();
    }


    @RequestMapping(value = "/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return myService.getUser(id);
    }


    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return myService.getAllUsers();
    }


    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseString checkUser(@RequestBody LoginUser user) {
        return myService.checkUser(user);
    }


    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseString saveUser(@RequestBody RegUser user) {
        return myService.saveUser(new User(null,user.getEmail(),user.getUsername(),user.getPassword()));
    }


    @RequestMapping(value = "/text/{id}")
    @ResponseBody
    public Text getText(@PathVariable Long id) {
        return myService.getText(id);
    }


    @RequestMapping(value = "/savetext", method = RequestMethod.POST)
    @ResponseBody
    public ResponseString saveText(@RequestBody Text text) {
        return myService.saveText(text);
    }


    @RequestMapping(value = "/texts")
    @ResponseBody
    public List getTexts() {
        return myService.getAllTexts();
    }

//    @RequestMapping(value = "/addaudio")
//    @ResponseBody
//    public List<Word> addAudio() {
//        List<Word> words = myService.getAllWords();
//        File[] files = new File("C:\\Users\\Pe4Nik\\Downloads\\eng-wcp-us_flac\\flac").listFiles();
//        for (Word word:words) {
//            for (File file:files) {
//                if(word.getWord().equals(file.getName().substring(6,file.getName().length()-5))) {
//                    word.setAudio(true);
//                    wordDao.save(word);
//                }
//            }
//        }
//        return myService.getAllWords();
//    }

}

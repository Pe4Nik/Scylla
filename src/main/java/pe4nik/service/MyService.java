package pe4nik.service;

import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.Word;
import pe4nik.jsonentity.LoginUser;
import pe4nik.jsonentity.ResponseString;

import java.util.List;

/**
 * Created by Pe4Nik on 29.04.2017.
 */

public interface MyService {
    public List<Word> getWords(List<Long> ids);

    public String getUserLearnedWords(String username);

    public void addUserLearnedWords(String learnedWords, String username);

    public void save(User user);

    public String getAudioFile(String word);

    public Word getWord(Long id);

    public List<Word> getAllWords();

    public User getUser(Long id);

    public List<User> getAllUsers();

    public User findUserByEmail(String email);

    public ResponseString checkUser(LoginUser user);

    public ResponseString saveUser(User user);

    public Text getText(Long id);

    public ResponseString saveText(Text text);

    public List getAllTexts();
}

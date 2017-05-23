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
    public String getUserWordsToStudy(String username);

    public void addUserWordsToStudy(List<String> wordsToStudy, String username);

    public String getProgress(String username);

    public void setProgress(String newProgress, String username);

    public List<Text> getTexts(List<Long> ids);

    public String getUserLearnedTexts(String username);

    public void addUserLearnedTexts(List<String> learnedTexts, String username);

    public List<Word> getWords(List<Long> ids);

    public String getUserLearnedWords(String username);

    public void addUserLearnedWords(List<String> learnedWords, String username);

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

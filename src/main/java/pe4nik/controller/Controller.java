package pe4nik.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe4nik.dao.UserDao;
import pe4nik.dao.WordDao;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.Word;
import pe4nik.registration.LoginUser;
import pe4nik.registration.RegUser;
import pe4nik.registration.ResponseString;
import pe4nik.service.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pe4Nik on 22.03.2017.
 */
@RestController
public class Controller {

    @Autowired
    ServiceImpl serviceImpl;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WordDao wordDao;

    @RequestMapping(value = "/audio/{word}")
    @ResponseBody
    public String getFile(@PathVariable String word) {
        if(wordDao.findByWord(word).getAudio()) {
            String catPath = "C:\\Users\\Pe4Nik\\Downloads\\eng-wcp-us_flac\\flac";
            File file = new File(catPath+File.separator+"En-us-"+word+".flac");
            byte[] bFile = new byte[(int) file.length()];
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(bFile);
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Base64.encodeBase64String(bFile);
        }
        else
            return "Oh, shit!";
    }

    @RequestMapping(value = "/word/{id}")
    @ResponseBody
    public Word getWord(@PathVariable Long id) {
//        Word word= serviceImpl.getWord(id);
//        ResponseWord responseWord;
//        if(word.getAudio() != null) {
//            Blob blob = word.getAudio();
//            int blobLength = 0;
//            byte[] blobAsBytes = null;
//            try {
//                blobLength = (int) blob.length();
//                blobAsBytes = blob.getBytes(1, blobLength);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            responseWord = new ResponseWord(word.getWord(),
//                    word.getValue(), Base64.encodeBase64String(blobAsBytes));
//            File outputFile = null;
//            try {
//                outputFile = new File("C:\\Users\\Pe4Nik\\Downloads\\somefile.flac");
//                FileOutputStream fileoutputstream = new FileOutputStream(outputFile);
//                fileoutputstream.write(blobAsBytes);
//                fileoutputstream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } else
//            responseWord = new ResponseWord(word.getWord(),
//                    word.getValue(), null);
//        return responseWord;
        return serviceImpl.getWord(id);
    }




    @RequestMapping(value = "/words")
    @ResponseBody
    public List<Word> getAllWords() {
        return serviceImpl.getAllWords();
    }


    @RequestMapping(value = "/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return serviceImpl.getUser(id);
    }


    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return serviceImpl.getAllUsers();
    }


    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseString checkUser(@RequestBody LoginUser user) {
        ResponseString responseString = new ResponseString();
        User foundUser = serviceImpl.findUserByEmail(user.getEmail());
        if (foundUser == null) {
            responseString.setValue("Wrong email");
            return responseString;
        }
        if (!foundUser.getPassword().equals(user.getPassword())) {
            responseString.setValue("Wrong password");
            return responseString;
        }
        responseString.setValue("Ok, "+foundUser.getUsername());
        return responseString;
    }


    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseString saveUser(@RequestBody RegUser user) {
        return serviceImpl.saveUser(new User(null,user.getEmail(),user.getUsername(),user.getPassword()));
    }


    @RequestMapping(value = "/text/{id}")
    @ResponseBody
    public Text getText(@PathVariable Long id) {
        return serviceImpl.getText(id);
    }


    @RequestMapping(value = "/savetext", method = RequestMethod.POST)
    @ResponseBody
    public String saveText(@RequestBody Text text) {
        return serviceImpl.saveText(text);
    }


    @RequestMapping(value = "/texts")
    @ResponseBody
    public List getAllTexts() {
        return serviceImpl.getAllTexts();
    }

    @RequestMapping(value = "/addaudio")
    @ResponseBody
    public List<Word> addAudio() {
        List<Word> words = serviceImpl.getAllWords();
        File[] files = new File("C:\\Users\\Pe4Nik\\Downloads\\eng-wcp-us_flac\\flac").listFiles();
        //int coincidence = 0;
        for (Word word:words) {
            for (File file:files) {
                if(word.getWord().equals(file.getName().substring(6,file.getName().length()-5))) {
                    //coincidence++;
                    word.setAudio(true);
                    //byte[] bFile = new byte[(int) file.length()];
//
                    //Blob bltry {
//                        FileInputStream fileInputStream = new FileInputStream(file);
//                        fileInputStream.read(bFile);
//                        fileInputStream.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }ob= null;
//                    try {
//                        blob = new javax.sql.rowset.serial.SerialBlob(bFile);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    word.setAudio(blob);
                    wordDao.save(word);
                }
            }
        }
        //System.out.println("============ "+coincidence+" ===========");
        return serviceImpl.getAllWords();
    }
}

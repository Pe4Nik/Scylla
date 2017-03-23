package pe4nik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pe4nik.entity.Word;
import pe4nik.service.WordService;

import java.util.List;

/**
 * Created by Pe4Nik on 22.03.2017.
 */
@RestController
public class WordController {
    @Autowired
    WordService wordService;

    @RequestMapping(value = "/word/{id}")
    @ResponseBody
    public Word getWord(@PathVariable Long id) {
        return wordService.getWord(id);
    }


    @RequestMapping(value = "/words")
    @ResponseBody
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

}

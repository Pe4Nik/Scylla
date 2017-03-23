package pe4nik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe4nik.dao.WordDao;
import pe4nik.entity.Word;

import java.util.List;

/**
 * Created by Pe4Nik on 21.03.2017.
 */
@Service
public class WordService {

    @Autowired
    private WordDao wordDao;

    @Transactional
    public Word getWord(Long id) {
        return wordDao.getWord(id);
    }

    @Transactional
    public List<Word> getAllWords() {
        return wordDao.getAllWords();
    }
}

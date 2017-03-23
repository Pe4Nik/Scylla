package pe4nik.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe4nik.entity.Word;

import java.util.List;

/**
 * Created by Pe4Nik on 21.03.2017.
 */
@Repository
public class WordDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Word getWord(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Word word = (Word) session.load(Word.class, new Long(id));
        return word;
    }

    public List<Word> getAllWords() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Word> wordList = session.createQuery("from Word").list();
        return wordList;
    }
}

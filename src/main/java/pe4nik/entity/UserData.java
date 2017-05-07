package pe4nik.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Pe4Nik on 07.05.2017.
 */
@Entity
@Table(name = "userdata")
public class UserData {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "learned_words")
    private String learnedWords;

    public UserData() {
    }

    public UserData(Long id, String learnedWords) {
        this.id = id;
        this.learnedWords = learnedWords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLearnedWords() {
        return learnedWords;
    }

    public void setLearnedWords(String learnedWords) {
        this.learnedWords = learnedWords;
    }
}

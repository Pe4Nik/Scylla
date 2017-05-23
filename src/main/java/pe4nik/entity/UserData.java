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

    @Column(name = "learned_texts")
    private String learnedTexts;

    @Column(name = "words_to_study")
    private String wordsToStudy;

    @Column(name = "progress")
    private String progress;

    public UserData() {
    }

    public UserData(Long id, String learnedWords, String learnedTexts, String wordsToStudy, String progress) {
        this.id = id;
        this.learnedWords = learnedWords;
        this.learnedTexts = learnedTexts;
        this.wordsToStudy = wordsToStudy;
        this.progress = progress;
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

    public String getLearnedTexts() {
        return learnedTexts;
    }

    public void setLearnedTexts(String learnedTexts) {
        this.learnedTexts = learnedTexts;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getWordsToStudy() {
        return wordsToStudy;
    }

    public void setWordsToStudy(String wordsToStudy) {
        this.wordsToStudy = wordsToStudy;
    }
}

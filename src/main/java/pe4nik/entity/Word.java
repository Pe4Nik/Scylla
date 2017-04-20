package pe4nik.entity;

import javax.persistence.*;

@Entity
@Table(name = "words")
//@Proxy(lazy = false)
public class Word {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "value")
    private String value;

    @Column(name = "audio")
    private Boolean audio;

    public Word() {
    }

    public Word(Long id, String word, String value, Boolean audio) {
        this.id = id;
        this.word = word;
        this.value = value;
        this.audio = audio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) { this.word = word; }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getAudio() {
        return audio;
    }

    public void setAudio(Boolean audio) {
        this.audio = audio;
    }
}

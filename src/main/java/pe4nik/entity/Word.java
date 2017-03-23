package pe4nik.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "words")
@Proxy(lazy = false)
public class Word {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "value")
    private String value;

    public Word() {
    }

    public Word(Long id, String word, String value) {
        this.id = id;
        this.word = word;
        this.value = value;
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
}

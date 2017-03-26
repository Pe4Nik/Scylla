package pe4nik.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Pe4Nik on 26.03.2017.
 */
@Entity
@Table(name = "texts")
public class Text {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    public Text() {
    }

    public Text(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

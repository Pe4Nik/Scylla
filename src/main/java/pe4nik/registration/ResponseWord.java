package pe4nik.registration;

/**
 * Created by Pe4Nik on 19.04.2017.
 */
public class ResponseWord {
    public String word;

    public String value;

    public String audio;

    public ResponseWord() {
    }

    public ResponseWord(String word, String value, String audio) {
        this.word = word;
        this.value = value;
        this.audio = audio;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}

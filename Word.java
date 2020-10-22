package DictionaryApplication;

import java.util.Objects;

public class Word {

    String word_target;
    String word_explain;

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return Objects.equals(getWord_target(), word.getWord_target()) &&
                Objects.equals(getWord_explain(), word.getWord_explain());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord_target(), getWord_explain());
    }

    @Override
    public String toString() {
        return "\n " + word_target + ": " + word_explain + "." ;
    }
}

package DictionaryApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryCommandline extends DictionaryManagement {

    // In ra tu dien
    public String showAllWords() {
        String s = "";
        for (int i = 0; i < wordList.size(); i++) {
            s += "\n |" + wordList.get(i).word_target + "\t\t|"
                        + wordList.get(i).word_explain;
        }
        return s;
    }

    public void dictionaryBasic() throws IOException {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.insertFromFile();
        dictionaryCommandline.showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.insertFromFile();
        dictionaryCommandline.showAllWords();
    }

    public List<Word> dictionarySearcher(String word) {
        word = word.trim();
        int len = word.length();
        List<Word> foundList = new ArrayList<>();
        for (int i = 0; i < wordList.size() ; i++) {
            if (wordList.get(i).word_target.length() < len) {
                continue;
            }
            String word_test = wordList.get(i).getWord_target().substring(0, len);
            if (word_test.equalsIgnoreCase(word)) {
                foundList.add(wordList.get(i));
            }
        }
        return foundList;
    }
}
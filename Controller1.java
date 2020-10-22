package DictionaryApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller1 implements Initializable {
    @FXML
    protected TableView<Word> tableView;

    @FXML
    protected TableColumn<Word, String> word_target;

    @FXML
    protected TableColumn<Word, String> word_explain;

    @FXML
    private TextField TA;

    @FXML
    private TextField TV;


    @FXML
    private TextField Search;

    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    ObservableList<Word> wordList;



    public void Cancel(ActionEvent event) {
        Search.setText("");
        wordList.remove(0, wordList.size());
        List<Word> s = dictionaryCommandline.dictionarySearcher(Search.getText());
        for (int i = 0; i < s.size(); i ++) {
            wordList.add(s.get(i));
        }
    }

    public void Search(Event event) {
        wordList.remove(0, wordList.size());
        List<Word> s = dictionaryCommandline.dictionarySearcher(Search.getText());
        for (int i = 0; i < s.size(); i ++) {
            wordList.add(s.get(i));
        }
    }

    public void Touch(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample3.fxml"));
        Parent WordAddMode = loader.load();
        Scene scene = new Scene(WordAddMode);
        Controller3 controller3 = loader.getController();
        Word selected = tableView.getSelectionModel().getSelectedItem();
        stage.setScene(scene);
        controller3.setWord(selected);

    }

    public void add(ActionEvent e) throws IOException {
        String a = TA.getText().trim();
        String v = TV.getText().trim();
        Word word = new Word(a, v);
        TA.setText("");
        TV.setText("");
        wordList.add(0,word);
        dictionaryCommandline.insertWord(word);
        dictionaryCommandline.sortDictionary();
        dictionaryCommandline.dictionaryExportToFile();
    }

    public void change(ActionEvent e) throws IOException {
        String a = TA.getText().trim();
        String v = TV.getText().trim();
        Word word = new Word(a,v);
        TA.setText("");
        TV.setText("");
        Word selected = tableView.getSelectionModel().getSelectedItem();
        int i = wordList.indexOf(selected);
        wordList.set(i, word);
        dictionaryCommandline.changeWord(i, word);
        dictionaryCommandline.sortDictionary();
        dictionaryCommandline.dictionaryExportToFile();
    }

    public void Remove(ActionEvent e) throws IOException {
        String a = TA.getText().trim();
        String v = TV.getText().trim();
        Word word = new Word(a,v);
        TA.setText("");
        TV.setText("");
        wordList.remove(word);
        Word selected = tableView.getSelectionModel().getSelectedItem();
        wordList.remove(selected);
        dictionaryCommandline.removeWord(word);
        dictionaryCommandline.removeWord(selected);
        dictionaryCommandline.sortDictionary();
        dictionaryCommandline.dictionaryExportToFile();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wordList = FXCollections.observableArrayList();
        word_target.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        word_explain.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        tableView.setItems(wordList);
        try {
            dictionaryCommandline.insertFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dictionaryCommandline.sortDictionary();
        wordList.remove(0, wordList.size());
        List<Word> s = dictionaryCommandline.dictionarySearcher(Search.getText());
        for (int i = 0; i < s.size(); i ++) {
            wordList.add(s.get(i));
        }
    }
}

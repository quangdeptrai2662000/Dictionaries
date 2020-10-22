package DictionaryApplication;
import com.sun.speech.freetts.VoiceManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 {

    @FXML
    Label Tu;

    @FXML
    TextArea dich;

    public void setWord(Word word) {
        Tu.setText(word.getWord_target());
        dich.setText(word.getWord_explain());
    }

    public void read(ActionEvent event) {

        String s = Tu.getText();
        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(s);
    }

    public void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample4.fxml"));
        Parent WordAddMode = loader.load();
        Scene scene = new Scene(WordAddMode);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }
}

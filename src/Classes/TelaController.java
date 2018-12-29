package Classes;

import javafx.scene.text.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author rafael
 */
public class TelaController implements Initializable {
    
    private Font fontLIBRAS;
    private ArrayList<Button> bts = new ArrayList();
    private ArrayList<Character> word = new ArrayList();
    private ArrayList<String[]> words = new ArrayList();
    private ArrayList<Character> acertos = new ArrayList();
    private ArrayList<Image> anim = new ArrayList();
    private int errorCount = 0;
    
    @FXML
    private ImageView animView;
    @FXML
    private GridPane buttonGrid;
    @FXML
    private Label lblPalavra, lblDica;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLIBRASFont();
        buildAnim();
        readFile("palavras.txt");
        newGame();
    }   
    
    private void buildAnim(){
        for (int i = 1; i < 8; i++) {
            Image ic = new Image("/img/0"+i+".png");
            anim.add(ic);
        }
        animView.setImage(anim.get(0));
    }
    
    private void setLIBRASFont(){
        fontLIBRAS = Font.loadFont(getClass().getResourceAsStream("Libras-2016.ttf"), 48f); 
        if(fontLIBRAS==null) System.out.println("font NULL!");
    }
    
    private void newGame(){
        word.clear();
        acertos.clear();
        bts.clear();
        errorCount = 0;
        
        String[] w = randomWord(); 
        buildLetras(w[0]);
        lblDica.setText("Dica: "+w[1]);
        animView.setImage(anim.get(0));
        printWord();
    }
    
    private String[] randomWord(){
        java.util.Random r = new java.util.Random();
        int max = words.size()-1;
        int pos = r.nextInt((max - 0) + 1) + 0;
        return words.get(pos);
    }
    
    private boolean readFile(String fileName){
        Scanner scan;
        scan = new Scanner(getClass().getResourceAsStream(fileName));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            words.add(line.split("="));
            System.out.println(line);
        }
        return true;
    }
    
    private Button Button(String txt){
        Button bt = new Button();
        int btSize = 70;
        Label st = new Label(txt);
        st.setFont(fontLIBRAS);
        st.setStyle("-fx-text-fill: #ffffff;");
        
        bt.setGraphic(st);
        bt.setOnAction(buttonHandler);
        bt.setMaxSize(btSize, btSize);
        bt.setMinSize(btSize, btSize);
        bt.setStyle("-fx-background-color:" +
"        linear-gradient(#003300, #001a00)," +
"        radial-gradient(center 50% -40%, radius 200%, #003300 45%, #001a00 50%);" +
"    -fx-background-radius: 35;" +
"    -fx-background-insets: 0;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
        
        return bt;
    }
    
    private void buildLetras(String aword){
        java.util.Random r = new java.util.Random();
        char[] sword = aword.toCharArray();
        
        for(char c: sword){
            word.add(c);
        }
        
        ArrayList<Character> opcoes = new ArrayList();
        
        for (int i = 0; i < 26; i++) {
            char c = (char)(r.nextInt(26) + 'a');
            while(opcoes.contains(c)){
                c = (char)(r.nextInt(26) + 'a');
            }
            opcoes.add(c);
            Button bt = Button(Character.toString(c));
            bts.add(bt);
        }

        System.out.println();
        
//        int numRows = 4 ;
//        int numColumns = 7 ;
//        for (int row = 0 ; row < numRows ; row++ ){
//            RowConstraints rc = new RowConstraints();
//            rc.setFillHeight(true);
//            rc.setVgrow(Priority.ALWAYS);
//            buttonGrid.getRowConstraints().add(rc);
//        }
//        for (int col = 0 ; col < numColumns; col++ ) {
//            ColumnConstraints cc = new ColumnConstraints();
//            cc.setFillWidth(true);
//            cc.setHgrow(Priority.ALWAYS);
//            buttonGrid.getColumnConstraints().add(cc);
//        }
        
        buttonGrid.getChildren().clear();
        
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
        
        int btPos = 0;
        
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if(btPos<26) buttonGrid.add(bts.get(btPos), i, j);
                btPos++;
            }
        }
    }

    
    private void letterChoosed(ActionEvent evt){
        Button bt = (Button)evt.getSource();
        Label lb = (Label)bt.getGraphic();
        char letter = lb.getText().toCharArray()[0];
        char lc = Character.toString(letter).toLowerCase().toCharArray()[0];
        char uc = Character.toString(letter).toUpperCase().toCharArray()[0];
        
        if(word.contains(lc) || word.contains(uc)){
            acertos.add(lc);
            acertos.add(uc);
        }else{
            errou();
        }
        
        bt.setDisable(true);
        
        printWord();
        
        if(acertos.containsAll(word)){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText("VOCÊ VENCEU!");
            alert.setContentText("Parabéns, você descobriu a palavra!");
            alert.showAndWait();
            newGame();
        }
    }
    
    private void printWord(){
        String p = "";
        for(char c: word){
            char lc = Character.toString(c).toLowerCase().toCharArray()[0];
            char uc = Character.toString(c).toUpperCase().toCharArray()[0];
            if(acertos.contains(lc) || acertos.contains(uc)){
                p+=Character.toString(c);
            }else{
                p+="_";
            }
        }

        lblPalavra.setText("Palavra: "+p);
    }
    
    private void errou(){
        errorCount++;
        animView.setImage(anim.get(errorCount));
        
        if(errorCount==6){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText("GAME OVER");
            alert.setContentText("Ooops, você errou demais!");
            alert.showAndWait();
            newGame();
        }
    }
    
    private final EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            letterChoosed(event);
            event.consume();
        }
    }; 
}

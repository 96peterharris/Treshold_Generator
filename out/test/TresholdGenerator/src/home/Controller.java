package home;


import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button menuStartBtn;

    @FXML
    private Button menuGenerateBtn;

    @FXML
    private Button menuEncryptBtn;

    @FXML
    private Button menuDecryptBtn;

    @FXML
    private Button menuAboutBtn;

    @FXML
    private Button menuTestBtn;

    @FXML
    private Button exportBtnG;

    @FXML
    private Button importConfigD;

    @FXML
    private Button importKeyT;

    @FXML
    private Button encryptBtn;

    @FXML
    private Button decryptBtn;

    @FXML
    private Button exportConfigBtn;

    @FXML
    private Button generateKeyBtn;

    @FXML
    private Button chooseFileBtnD;

    @FXML
    private Button chooseFileBtnE;

    @FXML
    private Button exportBtnD;

    @FXML
    private Button exportBtnE1;

    @FXML
    private Button exportBtnE2;

    @FXML
    private Button importKeyD;

    @FXML
    private Button generateBtn;

    @FXML
    private Button exportInitial1;

    @FXML
    private Button exportInitial2;

    @FXML
    private Button exitBtn;

    @FXML
    private AnchorPane apStart;

    @FXML
    private AnchorPane apGenerate;

    @FXML
    private AnchorPane apAbout;

    @FXML
    private AnchorPane apEncrypt;

    @FXML
    private AnchorPane apDecrypt;

    @FXML
    private AnchorPane apTest;

    @FXML
    private TextField tfG1;

    @FXML
    private TextField tfG2;

    @FXML
    private TextField taTime;

    @FXML
    private TextField tfBits;

    @FXML
    private TextField tfRegs;

    @FXML
    private TextField tfFileE;

    @FXML
    private TextField tfFileD;

    @FXML
    private TextField tfKeyD;

    @FXML
    private TextField tfKeyE;

    @FXML
    private TextField tfKeyE1;

    @FXML
    private TextField tfConfigD;

    @FXML
    private TextField tfConfigE;

    @FXML
    private TextField tfStringT;

    @FXML
    private TextField tfBits1T;

    @FXML
    private TextField tfBits2T;

    @FXML
    private TextField tfBits3T;

    @FXML
    private TextField tfBits4T;

    @FXML
    private TextField tfBits5T;

    @FXML
    private TextField tfBits6T;

    @FXML
    private TextArea tfResultD;

    @FXML
    private TextArea tfResultE1;

    @FXML
    private TextArea tfTypeE;

    @FXML
    private TextArea tfTypeD;

    @FXML
    private TextArea tfResultG;

    @FXML
    private Label warningLabel1;

    @FXML
    private Label warningLabel2;

    @FXML
    private Label warningLabelE;

    @FXML
    private Label warningLabelD;

    @FXML
    private Label warningLabelT;

    @FXML
    private Label resultLabel1;

    @FXML
    private Label resultLabel2;

    @FXML
    private Label resultLabel3;

    @FXML
    private Label resultLabel4;

    HashMap<Character, ArrayList<Integer>> keyMapImported = new HashMap<>();
    private HashMap<Character, ArrayList<Integer>> metaData = new HashMap<>();

    LFSR lfsr = new LFSR();

    private String encryptResult = "";
    private String decryptResult = "";
    //private ArrayList<Integer> keyArr = new ArrayList<>();
    private String textToDecrypt = "";
    private String textToEncrypt = "";
    private String keyArr = "";
    private String testStr = "";
    private boolean state = false;

    public Controller() throws NoSuchAlgorithmException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apStart.setVisible(true);
        apStart.setOpacity(1);
        tfG1.setEditable(false);
        tfG2.setEditable(false);
        tfResultG.setEditable(false);
        tfResultG.setScrollTop(1000000);
        warningLabel1.setVisible(false);
        warningLabel2.setVisible(false);
        tfFileE.setEditable(false);
        tfFileD.setEditable(false);
        tfResultE1.setEditable(false);
        tfResultD.setEditable(false);
        tfKeyE.setEditable(false);
        tfKeyE1.setEditable(false);
        tfKeyD.setEditable(false);
        warningLabelE.setVisible(false);
        warningLabelD.setVisible(false);
        warningLabelT.setVisible(false);
        resultLabel1.setVisible(false);
        resultLabel2.setVisible(false);
        resultLabel3.setVisible(false);
        resultLabel4.setVisible(false);
    }
    public void handleClicks(ActionEvent actionEvent) throws IOException, NoSuchAlgorithmException {
        if (actionEvent.getSource() == menuStartBtn) {
            apStart.setVisible(true);
            apStart.setOpacity(1);
            apGenerate.setVisible(false);
            apGenerate.setOpacity(0);
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
            apTest.setVisible(false);
            apTest.setOpacity(0);
        }
        if (actionEvent.getSource() == menuGenerateBtn) {
            apGenerate.setVisible(true);
            apGenerate.setOpacity(1);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
            apTest.setVisible(false);
            apTest.setOpacity(0);
        }
        if (actionEvent.getSource() == menuAboutBtn) {
            apAbout.setVisible(true);
            apAbout.setOpacity(1);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apGenerate.setVisible(false);
            apGenerate.setOpacity(0);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
            apTest.setVisible(false);
            apTest.setOpacity(0);
        }
        if (actionEvent.getSource() == menuEncryptBtn) {
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apGenerate.setVisible(false);
            apGenerate.setOpacity(0);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apEncrypt.setVisible(true);
            apEncrypt.setOpacity(1);
            apTest.setVisible(false);
            apTest.setOpacity(0);
        }
        if (actionEvent.getSource() == menuDecryptBtn) {
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apGenerate.setVisible(false);
            apGenerate.setOpacity(0);
            apDecrypt.setVisible(true);
            apDecrypt.setOpacity(1);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
            apTest.setVisible(false);
            apTest.setOpacity(0);
        }
        if (actionEvent.getSource() == menuTestBtn) {
            apAbout.setVisible(false);
            apAbout.setOpacity(0);
            apStart.setVisible(false);
            apStart.setOpacity(0);
            apGenerate.setVisible(false);
            apGenerate.setOpacity(0);
            apDecrypt.setVisible(false);
            apDecrypt.setOpacity(0);
            apEncrypt.setVisible(false);
            apEncrypt.setOpacity(0);
            apTest.setVisible(true);
            apTest.setOpacity(1);
        }
        if (actionEvent.getSource() == exportInitial1) {
            Export export = new Export();
            lfsr.clearArrays();
            boolean result2 = checkNumbers();
            boolean result1 = checkLenRegs();
            if(result2 && result1){
                lfsr.setNumOfReg(Integer.valueOf(tfRegs.getText()));
                lfsr.setNumToGenerate(Integer.valueOf(tfBits.getText()));
                Instant start = Instant.now();
                lfsr.executeOne();
                Instant finish = Instant.now();
                double timeElapsed = Duration.between(start, finish).toNanos();
                taTime.setText(timeElapsed * 0.000000001 + " s");
                export.setMapToExport(lfsr.getMetaData());
                export.exportMap(exportInitial1);
                tfG1.setText(export.getPath());
                tfG2.clear();
                warningLabel1.setVisible(false);
                warningLabel2.setVisible(false);
            }else if(!result1 && result2){
                warningLabel2.setVisible(false);
                warningLabel1.setVisible(true);
            }
            else if(result1 && !result2){
                warningLabel1.setVisible(false);
                warningLabel2.setVisible(true);
            }else if(!result1 && !result2){
                warningLabel1.setVisible(true);
                warningLabel2.setVisible(true);
            }
        }
        if (actionEvent.getSource() == exportInitial2) {
            Export export = new Export();
            boolean result2 = checkNumbers();
            boolean result1 = checkLenRegs();
            lfsr.clearArrays();
            if(result2 && result1){
                lfsr.setNumOfReg(Integer.valueOf(tfRegs.getText()));
                lfsr.setNumToGenerate(Integer.valueOf(tfBits.getText()));
                Instant start = Instant.now();
                lfsr.executeRandom();
                Instant finish = Instant.now();
                double timeElapsed = Duration.between(start, finish).toNanos();
                taTime.setText(timeElapsed * 0.000000001 + " s");
                //taTime.setText(String.format("%.18f", timeElapsed * 0.000000001) + " s");
                export.setMapToExport(lfsr.getMetaData());
                export.exportMap(exportInitial2);
                tfG2.setText(export.getPath());
                tfG1.clear();
                warningLabel1.setVisible(false);
                warningLabel2.setVisible(false);
            }else if(!result1 && result2){
                warningLabel2.setVisible(false);
                warningLabel1.setVisible(true);
            }
            else if(result1 && !result2){
                warningLabel1.setVisible(false);
                warningLabel2.setVisible(true);
            }else if(!result1 && !result2){
                warningLabel1.setVisible(true);
                warningLabel2.setVisible(true);
            }
        }
        if(actionEvent.getSource() == generateBtn){
            tfResultG.clear();
            tfResultG.setText(lfsr.getResultArr().toString());
        }
        if (actionEvent.getSource() == exportBtnG) {
            Export export = new Export();
            export.setStringToExport(lfsr.getResult());
//            export.setArrayToExport(lfsr.getResultArr());
//            export.exportArray(exportBtnE);
            export.exportText(exportBtnG);
        }
        if (actionEvent.getSource() == chooseFileBtnD) {
            Import importFile = new Import();
            Text_Manager tm = new Text_Manager();
            byte[] imported = importFile.importByte(menuDecryptBtn);
            tfFileD.setText(importFile.getPath());
            tfTypeD.setText(tm.bytesToString(imported));
            this.textToDecrypt = "";
            this.textToDecrypt = tm.bytesToString(imported);

        }
        if (actionEvent.getSource() == chooseFileBtnE) {
            Import importFile = new Import();
            importFile.importText(menuEncryptBtn);
            tfFileE.setText(importFile.getPath());
            tfTypeE.setText(importFile.getReadedContent());
        }
        if (actionEvent.getSource() == importKeyD) {
            Text_Manager tm = new Text_Manager();
            Import importKd = new Import();
            this.keyArr = "";
            byte[] imported = importKd.importByte(menuDecryptBtn);
            this.keyArr = tm.bytesToString(imported);
            this.state = false;
            this.tfConfigD.clear();
            this.tfResultD.clear();
            tfKeyD.setText(importKd.getPath());
        }
        if (actionEvent.getSource() == importConfigD) {
            Text_Manager tm = new Text_Manager();
            Import importKd = new Import();
            LFSR_Cipher lfsr = new LFSR_Cipher();
            this.keyArr = "";
            this.metaData.clear();
            importKd.importJson(menuDecryptBtn);
            this.metaData = importKd.getKeyMap();
            lfsr.executeMeta(this.metaData);
            String tmp = tm.arrToString(lfsr.getResultArr());
            byte[] imported = tmp.getBytes();
            this.keyArr = tm.bytesToString(imported);
            this.state = true;
            this.tfKeyD.clear();
            this.tfResultD.clear();
            tfConfigD.setText(importKd.getPath());
        }
        if (actionEvent.getSource() == generateKeyBtn) {
            Export exportKe = new Export();
            XOR xor = new XOR();
            Text_Manager tm = new Text_Manager();
            LFSR_Cipher lfsr = new LFSR_Cipher();

            if(tfTypeE.getText().length() > 0){
                this.encryptResult = "";
                byte[] strToEncryptByte = tm.stringToByte(tfTypeE.getText());
                lfsr.setNumToGenerate(strToEncryptByte.length);
                lfsr.execute(7); //Wybór ile rejestrów
                if(metaData.size() == 0){
                    this.metaData.clear();
                }
                this.metaData = lfsr.getMetaData();
                String strArr =  tm.arrToString(lfsr.getResultArr());
                byte[] arrByte = tm.stringToByte(strArr);
                byte[] xorResult = xor.xor(strToEncryptByte,arrByte);
                exportKe.setByteToExport(tm.stringToByte(strArr));
                exportKe.exportText(menuEncryptBtn);
                this.encryptResult = tm.bytesToString(xorResult);
                tfKeyE1.setText(exportKe.getPath());
                tfResultE1.clear();
                warningLabelE.setVisible(false);
            }else{
                warningLabelE.setVisible(true);
            }
        }
        if(actionEvent.getSource() == exportConfigBtn){
            Export exportKe = new Export();
            XOR xor = new XOR();
            Text_Manager tm = new Text_Manager();
            LFSR_Cipher lfsr = new LFSR_Cipher();

            if(tfTypeE.getText().length() > 0){
                exportKe.setMapToExport(this.metaData);
                exportKe.exportMap(exportConfigBtn);
                this.tfConfigE.setText(exportKe.getPath());
                warningLabelE.setVisible(false);
            }else{
                warningLabelE.setVisible(true);
            }
        }
        if (actionEvent.getSource() == exportBtnE1) {
            Text_Manager tm = new Text_Manager();
            Export export = new Export();
            export.setByteToExport(tm.stringToByte(tfResultE1.getText()));
            export.exportText(menuEncryptBtn);
        }
        if (actionEvent.getSource() == exportBtnE2) {
            Text_Manager tm = new Text_Manager();
            Export export = new Export();
            byte[] tmp = tm.stringToByte(tfResultE1.getText());
            String result = tm.byteToBinary(tmp);
            export.setByteToExport(tm.stringToByte(result));
            export.exportText(menuEncryptBtn);
        }
        if (actionEvent.getSource() == exportBtnD) {
            Export export = new Export();
            export.setStringToExport(tfResultD.getText());
            export.exportText(menuDecryptBtn);
        }
        if (actionEvent.getSource() == encryptBtn){
            if(tfTypeE.getText().length() > 0){
                tfResultE1.clear();
                tfResultE1.setText(this.encryptResult);
                warningLabelE.setVisible(false);
            }else{
                warningLabelE.setVisible(true);
            }
        }
        if (actionEvent.getSource() == decryptBtn){
            XOR xor = new XOR();
            Text_Manager tm = new Text_Manager();
            if(tfKeyD.getText().length() > 0 || tfConfigD.getText().length() > 0 && tfTypeD.getText().length() > 0) {
                this.decryptResult = "";
                byte[] bT = tm.stringToByte(this.tfTypeD.getText());
                byte[] bK = tm.stringToByte(this.keyArr);
                if(bT.length != bK.length){
                    warningLabelD.setText(" Wrong key len - proper key len: " + bT.length);
                    warningLabelD.setVisible(true);
                }else{
                    byte[] xorResult = xor.xor(bT,bK);
                    this.decryptResult = tm.bytesToString(xorResult);
                    tfResultE1.clear();
                    tfResultD.setText(this.decryptResult);
                    warningLabelD.setVisible(false);
                }
            }else{
                warningLabelD.setVisible(true);
            }

        }
        if (actionEvent.getSource() == exitBtn){
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();
        }
        if (actionEvent.getSource() == importKeyT){
            this.testStr = "";
            Import importKt = new Import();
            importKt.importText(importKeyT);
            this.testStr = importKt.getReadedContent();
            tfStringT.setText(importKt.getPath());

            if(testStr.length() < 20000){
                warningLabelT.setVisible(true);
                tfBits1T.clear();
                tfBits2T.clear();
                tfBits3T.clear();
                tfBits4T.clear();
                tfBits5T.clear();
                tfBits6T.clear();
                resultLabel1.setVisible(false);
                resultLabel2.setVisible(false);
                resultLabel3.setVisible(false);
                resultLabel4.setVisible(false);
            }else{
                warningLabelT.setVisible(false);
                Tests tests = new Tests();
                String data = "";
                data = this.testStr;

                //TEST DłUGIEJ SERII
                Integer maxZeros = tests.longSeriesTestInt("0+", data);
                Integer maxOnes = tests.longSeriesTestInt("1+", data);
                tfBits1T.setText(String.valueOf(maxZeros));
                tfBits2T.setText(String.valueOf(maxOnes));
//                System.out.println("Max 0: " + maxZeros);
//                System.out.println("Max 1: " + maxOnes);
//                System.out.println(tests.longSeriesTestBool(maxZeros, maxOnes) + "\n");
                if(tests.longSeriesTestBool(maxZeros, maxOnes)){
                    resultLabel1.setVisible(true);
                    resultLabel1.setStyle("-fx-text-fill:#63A4B4;");
                    resultLabel1.setText("PASSED");
                }else{
                    resultLabel1.setVisible(true);
                    resultLabel1.setStyle("-fx-text-fill:Red;");
                    resultLabel1.setText("FAILED");
                }
                // +++++++++++++++++++++++++++++

                //TEST SERII
                Integer properZeros = tests.seriesTestInt("0+", data);
                Integer properOnes = tests.seriesTestInt("1+", data);
                tfBits3T.setText(String.valueOf(properZeros));
                tfBits4T.setText(String.valueOf(properOnes));
//                System.out.println("Proper 0: " + properZeros);
//                System.out.println("Proper 1: " + properOnes);
//                System.out.println(tests.seriesTestBool(properZeros, properOnes) + "\n");
                if(tests.seriesTestBool(properZeros, properOnes)){
                    resultLabel2.setVisible(true);
                    resultLabel2.setStyle("-fx-text-fill:#63A4B4;");
                    resultLabel2.setText("PASSED");
                }else{
                    resultLabel2.setVisible(true);
                    resultLabel2.setStyle("-fx-text-fill:Red;");
                    resultLabel2.setText("FAILED");
                }
                // +++++++++++++++++++++++++++++

                //TEST Pojedynczych bitów
                Integer counted = tests.singleBitTestInt( data);
                tfBits5T.setText(String.valueOf(counted));
//                System.out.println("Liczba poj. bitów: " + counted);
//                System.out.println(tests.singleBitTestBool(counted) + "\n");
                if(tests.singleBitTestBool(counted)){
                    resultLabel3.setVisible(true);
                    resultLabel3.setStyle("-fx-text-fill:#63A4B4;");
                    resultLabel3.setText("PASSED");
                }else{
                    resultLabel3.setVisible(true);
                    resultLabel3.setStyle("-fx-text-fill:Red;");
                    resultLabel3.setText("FAILED");
                }
                // +++++++++++++++++++++++++++++s

                //TEST Pokerowy
                Double x = tests.pokerTestDouble(data);
                tfBits6T.setText(String.valueOf(x));
//                System.out.println("Poker test x: " + x);
//                System.out.println(tests.pokerTestBool(x) + "\n");
                if(tests.pokerTestBool(x)){
                    resultLabel4.setVisible(true);
                    resultLabel4.setStyle("-fx-text-fill:#63A4B4;");
                    resultLabel4.setText("PASSED");
                }else{
                    resultLabel4.setVisible(true);
                    resultLabel4.setStyle("-fx-text-fill:Red;");
                    resultLabel4.setText("FAILED");
                }
                // +++++++++++++++++++++++++++++

            }

        }
    }

    private boolean checkNumbers(){
        if(tfBits.getText().matches("\\d+") && Integer.parseInt(tfBits.getText()) > 0){
            return true;
        }
        return false;
    }
    private boolean checkLenRegs(){
        if(tfRegs.getText().equals(Integer.toString(3)) || tfRegs.getText().equals(Integer.toString(5)) || tfRegs.getText().equals(Integer.toString(7))){
            return true;
        }
        return false;
    }
    private boolean metaState(){
        if(this.state == true){
            return true;
        }else{
            return false;
        }
    }
}

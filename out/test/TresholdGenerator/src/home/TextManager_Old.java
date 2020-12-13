package home;

import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TextManager_Old {

    private String textBin;
    private ArrayList<String> splited;
    private String resultString;

    public void setSplited(ArrayList<String> splited) {
        this.splited = splited;
    }

    public void setTextBin(String textBin) {
        this.textBin = textBin;
    }

    TextManager_Old(){
        this.textBin = "";
        this.splited = new ArrayList<>();
        this.resultString = "";
    }

    public String getTextBin() {
        return textBin;
    }

    public String getResultString() {
        return resultString;
    }

    //Konwersja stringa do bin
    public void stringToBin(String text){
        this.textBin =  "";
        byte[] b = text.getBytes();
        ArrayList<String> bitsArr = new ArrayList<>();

        for(int i = 0; i < b.length ; i++){//konwersja ascii na bin

            String temp = StringUtils.leftPad(Integer.toBinaryString(b[i]), 8, '0');
            System.out.println(temp);
            bitsArr.add(temp);
        }

        for(String k : bitsArr){
            this.textBin += k;
        }
    }

    private void splitStringToArr(String text){
        this.splited.clear();
        int len = text.length() / 8 + 1;
        int i = 1;
        int l = 0;
        int r = 8;
        while(i != len){
            String temp = "";
            temp += text.substring(l,r);
            this.splited.add(temp);
            l = r;
            r += 8;
            i++;
        }
    }

    //Konwersja bin do string
    public void binToString(String binText){
        splitStringToArr(binText);
        this.resultString = "";
        ArrayList<Integer> intArr = new ArrayList<>();

        for(String z : this.splited){
            intArr.add(Integer.parseInt(z, 2));
        }

        byte[] bitsDecoded = new byte[intArr.size()];
        int z = 0;

        for(Integer i : intArr){
            bitsDecoded[z] = i.byteValue();
            z++;
        }

        Charset charset = StandardCharsets.UTF_8;
        this.resultString = charset.decode(ByteBuffer.wrap(bitsDecoded)).toString();
        //this.resultString = this.resultString.substring(4,this.resultString.length());
    }

}

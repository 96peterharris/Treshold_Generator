package home;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Text_Manager {

    public Text_Manager(){

    }
    public byte[] stringToByte(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("ISO8859-2"); // Charset to encode into Cp1250
        return bytes;
    }
    public String bytesToString(byte[] bytes) throws UnsupportedEncodingException {
        String s2 = new String(bytes, "ISO8859-2"); // Charset with which bytes were encoded
        return  s2;
    }
    public String arrToString(ArrayList<Integer> arr){
        String result = "";
        for(Integer i : arr){
            result += Integer.toString(i);
        }
        return result;
    }
    public String byteToBinary(byte[] data){
        String result = "";
        for(int i = 0; i < data.length; i++){
            String t = String.format("%8s", Integer.toBinaryString(data[i] & 0xFF)).replace(' ', '0');
            result += t;
        }
        return result;
    }
}

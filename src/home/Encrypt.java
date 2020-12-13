package home;

import java.util.ArrayList;

public class Encrypt {
    private String inputFile;
    private ArrayList<Integer> inputArr;
    private ArrayList<Integer> binArr;
    private ArrayList<Integer> splitedArr;
    private String result;

    public Encrypt () {
        this.inputFile = "";
        this.inputArr = new ArrayList<>();
        this.binArr = new ArrayList<>();
        this.splitedArr = new ArrayList<>();
        this.result = "";
    }

    public String getResult() {
        return result;
    }

    public ArrayList<Integer> getBinArr() {
        return binArr;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = "";
        this.inputFile = inputFile;
    } // 1

    public void setBinArr(ArrayList<Integer> generatedArr) {
        this.binArr.clear();
        this.binArr = generatedArr;
    } // 1

    private void strToArr(){
        this.inputArr.clear();
        for(int i = 0; i < this.inputFile.length(); i++){
            this.inputArr.add(Integer.parseInt(String.valueOf(this.inputFile.charAt(i))));
        }
    }
    private void xor(){
        strToArr();
        int i = 0;
        this.splitedArr.clear();
        for(Integer j : this.inputArr){
            this.splitedArr.add(j ^ this.binArr.get(i));
            i++;
        }
    }

    public void execute(){
        strToArr();
        xor();
        result = "";
        for(Integer i : this.splitedArr){
            this.result += i.toString();
        }
    }

}

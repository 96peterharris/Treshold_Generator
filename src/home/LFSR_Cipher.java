package home;

import org.apache.commons.math3.random.MersenneTwister;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class LFSR_Cipher {

    private Integer numOfReg;
    private Integer numToGenerate;
    private ArrayList<ArrayList<Integer>> processedRegs;
    private ArrayList<Integer> arrLenArray;
    private ArrayList<ArrayList<Integer>> initialArrays;
    private HashMap<Integer, ArrayList<Integer>> feedbackArray;
    private boolean state;
    private SecureRandom rand;

    public HashMap<Character, ArrayList<Integer>> getMetaData() {
        return metaData;
    }

    private HashMap<Character, ArrayList<Integer>> metaData;

    public ArrayList<Integer> getResultArr() {
        return resultArr;
    }

    public void setResultArr(ArrayList<Integer> resultArr) {
        this.resultArr = resultArr;
    }

    private ArrayList<Integer> resultArr;

    public Integer getNumToGenerate() {
        return numToGenerate;
    }

    public ArrayList<ArrayList<Integer>> getProcessedRegs() {
        return processedRegs;
    }

    public void setNumToGenerate(Integer numToGenerate) {
        this.numToGenerate = numToGenerate;
    }

    public Integer getNumOfReg() {
        return numOfReg;
    }

    public void setNumOfReg(Integer numOfReg) {
        this.numOfReg = numOfReg;
    }

    public LFSR_Cipher() throws NoSuchAlgorithmException {
        this.numOfReg = 0;
        this.numToGenerate = 0;
        this.processedRegs = new ArrayList<>();
        this.arrLenArray = new ArrayList<>();
        this.initialArrays = new ArrayList<>();
        this.feedbackArray = new HashMap<>();
        this.resultArr = new ArrayList<>();
        this.metaData = new HashMap<>();
        this.state = false;
        this.rand = SecureRandom.getInstanceStrong();
        this.feedbackArray.put(20, new ArrayList<Integer>(Arrays.asList(17,20)));
        this.feedbackArray.put(21, new ArrayList<Integer>(Arrays.asList(19,21)));
        this.feedbackArray.put(22, new ArrayList<Integer>(Arrays.asList(21,22)));
        this.feedbackArray.put(23, new ArrayList<Integer>(Arrays.asList(18,23)));
        this.feedbackArray.put(24, new ArrayList<Integer>(Arrays.asList(20,21,23,24)));
        this.feedbackArray.put(25, new ArrayList<Integer>(Arrays.asList(22,25)));
        this.feedbackArray.put(26, new ArrayList<Integer>(Arrays.asList(20,24,25,26)));
        this.feedbackArray.put(27, new ArrayList<Integer>(Arrays.asList(22,25,26,27)));
        this.feedbackArray.put(28, new ArrayList<Integer>(Arrays.asList(25,28)));
        this.feedbackArray.put(29, new ArrayList<Integer>(Arrays.asList(27,29)));
        this.feedbackArray.put(30, new ArrayList<Integer>(Arrays.asList(24,26,29,30)));
        this.feedbackArray.put(31, new ArrayList<Integer>(Arrays.asList(28,31)));
        this.feedbackArray.put(32, new ArrayList<Integer>(Arrays.asList(25,27,29,30,31,32)));
        this.feedbackArray.put(33, new ArrayList<Integer>(Arrays.asList(20,33)));
        this.feedbackArray.put(34, new ArrayList<Integer>(Arrays.asList(27,28,29,32,33,34)));
        this.feedbackArray.put(35, new ArrayList<Integer>(Arrays.asList(33,35)));
        this.feedbackArray.put(36, new ArrayList<Integer>(Arrays.asList(25,36)));
        this.feedbackArray.put(37, new ArrayList<Integer>(Arrays.asList(31,33,36,37)));
        this.feedbackArray.put(38, new ArrayList<Integer>(Arrays.asList(32,33,37,38)));
        this.feedbackArray.put(39, new ArrayList<Integer>(Arrays.asList(35,39)));
        this.feedbackArray.put(40, new ArrayList<Integer>(Arrays.asList(35,36,37,40)));
        this.feedbackArray.put(41, new ArrayList<Integer>(Arrays.asList(38,41)));
        this.feedbackArray.put(42, new ArrayList<Integer>(Arrays.asList(35,38,39,40,41,42)));
        this.feedbackArray.put(43, new ArrayList<Integer>(Arrays.asList(37,39,40,43)));
        this.feedbackArray.put(44, new ArrayList<Integer>(Arrays.asList(38,39,42,44)));
        this.feedbackArray.put(45, new ArrayList<Integer>(Arrays.asList(41,42,44,45)));
        this.feedbackArray.put(46, new ArrayList<Integer>(Arrays.asList(38,39,40,46)));
        this.feedbackArray.put(47, new ArrayList<Integer>(Arrays.asList(42,47)));
        this.feedbackArray.put(48, new ArrayList<Integer>(Arrays.asList(39,41,44,48)));
        this.feedbackArray.put(49, new ArrayList<Integer>(Arrays.asList(40,49)));
        this.feedbackArray.put(50, new ArrayList<Integer>(Arrays.asList(46,47,48,50)));
        this.feedbackArray.put(51, new ArrayList<Integer>(Arrays.asList(45,48,50,51)));
        this.feedbackArray.put(52, new ArrayList<Integer>(Arrays.asList(49,52)));
        this.feedbackArray.put(53, new ArrayList<Integer>(Arrays.asList(47,51,52,53)));
        this.feedbackArray.put(54, new ArrayList<Integer>(Arrays.asList(46,48,51,54)));
        this.feedbackArray.put(55, new ArrayList<Integer>(Arrays.asList(31,55)));
        this.feedbackArray.put(56, new ArrayList<Integer>(Arrays.asList(49,52,54,56)));
        this.feedbackArray.put(57, new ArrayList<Integer>(Arrays.asList(50,57)));
        this.feedbackArray.put(58, new ArrayList<Integer>(Arrays.asList(39,58)));
        this.feedbackArray.put(59, new ArrayList<Integer>(Arrays.asList(52,55,57,59)));
        this.feedbackArray.put(60, new ArrayList<Integer>(Arrays.asList(59,60)));
    }

    private Integer checkRelativelyFirst(Integer a, Integer b){
        if(b > 0){
            return checkRelativelyFirst(b, a % b);
        }
        return a;
    }

    public int randInt(int min, int max) throws NoSuchAlgorithmException {
        //MersenneTwister rand = new MersenneTwister();
        //SecureRandom rand = SecureRandom.getInstance("Windows-PRNG");
        //SecureRandom rand = SecureRandom.getInstance("DRBG");
        //SecureRandom rand = SecureRandom.getInstanceStrong();
        int randomNum = this.rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private void randomSizeArray() throws NoSuchAlgorithmException { //1 krok
        for(int i = 0; i < this.numOfReg; i++){
            if(i == 0) {
                while (true) {
                    int arrSize = randInt(20, 60);
                    if (arrSize % 2 == 1) {
                        this.arrLenArray.add(arrSize);
                        break;
                    }
                }
            }else{
                while(true){
                    int temp = 1;
                    int arrSize = randInt(20, 60);
                    for(Integer j : this.arrLenArray){
                        if(checkRelativelyFirst(j,arrSize) == 1 && this.arrLenArray.contains(arrSize) == false && arrSize % 2 == 1){
                            temp +=1;
                        }
                    }
                    if(temp == this.arrLenArray.size() + 1){
                        this.arrLenArray.add(arrSize);
                        break;
                    }else{
                        arrSize = randInt(20,60);
                        temp = 1;
                    }
                }
            }
        }
    }

    private void initial(){ //2 krok
        for(Integer i : this.arrLenArray){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0; j < i-1; j++){
                temp.add(0);
            }
            temp.add(1);
            this.initialArrays.add(temp);
        }
    }

    private void randomInitial() throws NoSuchAlgorithmException { // 2 krok opcjonalnie
        for(ArrayList<Integer> a : this.initialArrays){
            for(int i = 0; i < a.size() - 2; i++){
                a.set(i,randInt(0,1));
            }
        }
    }

    private Integer xor(ArrayList<Integer> reg){
        int arrLen = reg.size();
        ArrayList<Integer> bitToXor = this.feedbackArray.get(arrLen);
        ArrayList<Integer> valuesToXor = new ArrayList<>();
        for(Integer i : bitToXor){
            valuesToXor.add(reg.get(i-1));
        }
        int result = valuesToXor.get(0);

        for(Integer i : valuesToXor.subList(1,valuesToXor.size())){
            for(Integer j : valuesToXor.subList(i,valuesToXor.size())){
                for(int k = i; k < j+1; k++){
                    result = result ^ valuesToXor.get(k);
                }
            }
        }
        return result;
    }

    private int lfsr(ArrayList<Integer> reg){
        int x = reg.get(reg.size()-1);
        int z = xor(reg);
        reg.remove(reg.size()-1);
        reg.add(0,z);
        return x;
    }

    private ArrayList<Integer> generate(){//3 - krok
        ArrayList<Integer> resultArr = new ArrayList<>();
        for(ArrayList<Integer> arr : this.initialArrays){
            resultArr.add(lfsr(arr));
        }
        return resultArr;
    }

    private void tresholdFunction(){
        int sum = 0;
        for(int i = 0; i < this.numToGenerate; i++) {
            ArrayList<Integer> temp = generate();
            for (Integer j : temp) {
                sum += j;
            }
            if ((float) sum > (float) this.numOfReg / 2) {
                this.resultArr.add(1);
            } else {
                this.resultArr.add(0);
            }
            sum = 0;
        }
    }

    public void execute(int regs) throws NoSuchAlgorithmException {
        this.numOfReg = regs;
        randomSizeArray();
        initial();
        randomInitial();
        splitToMeta();
        System.out.println(initialArrays + "--- keys");
        this.metaData.put('r',new ArrayList<>(Arrays.asList(this.numOfReg)));
        this.metaData.put('b',new ArrayList<>(Arrays.asList(this.numToGenerate)));
        tresholdFunction();
        System.out.println(initialArrays + " ---keys");
    }

    public void setMetaData(HashMap<Character, ArrayList<Integer>> metaData) {
        this.metaData = metaData;
    }

    private void splitToMeta(){
        int i = 0;
        for(ArrayList<Integer> arr : this.initialArrays){
            this.metaData.put(Character.forDigit(i,10),new ArrayList<>(arr));
            i++;
        }
    }

    private void readMeta(HashMap<Character, ArrayList<Integer>> metaData){
        this.initialArrays.clear();
        if(metaData.containsKey('b')){
            ArrayList<Integer> tmp = metaData.get('b');
            System.out.println(tmp.get(0) + " bity");
            this.numToGenerate = tmp.get(0);
            metaData.remove('b');
        }
        if(metaData.containsKey('r')){
            ArrayList<Integer> tmp = metaData.get('r');
            System.out.println(tmp.get(0) + " rejestry");
            this.numOfReg = tmp.get(0);
            metaData.remove('r');
        }

        for (HashMap.Entry<Character,ArrayList<Integer>> entry : metaData.entrySet()){
            this.initialArrays.add(entry.getValue());
            System.out.println(entry.getValue());
        }
    }

    public void executeMeta(HashMap<Character, ArrayList<Integer>> metaData){
        readMeta(metaData);
        System.out.println(this.initialArrays + " ---meta");
        tresholdFunction();
    }



}

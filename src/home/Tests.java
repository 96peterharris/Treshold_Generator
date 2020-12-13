package home;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tests {
    private LinkedHashMap<Integer, Integer[]> stringFreq;
    private LinkedHashMap<String, Integer> combinations;

    public Tests(){
        this.stringFreq = new LinkedHashMap<Integer, Integer[]>(){{
            put(1, new Integer[]{2315, 2685});
            put(2, new Integer[]{1114, 1386});
            put(3, new Integer[]{527, 723});
            put(4, new Integer[]{240, 384});
            put(5, new Integer[]{103, 209});
            put(6, new Integer[]{103, 209});
        }};

        this.combinations = new LinkedHashMap<String, Integer>(){{
            put("0000", 0);
            put("0001", 0);
            put("0010", 0);
            put("0011", 0);
            put("0100", 0);
            put("0101", 0);
            put("0110", 0);
            put("0111", 0);
            put("1000", 0);
            put("1001", 0);
            put("1010", 0);
            put("1011", 0);
            put("1100", 0);
            put("1101", 0);
            put("1110", 0);
            put("1111", 0);
        }};
    }

    public int longSeriesTestInt(String pattern, String bitStr){
        LinkedHashMap<Integer,Integer> strFreq = new LinkedHashMap<>();
        bitStr = limitString(bitStr);

//        String[] matches1 = Pattern.compile(pattern)
//                .matcher(bitStr)
//                .results()
//                .map(MatchResult::group)
//                .toArray(String[]::new);

//        String[] matches1 = Pattern.compile(pattern)
//                .matcher(bitStr).pattern().stre
//                .map(MatchResult::group)
//                .toArray(String[]::new);

        Matcher matcher = Pattern.compile(pattern).matcher(bitStr);
        ArrayList<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }


        //Collections.addAll(matches, matches1);

        for(String str : matches){
            int strLen = str.length();
            if(!strFreq.containsKey(strLen)){
                int countElem = Collections.frequency(matches, str);
                strFreq.put(strLen,countElem);
            }
        }

        List sortedKeys= new ArrayList(strFreq.keySet());
        Collections.sort(sortedKeys);
        int arrSize = sortedKeys.size() - 1;
        return (int) sortedKeys.get(arrSize);
    }
    public boolean longSeriesTestBool(Integer maxZeros, Integer maxOnes){
        if(maxZeros > 25 || maxOnes > 25){
            return false;
        }else{
            return true;
        }
    }

    public int seriesTestInt(String pattern, String bitStr){
        LinkedHashMap<Integer,Integer> strFreq = new LinkedHashMap<>();
        bitStr = limitString(bitStr);

//        String[] matches1 = Pattern.compile(pattern)
//                .matcher(bitStr)
//                .results()
//                .map(MatchResult::group)
//                .toArray(String[]::new);
//        ArrayList<String> matches =new ArrayList<>();
//        Collections.addAll(matches, matches1);

        Matcher matcher = Pattern.compile(pattern).matcher(bitStr);
        ArrayList<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        for(String str : matches){
            int countElem = Collections.frequency(matches, str);
            int strLen = str.length();
            strFreq.put(strLen,countElem);
//            int strLen = str.length();
//            if(strLen > 6){
//                strLen = 6;
//            }
//            if(strFreq.containsKey(strLen) == false && strLen < 6){
//                int countElem = Collections.frequency(matches, str);
//                strFreq.put(strLen,countElem);
//            }
//            else if(strFreq.containsKey(strLen) == false && str.length() >= 6){
//                strFreq.put(6,1);
//            }else {
//                int old = strFreq.get(strLen);
//                old++;
//                strFreq.replace(6,old);
//            }
        }
        LinkedHashMap<Integer, Integer> tmpMap = new LinkedHashMap<>();
        int tmp = 0;
        for(Map.Entry<Integer, Integer> entry : strFreq.entrySet()){
            if(entry.getKey() < 6){
                tmpMap.put(entry.getKey(),entry.getValue());
            }else{
                tmp += entry.getValue();
            }
        }
        tmpMap.put(6,tmp);
        strFreq.clear();
        strFreq = tmpMap;

        int proper = 0;
        for(Map.Entry<Integer, Integer> entry : strFreq.entrySet()){
            int occurenceNum = entry.getValue();
            Integer[] occurenceLimit = this.stringFreq.get(entry.getKey());
            if(occurenceNum >= occurenceLimit[0] && occurenceNum <= occurenceLimit[1]){
                proper++;
            }
        }
        System.out.println(strFreq);
        return proper;
    }
    public boolean seriesTestBool(Integer properZeros, Integer properOnes){
        if(properOnes + properZeros != 12){
            return false;
        }else{
            return true;
        }
    }

    public double pokerTestDouble(String data){
        if(data.length() > 20000){
            String temp = data.substring(0,200000);
            data = temp;
        }
        System.out.println(data.length());

        for(int i = 0; i < data.length(); i += 4){
            String tmp = "";
            for(int j = 0; j < 4; j++){
                tmp += data.charAt(i + j);
            }
            int occ = this.combinations.get(tmp);
            this.combinations.replace(tmp,occ+=1);
        }

        int result = 0;
        List<Integer> valuesList = new ArrayList<Integer>(this.combinations.values());
        for(Integer i : valuesList){
            result += (int) Math.pow(i,2);
        }
        return ((((double)16 / 5000) * result) - 5000);
    }
    public boolean pokerTestBool(Double x){
        if(x > 2.16 && x < 46.17){
            return true;
        }else{
            return false;
        }
    }

    public int singleBitTestInt(String bitStr){
//        String[] matches1 = Pattern.compile(pattern)
//                .matcher(bitStr)
//                .results()
//                .map(MatchResult::group)
//                .toArray(String[]::new);
//        ArrayList<String> matches =new ArrayList<>();
//        Collections.addAll(matches, matches1);
//        return Collections.frequency(matches, "1");
        bitStr = limitString(bitStr);
        return (int)bitStr.chars().filter(ch -> ch == '1').count();
    }
    public boolean singleBitTestBool(Integer counted){
        if(counted > 9725 && counted < 10275){
            return true;
        }else{
            return  false;
        }
    }

    private String limitString(String data){
        if(data.length() > 20000){
            System.out.println(data.length());
            return  data.substring(0,19999);

        }else{
            return data;
        }
    }
}

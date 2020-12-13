package home;

public class XOR {

    public XOR(){}

    public byte[] xor(byte[] bytes1, byte[] bytes2){
        byte[] result = new byte[bytes1.length];

        int i = 0;
//
//        for (byte b : bytes1)
//            result[i] = (byte) (b ^ bytes2[i++]);
//        return result;
        int j = 0;
        for (byte b : bytes1) {
            j = b ^ bytes2[i];
            //if((j < 32 || j > 126) && (j != 13 || j != 10))
            if((j > 0 && j < 32 ) && (j != 13 || j != 10 || j != 15 || j != 12) || (j > 126)){

                result[i] = b;
            }else{
                result[i] = (byte) j;
            }
            i++;

        }
        return result;
    }
}

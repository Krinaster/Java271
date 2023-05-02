
package genericdemo;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class GenericDemo {

    public static void main(String[] args) {
        
        Safe<Integer> intSafe = new Safe<>(3);
        System.out.println(intSafe.getItem());
        
        Safe<BigInteger> bigIntegerSafe = new Safe<>(new BigInteger("12312341243"));
        System.out.println(bigIntegerSafe.getItem());
        
        Safe<String> stringSafe = new Safe<>("Java 271");
        System.out.println(stringSafe.getItem());
        
        // Change the contents of each safe
        intSafe.setItem(10);
        bigIntegerSafe.setItem(new BigInteger("312312312"));
        stringSafe.setItem("HELLO WORLD");
        
        System.out.println(intSafe.getItem());
        System.out.println(bigIntegerSafe.getItem());
        System.out.println(stringSafe.getItem());
        
        Pair<Integer, Integer> pairA = new Pair<>(3,8);
        Pair<Character, Integer> pairB = new Pair<>('B', 1);
        Pair<String, Double> pairC = new Pair<>("Java", 3.0);
        
        // Create the intrsuction to create a pairing that pairs  BigInteger  to a character
        Pair<BigInteger, Character> pairD = new Pair<>(new BigInteger("2143124312"), 'C');
        
        System.out.println(pairA);
        System.out.println(pairB);
        System.out.println(pairC);
        System.out.println(pairD);
        
        Stuff.print(new BigInteger("2341294038143"));
        Stuff.print(new DecimalFormat());
        Double d = new Double(3.0);
        Stuff.print(d);
        
        // Illustrate passing arrays and an element of a certain type
        // return the frequency of that type
        Random rand = new Random();
        String[] s = {"Pink", "Red","Orange", "Blue", "Red", "Blue"};
        Integer[] val = new Integer[100];
        for(int i =0; i<val.length; i++){
            val[i] = rand.nextInt(20) + 1;
        }
        Character[] cVal = new Character[2000];
        for(int i=0; i<cVal.length; i++){
            cVal[i] = (char)(rand.nextInt(26) + 'A');
        }
        
        Stuff temp = new Stuff();
        System.out.println("Red occurs " + temp.freq(s, "Red") + " times");
        System.out.println("Blue occurs " + temp.freq(s, "Blue") + " times");
        System.out.println("Orange occurs " + temp.freq(s, "Orange") + " times");
        System.out.println("Pink occurs " + temp.freq(s, "Pink") + " times");
        System.out.println("15 occurs " + temp.freq(val, 15) + " times");
        System.out.println("3 occurs " + temp.freq(val, 3) + " times");
        System.out.println("24 occurs " + temp.freq(val, 24) + " times");
        System.out.println("A occurs " + temp.freq(cVal, 'A') + " times");
        System.out.println("C occurs " + temp.freq(cVal, 'C') + " times");
        System.out.println("R occurs " + temp.freq(cVal, 'R') + " times");
        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(val));
        System.out.println(Arrays.toString(cVal));
        
        BigInteger[] biVal = new BigInteger[12];
        for(int i =0; i<biVal.length; i++){
            StringBuilder sb =new StringBuilder();
            for (int j = 0; j < 24; j++) {
                sb = sb.append(rand.nextInt(10));
            }
            biVal[i] = new BigInteger(sb.toString());
        }
        System.out.println("Sum of Integer array values is " + temp.sum(val) + ".");
        //System.out.println("Sum of Character array values is " + temp.sum(cVal) + ".");
        System.out.println("Sum of BigInteger array values is " + temp.sum(biVal) + ".");
    
    
        
    
    }
    
}

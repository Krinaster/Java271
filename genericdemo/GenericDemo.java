
package genericdemo;

import java.math.BigInteger;

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
    
    }
    
}

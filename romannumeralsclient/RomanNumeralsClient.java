package romannumeralsclient;

import java.util.Random;


public class RomanNumeralsClient {

    
    public static void main(String[] args) {
        
        Random rand = new Random();
        RomanNumeral[] r = new RomanNumeral[10];
        for(int i =1; i<r.length; i++)
            r[i] = new RomanNumeral(rand.nextInt(4000) + 1);
        r[0]  = new RomanNumeral();
        
        for(RomanNumeral nr: r)
            System.out.println(nr.getValue() + " = " + nr);
        
        RomanNumeral test1 = new RomanNumeral(5);
        RomanNumeral test2 = new RomanNumeral("V");
        
        System.out.println(test1.equals(test2));
        
    }
    
}

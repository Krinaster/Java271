
package romannumeralsclient;


public class RomanNumeralsClient {

    
    public static void main(String[] args) {
        new RomanNumeral();
        new RomanNumeral(5);
        try{
            new RomanNumeral(0);
        }
        catch(Exception e){
            System.out.println("Please input a value number");
        }
        

    }
    
}


package romannumeralsclient;


public class RomanNumeralsClient {

    
    public static void main(String[] args) {
        new RomanNumeral();
        RomanNumeral test = new RomanNumeral(4);
        /*try{
            new RomanNumeral(0);
        }
        catch(Exception e){
            System.out.println("Please input a value number");
        }
       */
        
        test.print();
        RomanNumeral stringTest = new RomanNumeral("DCLXI");
        stringTest.print();
        System.out.println(test.toString());
    }
    
}

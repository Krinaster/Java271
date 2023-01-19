
package romannumeralsclient;


public class RomanNumeral {
    
    private int value;
    
    public RomanNumeral(){
        value = 1;
    }
    
    public RomanNumeral(int i) throws IllegalArgumentException{
        if(i >= 1 && i <= 4000)
            value = i;
        else
            throw new IllegalArgumentException();
    }
    
    public RomanNumeral(String s){
        
    }
    
    private int toInt(String s){
        
        return 0;
    }
    // I V X L C D M
    public int valueOf(char c)throws NumberFormatException{
        int a = c;
        switch(a){
            case 73:
            case 105: a=1; break;
            case 86:
            case 118: a=5; break;
            case 88:
            case 120: a=10; break;
            case 76:
            case 108: a=50; break;
            case 67:
            case 99: a=100; break;
            case 68: 
            case 100: a=500; break;
            case 77: 
            case 109: a=1000; break;
            default: throw new NumberFormatException();
        }
        System.out.print(a);
        return a;
    }
    


}
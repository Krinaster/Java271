
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
    
    public RomanNumeral(String str){
        value = toInt(str);
    }
    
    private int toInt(String str){
        int curValue = 0;
        for(int i = str.length() - 1; i >= 0 ; i--)
            curValue+= valueOf(str.charAt(i));
          
        return curValue;
    }
    
    private int valueOf(char c) throws NumberFormatException{
        int curValue = 0;
        switch(c){
            case 'i':
            case 'I': curValue = 1; break;
            case 'v':
            case 'V': curValue = 5; break;
            case 'x':
            case 'X': curValue = 10; break;
            case 'l':
            case 'L': curValue = 50; break;
            case 'c':
            case 'C': curValue = 100; break;
            case 'd':
            case 'D': curValue = 500; break;
            case 'm':
            case 'M': curValue = 1000; break;
            default: throw new NumberFormatException();
        }
        return curValue;
    }
    
    public void setRomanNumeral(int i){
    
    }
    
    public void setRomanNumeral(String str){
        
    }
    
    public void print(){
        System.out.println(value);
    }
}

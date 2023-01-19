
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
        
        // if(str.equals(str.toString()))
            //return curValue;
        // else 
            // throw new NumberFormatException();
        //return curValue;
    }
    
    private int valueOf(char c) throws NumberFormatException{
        int curValue = 0;
        switch(c){
            case 'I': curValue = 1; break;
            case 'V': curValue = 5; break;
            case 'X': curValue = 10; break;
            case 'L': curValue = 50; break;
            case 'C': curValue = 100; break;
            case 'D': curValue = 500; break;
            case 'M': curValue = 1000; break;
            default: throw new NumberFormatException();
        }
        return curValue;
    }

    @Override
    public String toString(){
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++){ 
            while(value >= values[i]){
                value = value - values[i];
                roman.append(romanLetters[i]);
                } 
            }

        return roman.toString();    
    }
    
    public void setRomanNumeral(int i){
    
    }
    
    public void setRomanNumeral(String str){
        
    }
    
    public void print(){
        System.out.println(value);
    }
}

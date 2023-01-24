
package romannumeralsclient;


public class TinyRoman extends RomanNumeral{
    
    public TinyRoman(){
        super();
    }

    public TinyRoman(int i) throws IllegalArgumentException{
        super(i);
        if(i > 100)
            throw new IllegalArgumentException("TinyRoman is capped at 100, try use Roman");
    }

    public TinyRoman(String str) throws IllegalArgumentException{
        super(str);
        if(this.getValue() > 100)
            throw new IllegalArgumentException("TinyRoman is capped at 100, try using Roman");
    }

}

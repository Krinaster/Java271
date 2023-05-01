
package genericdemo;

public class Pair<T, U> {
    T x;
    U y;
    
    public Pair(){
        x= null;
        y = null;
    }
    
    public Pair(T x, U y){
        this.x = x;
        this.y = y;
        
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("(");
        sb.append(x).append(",").append(y).append(")");
        return sb.toString();
        
    }
}

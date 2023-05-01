
package genericdemo;


public class Safe<T> {
    T item;
    
    public Safe(T item){
        this.item = item;
    }
    
    public T getItem(){
        return item;
    }
    
    public void setItem(T item){
        this.item = item;
    }
    
}

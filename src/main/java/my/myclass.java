package my;


public class myclass {
    public static void main(String[] args) {
        int data=50;
        System.out.println("Hi from myclass45");    
    }

    //code duplication
            public boolean isSomething()
{
    if (!this.calculateSomeBooleanValue())
    {
        return true;
    }
    else
    {
        return false;
    }
}
// end of code duplication
}

package my;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import java.lang.*;

public class mytest {

    @Test
    public void foo1master() {
        //if (System.currentTimeMillis()%3==1){
        ///}
        //else {
            Assert.fail("fail mkk");

        //};

    };
    @Test
    public void foo2() {
        try 
            {
                Thread.sleep(900);
            } 
            catch(InterruptedException e)
            {
            // this part is executed when an exception (in this example InterruptedException) occurs
            }
        if (System.currentTimeMillis()%3==1){
        }
        else {
          //
        };

    };
    @Test
    public void foo3() {
        //if (System.currentTimeMillis()%3==1){
        //}
       // else {
            Assert.fail("fail messajge2");
        //};

    };

}

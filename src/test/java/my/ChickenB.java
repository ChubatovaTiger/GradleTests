package my;
//haha
import org.junit.Test;
import java.lang.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;



public class ChickenB {
Logger log = LogManager.getLogger();
    
@Rule
public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    
    @Test
    public void ChickenB1() {
        //if (System.currentTimeMillis()%3==1){
        //}
        //else {
           System.out.print("##teamcity[setParameter name='ddd' value='fff']");
          System.out.print("teamcity[setParameter name='ddd' value='fff']");
         try 
            {
                //Thread.sleep(9);
            Thread.sleep(10000);
            } 
            catch(InterruptedException e)
            {
            // this part is executed when an exception (in this example InterruptedException) occurs
            }
            //Assert.fail("fai ");

        //};

    };
    @Test
    public void ChiсkenB2() {
        try 
            {
                //Thread.sleep(1800);
            Thread.sleep(20000);
            } 
            catch(InterruptedException e)
            {
            // this part is executed when an exception (in this example InterruptedException) occurssss
            }
        if (System.currentTimeMillis()%3==1){
        }
        else {
          //
        };

    };
    @Test
    public void ChickenB3() {
                 try 
            {
                //Thread.sleep(90000);
            Thread.sleep(10000);
            } 
            catch(InterruptedException e)
            {
            // this part is executed when an exception (in this example InterruptedException) occurs
            }
        if (System.currentTimeMillis()%1==1){
        }
        else {
            //Assert.fail("fail messajge5");
            

        };

    };

}
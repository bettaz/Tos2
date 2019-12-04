package it.unipd.tos.business.exception;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TakeAwayBillExceptionTest {
    @Test
    public void Constructor_Test(){
        TakeAwayBillException exc=new TakeAwayBillException("Test Exception");
        assertEquals("Test Exception", exc.getMessage());
    }
}

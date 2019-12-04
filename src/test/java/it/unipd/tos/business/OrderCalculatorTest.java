package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.business.exception.TakeAwayBillException;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class OrderCalculatorTest {
    /** Test simple bill
     * with more than 10€, less than 5 sandwiches, less than 50€ of total order,
     * less than 30 entries
     */
    @Test
    public void SimpleOrderList_Test() throws TakeAwayBillException
    {
        List<MenuItem> simpleOrder = new ArrayList<MenuItem>();
        OrderTotalBill calculator = new OrderTotalBill();
        simpleOrder.add(new MenuItem("panino 1",5.0, MenuItem.itemType.Panino));
        simpleOrder.add(new MenuItem("panino 2",6.0, MenuItem.itemType.Panino));
        simpleOrder.add(new MenuItem("cola",3.5,MenuItem.itemType.Bevanda));
        simpleOrder.add(new MenuItem("oliva ascolana", 3.0, MenuItem.itemType.Fritto));
        double total = calculator.getOrderPrice(simpleOrder);
        assertEquals(17.5, total,0.0);
    }
    /** Test void bill
     */
    @Test
    public void  VoidOrderList_Test() throws TakeAwayBillException
    {
        List<MenuItem> voidOrder = new ArrayList<MenuItem>();
        OrderTotalBill calculator = new OrderTotalBill();
        double total = calculator.getOrderPrice(voidOrder);
        assertEquals(0.0, total,0.0);
    }
}

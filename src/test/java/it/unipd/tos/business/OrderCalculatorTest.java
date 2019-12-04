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
    /**Test an order with more than 5 sandwiches
     * with a bill of less than 50€ of total order but more than 10€,
     * less than 30 entries.
     */
    @Test
    public void MoreThanFiveSandwichOrder_Test() throws TakeAwayBillException{
        List<MenuItem> sandwichOrder = new ArrayList<MenuItem>();
        OrderTotalBill calculator = new OrderTotalBill();
        sandwichOrder.add(new MenuItem("panino 1",5.0, MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 2",6.0, MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 3",3.5,MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 4",5.75,MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 5",3.10,MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 6",2.0,MenuItem.itemType.Panino));
        double total = calculator.getOrderPrice(sandwichOrder);
        assertEquals(24.35, total,0.0);
    }
}

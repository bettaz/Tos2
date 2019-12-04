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
        OrderCalculator calculator = new OrderCalculator();
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
        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.getOrderPrice(voidOrder);
        assertEquals(0.0, total,0.0);
    }
    /**Test an order with more than 5 sandwiches
     * with a bill of less than 50€ of total order but more than 10€,
     * less than 30 entries.
     */
    @Test
    public void MoreThanFiveSandwichOrder_Test() throws TakeAwayBillException{
        List<MenuItem> sandwichOrder = new ArrayList<MenuItem>();
        OrderCalculator calculator = new OrderCalculator();
        sandwichOrder.add(new MenuItem("panino 1",5.0, MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 2",6.0, MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 3",3.5,MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 4",5.75,MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 5",3.10,MenuItem.itemType.Panino));
        sandwichOrder.add(new MenuItem("panino 6",2.0,MenuItem.itemType.Panino));
        double total = calculator.getOrderPrice(sandwichOrder);
        assertEquals(24.35, total,0.0);
    }
    /**Test an order with more than 50€ total bill
     * with a less than 5 sandwich but more than 10€ of bill,
     * less than 30 entries
     */
    @Test
    public void MoreThanFiftyEurosTotal_Test() throws TakeAwayBillException{
        List<MenuItem> discountOrder = new ArrayList<MenuItem>();
        OrderCalculator calculator = new OrderCalculator();
        discountOrder.add(new MenuItem("panino d'oro",48.0, MenuItem.itemType.Panino));
        discountOrder.add(new MenuItem("Cola d'oro",10.0,MenuItem.itemType.Bevanda));
        double total = calculator.getOrderPrice(discountOrder);
        assertEquals(52.2, total,0.0);
    }
    /**Oversized order test
     * throws exceptions if the order have more than 30 elements
     */
    @org.junit.Rule
    public ExpectedException exc = ExpectedException.none();

    @Test
    public void OversizedOrder_Test() throws TakeAwayBillException{
        exc.expect(TakeAwayBillException.class);
        exc.expectMessage("Raggiunto il limite di 30 elementi");

        List<MenuItem> oversizedOrder = new ArrayList<MenuItem>();
        OrderCalculator calculator = new OrderCalculator();

        int i;
        for(i=0; i<31; i++){
            oversizedOrder.add(new MenuItem(new String("panino "+i),1.0, MenuItem.itemType.Panino));
        }
        calculator.getOrderPrice(oversizedOrder);
    }
    /**Undersized commission test
     * apply 0.10€ commission if the order is less than 10€
     */
    @Test
    public void LessThanTenEurosTotal_Test() throws TakeAwayBillException{
        List<MenuItem> commissionedOrder = new ArrayList<MenuItem>();
        OrderCalculator calculator = new OrderCalculator();
        commissionedOrder.add(new MenuItem("panino economico",2.0, MenuItem.itemType.Panino));
        commissionedOrder.add(new MenuItem("Cola economica",1.0,MenuItem.itemType.Bevanda));
        double total = calculator.getOrderPrice(commissionedOrder);
        assertEquals(3.1, total,0.0);
    }
}

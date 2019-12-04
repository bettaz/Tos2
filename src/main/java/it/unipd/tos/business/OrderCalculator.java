////////////////////////////////////////////////////////////////////
// [Alessio] [Bettarello] [1227736]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import java.util.List;
public class OrderCalculator implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        Double total=0.0;
        int sandwichCount=0;
        double cheaperSandwichPrice=0;
        for (MenuItem item: itemsOrdered ) {
            total+=item.GetPrice();
            if(item.GetItemType()== MenuItem.itemType.Panino){
                sandwichCount++;
                if (sandwichCount==1||item.GetPrice()<cheaperSandwichPrice){
                    cheaperSandwichPrice=item.GetPrice();
                }
            }
        }
        if(sandwichCount>5){
            total-=cheaperSandwichPrice/2.0;
        }
        if(total>50.0){
            total=total*90.0/100.0;
        }
        if (total<10.0&&!itemsOrdered.isEmpty()){
            total+=0.1;
        }
        if (itemsOrdered.size()>30){
            throw new TakeAwayBillException("Raggiunto il limite di 30 elementi, trovati:"+itemsOrdered.size());
        }
        return total;
    }
}
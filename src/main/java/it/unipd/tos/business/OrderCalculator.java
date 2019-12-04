////////////////////////////////////////////////////////////////////
// [Alessio] [Bettarello] [1227736]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;

public class OrderCalculator implements TakeAwayBill {
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        if (itemsOrdered.size()>30){
            throw new TakeAwayBillException("Raggiunto il limite di 30 elementi, trovati:"+itemsOrdered.size());
        }
        Double total=0.0;
        if (!itemsOrdered.isEmpty()){
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
            if (total<10.0){
                total+=0.1;
            }
            else {
                if(total>50.0){
                    total=total*90.0/100.0;
                }
            }
        }
        return total;
    }
}

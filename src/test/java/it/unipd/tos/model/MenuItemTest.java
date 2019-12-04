package it.unipd.tos.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuItemTest {
    /**Test the constructor*/
    @Test
    public void MenuItem_Test(){
        MenuItem target=new MenuItem("Test 1",5.75, MenuItem.itemType.Panino);
        assertEquals("Test 1",target.GetName());
        assertEquals(5.75,target.GetPrice(),0.0);;
        assertEquals(MenuItem.itemType.Panino,target.GetItemType());
    }
}

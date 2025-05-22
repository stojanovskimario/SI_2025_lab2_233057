
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void EveryStatement() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null,"");
        });
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        List<Item> nevalidno = new ArrayList<Item>();
        nevalidno.add(new Item("",5,1,1));
        ex = assertThrows(RuntimeException.class, () -> {
           SILab2.checkCart(nevalidno,"0123456789012345");
        });
        assertTrue(ex.getMessage().contains("Invalid item!"));

        List<Item> validno = new ArrayList<Item>();
        validno.add(new Item("abc",5,1,1));
        ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(validno,"0");
        });
        assertTrue(ex.getMessage().contains("Invalid card number!"));

        ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(validno,"0123456789aaa345");
        });
        assertTrue(ex.getMessage().contains("Invalid character in card number!"));

        List<Item> validno2 = new ArrayList<>();
        validno2.add(new Item("cba",5,1,1));
        //item.getPrice()*(1-item.getDiscount())*item.getQuantity()
        //1*(1-1)*5
        //-30 bidejkji discount>1 pa sum=-30
        assertEquals(-30,SILab2.checkCart(validno2,"0123456789012345"));
        validno2.add(new Item("cba",5,1,0));
        assertEquals(-25,SILab2.checkCart(validno2,"0123456789012345"));
    }

    @Test
    public void MultipleCondition() {
        List<Item> array = new ArrayList<>();
        //if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
        array.add(new Item("i1",5,301,0)); //TXX
        array.add(new Item("i2",5,299,1)); //FTX
        array.add(new Item("i3",11,299,0)); //FFT
        array.add(new Item("i4",5,299,0)); //FFF
        double res = SILab2.checkCart(array,"0123456789012345");
        assertEquals(6199,res);
    }
}

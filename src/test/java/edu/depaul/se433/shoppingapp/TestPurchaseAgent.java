package edu.depaul.se433.shoppingapp;

import static org.mockito.Mockito.*;

import edu.depaul.se433.shoppingapp.PurchaseAgent;
import edu.depaul.se433.shoppingapp.PurchaseDBO;
import edu.depaul.se433.shoppingapp.Purchase;
import edu.depaul.se433.shoppingapp.ShippingType;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPurchaseAgent {
  private PurchaseDBO purchaseDBO;
  private String user = "Bob";
  private ArrayList<Purchase> purchaseList = new ArrayList<>();

  @BeforeEach
  void setup() {
    purchaseDBO = mock(PurchaseDBO.class);
    when(purchaseDBO.getPurchases(user)).thenReturn(purchaseList);
  }

  @Test
  @DisplayName("Test purchaseAverage with no purchase history")
  void test_average_no_history () {
    PurchaseAgent SUT = new PurchaseAgent(purchaseDBO);
    double expected = 0.00;
    double actual = SUT.averagePurchase(user);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test purchaseAverage of single item")
  void test_average_single_item () {
    Purchase p1 = Purchase.make("p1", LocalDate.now(), 5.00, "IL", "STANDARD");
    purchaseList.add(p1);
    PurchaseAgent SUT = new PurchaseAgent(purchaseDBO);
    double expected = 5.00;
    double actual = SUT.averagePurchase(user);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test purchaseAverage of four items")
  void test_average_four_items () {
    Purchase p1 = Purchase.make("p1", LocalDate.now(), 51.00, "IL", "STANDARD");
    purchaseList.add(p1);
    Purchase p2 = Purchase.make("p1", LocalDate.now(), 14.00, "NY", "NEXT_DAY");
    purchaseList.add(p2);
    Purchase p3 = Purchase.make("p1", LocalDate.now(), 27.00, "CA", "STANDARD");
    purchaseList.add(p3);
    Purchase p4 = Purchase.make("p1", LocalDate.now(), 83.00, "PA", "STANDARD");
    purchaseList.add(p4);
    PurchaseAgent SUT = new PurchaseAgent(purchaseDBO);
    double expected = 43.75;
    double actual = SUT.averagePurchase(user);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test that Purchase.DBO.save() gets called correctly")
  void test_save_gets_called() {
    PurchaseAgent SUT = new PurchaseAgent(purchaseDBO);
    Purchase p1 = Purchase.make("p1", LocalDate.now(), 5.00, "IL", "STANDARD");
    SUT.save(p1);
    verify(purchaseDBO, times(1)).savePurchase(p1);
  }
}

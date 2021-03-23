package edu.depaul.se433.shoppingapp;
import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.ShoppingCart;
import edu.depaul.se433.shoppingapp.Bill;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestTotalCostCalculator {
  private ShoppingCart shoppingCart;

  @BeforeEach
  void setup() {
    shoppingCart = mock(ShoppingCart.class);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/normalBoundary.csv", numLinesToSkip = 1)
  @DisplayName("Parameterized Normal Boundary tests of TotalCostCalculator.calculate")
  void test_normal_boundary(double expectedInitialCost, double expectedShipping, double expectedTax, double
      expectedTotal, double initialCost, String state, String inShipping) {
    TotalCostCalculator SUT = new TotalCostCalculator();
    when(shoppingCart.cost()).thenReturn(initialCost);
    ShippingType shipping = parseShipping(inShipping);
    Bill expected = new Bill(expectedInitialCost, expectedShipping, expectedTax, expectedTotal);
    Bill actual = SUT.calculate(shoppingCart, state, shipping);
    assertEquals(expected.total(), actual.total());
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/weakRobust.csv", numLinesToSkip = 1)
  @DisplayName("Parameterized Weak Robust tests of TotalCostCalculator.calculate")
  void test_weak_robust(double expectedInitialCost, double expectedShipping, double expectedTax, double
      expectedTotal, double initialCost, String state, String inShipping) {
    TotalCostCalculator SUT = new TotalCostCalculator();
    when(shoppingCart.cost()).thenReturn(initialCost);
    ShippingType shipping = parseShipping(inShipping);
    Bill expected = new Bill(expectedInitialCost, expectedShipping, expectedTax, expectedTotal);
    Bill actual = SUT.calculate(shoppingCart, state, shipping);
    assertEquals(expected.total(), actual.total());
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/strongNormal.csv", numLinesToSkip = 1)
  @DisplayName("Parameterized Weak Robust tests of TotalCostCalculator.calculate")
  void test_strong_normal(double expectedInitialCost, double expectedShipping, double expectedTax, double
      expectedTotal, double initialCost, String state, String inShipping) {
    TotalCostCalculator SUT = new TotalCostCalculator();
    when(shoppingCart.cost()).thenReturn(initialCost);
    ShippingType shipping = parseShipping(inShipping);
    Bill expected = new Bill(expectedInitialCost, expectedShipping, expectedTax, expectedTotal);
    Bill actual = SUT.calculate(shoppingCart, state, shipping);
    assertEquals(expected.total(), actual.total());
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/normalBoundary.csv", numLinesToSkip = 1)
  @DisplayName("Parameterized Normal Boundary tests of overloaded TotalCostCalculator.calculate")
  void test_normal_boundary_overloaded_calculate(double expectedInitialCost, double expectedShipping,
      double expectedTax, double expectedTotal, double initialCost, String state, String inShipping) {
    TotalCostCalculator SUT = new TotalCostCalculator();
    ShippingType shipping = parseShipping(inShipping);
    Double expected = expectedTotal;
    Double actual = SUT.calculate(initialCost, state, shipping);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/weakRobust.csv", numLinesToSkip = 1)
  @DisplayName("Parameterized Weak Robust tests of overloaded TotalCostCalculator.calculate")
  void test_weak_robust_overloaded_calculate(double expectedInitialCost, double expectedShipping,
      double expectedTax, double expectedTotal, double initialCost, String state, String inShipping) {
    TotalCostCalculator SUT = new TotalCostCalculator();
    ShippingType shipping = parseShipping(inShipping);
    Double expected = expectedTotal;
    Double actual = SUT.calculate(initialCost, state, shipping);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/strongNormal.csv", numLinesToSkip = 1)
  @DisplayName("Parameterized Weak Robust tests of overloaded TotalCostCalculator.calculate")
  void test_strong_normal_overloaded_calculate(double expectedInitialCost, double expectedShipping, double expectedTax, double
      expectedTotal, double initialCost, String state, String inShipping) {
    TotalCostCalculator SUT = new TotalCostCalculator();
    ShippingType shipping = parseShipping(inShipping);
    Double expected = expectedTotal;
    Double actual = SUT.calculate(initialCost, state, shipping);
    assertEquals(expected, actual);
  }

  ShippingType parseShipping(String inShipping) {
    ShippingType shipping;
    if (inShipping.equals("ShippingType.STANDARD")) {
      shipping = ShippingType.STANDARD;
    } else {
      shipping = ShippingType.NEXT_DAY;
    }
    return shipping;
  }
}

/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */

package no.ntnu.idata2302.lab02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CounterTest {

  @Test
  public void shouldStartAtZero() {
    var counter = Counter.decimal(5);
    assertEquals("00000", counter.value());
  }

  @Test
  public void shouldIncrementTheValue() {
    var counter = decimalCounterSetAt("12345");
    counter.increment();
    assertEquals(counter.value(), "12346");
  }

  @Test
  public void shouldIncrementTheValueManyTimes() {
    var counter = decimalCounterSetAt("0000");
    for (int i = 0; i < 29; i++) {
      counter.increment();
    }
    assertEquals("0029", counter.value());
  }

  @Test
  public void shouldPropagateCarryOverOnce() {
    var counter = decimalCounterSetAt("12349");
    counter.increment();
    assertEquals(counter.value(), "12350");
  }

  @Test
  public void shouldPropagateCarryOverManyTimes() {
    var counter = decimalCounterSetAt("1999999");
    counter.increment();
    assertEquals("2000000", counter.value());
  }

  @Test
  public void shouldRollbackToZeroWhenOverflowing() {
    var counter = decimalCounterSetAt("9999");
    counter.increment();
    assertEquals("0000", counter.value());
  }

  private Counter decimalCounterSetAt(String initialValue) {
    final String alphabet = "0123456789";
    final int digitCount = initialValue.length();
    var digits = new DigitDisplay[initialValue.length()];
    for (int i = digitCount - 1; i >= 0; i--) {
      digits[digitCount - i - 1] =
          new DigitDisplay(alphabet, alphabet.indexOf(initialValue.charAt(i), 0));
    }
    return new Counter(digits);
  }
}

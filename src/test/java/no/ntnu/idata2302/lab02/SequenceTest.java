/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */

package no.ntnu.idata2302.lab02;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class SequenceTest {

  @Test
  public void emptySequenceHasLengthZero() {
    var sequence = new Sequence();
    assertEquals(0, sequence.getLength());
  }

  @Test
  public void insertionShouldIncreaseLength() {
    var sequence = new Sequence();
    sequence.insert(25, 1);
    assertEquals(1, sequence.getLength());
  }

  @Test
  public void insertShouldShiftItemsTowardsTheEnd() {
    var sequence = new Sequence(10, new int[] {1, 2, 3});
    sequence.insert(100, 2);
    assertEquals(4, sequence.getLength());
    assertEquals(1, sequence.get(1));
    assertEquals(100, sequence.get(2));
    assertEquals(2, sequence.get(3));
    assertEquals(3, sequence.get(4));
  }

  @Test
  public void insertShouldIncreaseCapacityWhenFull() {
    var sequence = new Sequence(2, new int[] {10, 20});
    sequence.insert(30, 3);
    assertEquals(3, sequence.getLength());
    assertEquals(4, sequence.getCapacity());
  }

  @Test
  public void searchShouldReturnZeroWhenTheItemDoesNotOccur() {
    var sequence = new Sequence(10, new int[] {1, 2, 3});
    var index = sequence.search(4);
    assertEquals(0, index);
  }

  @Test
  public void searchShouldReturnAIndexWhereItemIs() {
    var sequence = new Sequence(10, new int[] {1, 2, 3, 4, 5});
    assertEquals(3, sequence.search(3));
  }

  @Test
  public void searchShouldReturnZeroWhenTheSequenceIsEmpty() {
    var sequence = new Sequence(10, new int[] {});
    assertEquals(0, sequence.search(23));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeShouldRejectEmptySequences() {
    var sequence = new Sequence(2, new int[] {});
    sequence.remove(2);
  }

  @Test
  public void removeShouldReduceTheLength() {
    var sequence = new Sequence(4, new int[] {1, 2, 3});
    sequence.remove(2);
    assertEquals(2, sequence.getLength());
  }

  @Test
  public void removeShouldShiftElementsTowardsTheBeginning() {
    var sequence = new Sequence(4, new int[] {1, 2, 3, 4});
    sequence.remove(2);
    assertEquals(1, sequence.get(1));
    assertEquals(3, sequence.get(2));
    assertEquals(4, sequence.get(3));
  }

  @Test
  public void removeShouldShrinkArrayWhenItGetsEmpty() {
    var sequence = new Sequence(4, new int[] {1, 2});
    sequence.remove(2);
    assertEquals(2, sequence.getCapacity());
  }

  @Test(expected = IllegalStateException.class)
  public void extremaShouldRejectEmptySequences() {
    var sequence = new Sequence(4, new int[] {});
    sequence.extrema();
  }

  @Test
  public void extremaShouldFindsBothMinAndMax() {
    var sequence = new Sequence(10, new int[] {3, 4, 5, 6, 7, 8});
    var extrema = sequence.extrema();
    assertEquals(3, extrema[0]);
    assertEquals(8, extrema[1]);
  }

  @Test
  public void extremaShouldFindsBothMinAndMaxWhenDecreasing() {
    var sequence = new Sequence(10, new int[] {8, 7, 6, 5, 4, 3});
    var extrema = sequence.extrema();
    assertEquals(3, extrema[0]);
    assertEquals(8, extrema[1]);
  }

  @Test
  public void extremaShouldFindsBothMinAndMaxWhenLengthIsOne() {
    var sequence = new Sequence(10, new int[] {8});
    var extrema = sequence.extrema();
    assertEquals(8, extrema[0]);
    assertEquals(8, extrema[1]);
  }

  @Test
  public void shouldDetectNoDuplicates() {
    var sequence = new Sequence(10, new int[] {1, 2, 3, 4, 5});
    assertFalse(sequence.hasDuplicate());
  }

  @Test
  public void shouldDetectDuplicates() {
    var sequence = new Sequence(10, new int[] {1, 5, 3, 4, 5});
    assertTrue(sequence.hasDuplicate());
  }

  @Test
  public void hasDuplicateShouldAcceptEmptySequence() {
    var sequence = new Sequence(10, new int[] {});
    assertFalse(sequence.hasDuplicate());
  }

}

package edu.grinnell.csc207.sorting;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Your Name
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Test sorting an array with duplicate and negative elements.
   */
  @Test
  public void duplicateAndNegativeElementsTest() {
    if (null == intSorter) {
      return;
    }
    Integer[] original = {3, -2, 1, -2, 3, 4, -5};
    Integer[] expected = {-5, -2, -2, 1, 3, 3, 4};
    assertSorts(expected, original, intSorter);
  } // duplicateAndNegativeElementsTest

  /**
   * Test sorting an array of strings with varying case sensitivity.
   */
  @Test
  public void caseSensitiveStringsTest() {
    if (null == stringSorter) {
      return;
    }
    String[] original = {"Alpha", "bravo", "Charlie", "delta", "Echo"};
    String[] expected = {"Alpha", "Charlie", "Echo", "bravo", "delta"};
    assertSorts(expected, original, stringSorter);
  } // caseSensitiveStringsTest

  /**
   * Test sorting a partially sorted array of integers.
   */
  @Test
  public void partiallySortedIntegersTest() {
    if (null == intSorter) {
      return;
    }
    Integer[] original = {1, 2, 3, 10, 7, 8, 9};
    Integer[] expected = {1, 2, 3, 7, 8, 9, 10};
    assertSorts(expected, original, intSorter);
  } // partiallySortedIntegersTest

  /**
   * Permutes a sorted array several times and checks 
   * if sorting returns the original sorted array.
   */
  @Test
  public void randomPermutationsTest() {
    if (null == intSorter) {
      return;
    }
    Integer[] original = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Integer[] expected = original.clone();
    for (int i = 0; i < 10; i++) {
      Integer[] permuted = original.clone();
      ArrayUtils.permute(permuted);
      assertSorts(expected, permuted, intSorter);
    } // for
  } // randomPermutationsTest

  /**
   * Checks performance of sorting with a large array.
   */
  @Test
  public void largeArrayTest() {
    int SIZE = 1000;
    if (null == intSorter) {
      return;
    }
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i; 
    }
    Integer[] expected = original.clone();
    for (int i = 0; i < 5; i++) { 
      Integer[] mixed = original.clone();
      ArrayUtils.permute(mixed); 
      assertSorts(expected, mixed, intSorter);
    }
  } // largeArrayTest
} // class TestSorter

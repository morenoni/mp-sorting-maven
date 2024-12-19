package edu.grinnell.csc207.sorting;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Tests of MorenoNicoleSort objects.
 * Credits of tests structure to Sam Rebelsky.
 *
 * @author Nicole Moreno Gonzalez
 *
 */
public class TestMorenoNicoleSorter{
  
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new MorenoNicoleSort<String>((x,y) -> x.compareTo(y));
    intSorter = new MorenoNicoleSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()

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
} // Class TestMorenoNicoleSorter

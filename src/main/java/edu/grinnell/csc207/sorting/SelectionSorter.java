package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    for (int i = 0; i < values.length - 1; i++) {
      select(values, i);
    } // for
  } // sort(T[])

  /**
   * Find the smallest element in the unsorted portion of the array
   * and place it at the given index.
   *
   * @param values
   *   The array containing the elements to sort.
   * @param startIndex
   *   The index in the array where the smallest element in the
   *   unsorted portion should be placed.
   */
  private void select(T[] values, int startIndex) {
    int smallerIndex = startIndex;
    for (int j = startIndex + 1; j < values.length; j++) {
      if (order.compare(values[j], values[smallerIndex]) < 0) {
        smallerIndex = j;
      } // if
    } // for
    T temp = values[startIndex];
    values[startIndex] = values[smallerIndex];
    values[smallerIndex] = temp;
  } // select(T[], int)
} // class SelectionSorter

package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Nicole Moreno Gonzalez
 * @author Samuel A. Rebelsky
 */

public class InsertionSorter<T> implements Sorter<T> {
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
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
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
    for (int i = 1; i < values.length; i++) {
      insert(values, i);
    } // for
  } // sort(T[])

  /**
   * Insert the element at the given index into its correct position
   * within the sorted portion of the array.
   *
   * @param values
   *   The array containing the elements to sort.
   * @param index
   *   The index of the element to insert into the sorted portion.
   */
  private void insert(T[] values, int index) {
    T current = values[index];
    int j = index - 1;
    while (j >= 0 && order.compare(values[j], current) > 0) {
      values[j + 1] = values[j];
      j--;
    } // while
    values[j + 1] = current;
  } // insert(T[], int)
} // class InsertionSorter

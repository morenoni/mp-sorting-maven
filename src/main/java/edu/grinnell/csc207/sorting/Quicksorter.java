package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;
  private final Random random = new Random();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    quicksort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Recursively applies the Quicksort algorithm to sort the subarray.
   *
   * @param values the array to be sorted.
   * @param lb the starting index of the subarray.
   * @param ub the ending index of the subarray.
   */
  private void quicksort(T[] values, int lb, int ub) {
    if (lb < ub) {
      int pivotIndex = partition(values, lb, ub);
      quicksort(values, lb, pivotIndex - 1);
      quicksort(values, pivotIndex + 1, ub);
    } // if
  } // quicksort(T[], int, int)

  /**
   * Partitions the array around a pivot, such that elements less than the pivot
   * are on the left, and elements greater than the pivot are on the right.
   *
   * @param values the array containing elements to partition.
   * @param lb the starting index of the partitioning range.
   * @param ub the ending index of the partitioning range.
   * @return the final index of the pivot after partitioning.
   */
  private int partition(T[] values, int lb, int ub) {
    // Choose a random pivot and move it to the end
    int pivotIndex = lb + random.nextInt(ub - lb + 1);
    T pivot = values[pivotIndex];
    swap(values, pivotIndex, ub);
    int i = lb - 1; 
    for (int j = lb; j < ub; j++) {
      if (order.compare(values[j], pivot) < 0) {
        i++;
        swap(values, i, j);
      } // if
    } // for
    swap(values, i + 1, ub);
    return i + 1;
  } // partition(T[], int, int)

  /**
   * Swaps two elements in the array.
   *
   * @param values the array containing elements to swap.
   * @param i the index of the first element.
   * @param j the index of the second element.
   */
  private void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  } // swap(T[], int, int)
} // class Quicksorter

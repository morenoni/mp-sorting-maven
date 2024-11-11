package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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
    if (values.length < 2) {
      return; 
    } // if
    T[] temp = values.clone();
    mergeSort(values, temp, 0, values.length - 1);
  } // sort(T[])

  /**
   * Recursively divides the array and sorts each part using merge sort.
   *
   * @param values
   *   The array to be sorted.
   * @param temp
   *   A temporary array used for merging.
   * @param start
   *   The starting index of the portion to be sorted.
   * @param end
   *   The ending index of the portion to be sorted.
   */
  private void mergeSort(T[] values, T[] temp, int start, int end) {
    if (start < end) {
      int mid = start + (end - start) / 2;
      mergeSort(values, temp, start, mid);
      mergeSort(values, temp, mid + 1, end);
      merge(values, temp, start, mid, end);
    } // if
  } // mergeSort

  /**
   * Merges two sorted halves of an array.
   *
   * @param values
   *   The array containing the sorted halves.
   * @param temp
   *   A temporary array used to store the merged result.
   * @param leftStart
   *   The starting index of the first sorted half.
   * @param mid
   *   The ending index of the first sorted half (midpoint).
   * @param rightEnd
   *   The ending index of the second sorted half.
   */
  private void merge(T[] values, T[] temp, int leftStart, int mid, int rightEnd) {
    int leftIndex = leftStart;      
    int rightIndex = mid + 1;       
    int tempIndex = leftStart;      
    // Merge elements from both halves into temp
    while (leftIndex <= mid && rightIndex <= rightEnd) {
      if (order.compare(values[leftIndex], values[rightIndex]) <= 0) {
        temp[tempIndex++] = values[leftIndex++];
      } else {
        temp[tempIndex++] = values[rightIndex++];
      } // else 
    } // while
    while (leftIndex <= mid) {
      temp[tempIndex++] = values[leftIndex++];
    } // while
    while (rightIndex <= rightEnd) {
      temp[tempIndex++] = values[rightIndex++];
    } // while
    for (int i = leftStart; i <= rightEnd; i++) {
      values[i] = temp[i];
    } // for
  } // merge
} // class MergeSorter

package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * This is a sorting algorithm that combines Merge Sort and Insertion Sort,
 * optimizing performance based on subarray size. This strategy efficiently 
 * handles large data and leverages the simplicity of InsertionSort for 
 * small or nearly sorted sections.
 *
 * @author Nicole Moreno Gonzalez
 *
 * @param <T> The types of values that are sorted.
 */
public class MorenoNicoleSort<T> implements Sorter<T> {

  /** Comparator used to define the order of elements. */
  private final Comparator<? super T> order;

  /** Threshold below which Insertion Sort is applied. */
  private static final int INSERTION_SORT_THRESHOLD = 16;

  /**
   * Constructs a MorenoNicoleSort instance with a specified comparator.
   *
   * @param comparator Determines the order for sorting elements in the array.
   */
  public MorenoNicoleSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MorenoNicoleSort(comparator)

  /**
   * Sorts an array in place using a flexible hybrid sorting algorithm.
   *
   * @param values The array to sort.
   */
  @Override
  public void sort(T[] values) {
    if (values == null || values.length < 2) {
      return;
    } // if
    hybridSort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Determines the appropriate sorting strategy based on subarray size.
   *
   * @param values The array to sort.
   * @param lb The lower bound of the subarray.
   * @param ub The upper bound of the subarray.
   */
  private void hybridSort(T[] values, int lb, int ub) {
    if (ub - lb < INSERTION_SORT_THRESHOLD) {
      insertionSort(values, lb, ub);
    } else {
      int mid = lb + (ub - lb) / 2;
      hybridSort(values, lb, mid);
      hybridSort(values, mid + 1, ub);
      merge(values, lb, mid, ub);
    } // else
  } // hybridSort

  /**
   * Sorts a subarray using a straightforward Insertion Sort approach.
   *
   * @param values The array to sort.
   * @param lb The lower bound of the subarray.
   * @param ub The upper bound of the subarray.
   */
  private void insertionSort(T[] values, int lb, int ub) {
    for (int i = lb + 1; i <= ub; i++) {
      T key = values[i];
      int j = i - 1;
      while (j >= lb && order.compare(values[j], key) > 0) {
        values[j + 1] = values[j];
        j--;
      } // while
      values[j + 1] = key;
    } // for
  } // insertionSort

  /**
   * Merges two sorted subarrays in place, leveraging temporary storage.
   *
   * @param values The array with the subarrays to merge.
   * @param lb The lower bound of the left subarray.
   * @param mid The midpoint dividing the subarrays.
   * @param ub The upper bound of the right subarray.
   */
  @SuppressWarnings("unchecked")
  private void merge(T[] values, int lb, int mid, int ub) {
    T[] temp = (T[]) new Object[ub - lb + 1];
    int left = lb;
    int right = mid + 1;
    int tempIndex = 0;
    while (left <= mid && right <= ub) {
      if (order.compare(values[left], values[right]) <= 0) {
        temp[tempIndex++] = values[left++];
      } else {
        temp[tempIndex++] = values[right++];
      } // else
    } // while
    while (left <= mid) {
      temp[tempIndex++] = values[left++];
    } // while
    while (right <= ub) {
      temp[tempIndex++] = values[right++];
    } // while
    System.arraycopy(temp, 0, values, lb, temp.length);
  } // merge
} // class MorenoNicoleSort

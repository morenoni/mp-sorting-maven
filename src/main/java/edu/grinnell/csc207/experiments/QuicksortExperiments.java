package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;
import java.util.Comparator;

import edu.grinnell.csc207.sorting.InsertionSorter;
import edu.grinnell.csc207.sorting.MergeSorter;
import edu.grinnell.csc207.sorting.MorenoNicoleSort;
import edu.grinnell.csc207.sorting.Quicksorter;
import edu.grinnell.csc207.sorting.SelectionSorter;
import edu.grinnell.csc207.sorting.Sorter;

/**
 * Some quick experiments with Quicksort.
 *
 * @author Nicole Moreno Gonzalez
 * @author Samuel A. Rebelsky.
 */
public class QuicksortExperiments {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Sort various arrays.
   *
   * @param args
   *   Command-line arguments; one of the arrays we sort.
   */
  @SuppressWarnings("rawtypes")
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Comparator<Comparable> order = (x, y) -> x.compareTo(y);
    Sorter<Comparable> sorter = new Quicksorter<>(order);
    SortExperiments.manyExperiments(pen, sorter);
    SortExperiments.oneExperiment(pen, args, sorter);

    // Integer sorting experiments
    Comparator<Integer> ascendingIntOrder = Integer::compareTo;
    Comparator<Integer> descendingIntOrder = (x, y) -> y.compareTo(x);
    Integer[] intArray = {42, 17, 256, 5, -99, 1, 73};
    Integer[] intArray2 = {42, 17, 256, 5, -99, 1, 73};
    Integer[] intArray3 = {42, 17, 256, 5, -99, 1, 73};
    Integer[] intArray4 = {42, 17, 256, 5, -99, 1, 73};
    Integer[] intArray5 = {42, 17, 256, 5, -99, 1, 73};

    Sorter<Integer> intSorter = new Quicksorter<>(ascendingIntOrder);
    Sorter<Integer> intSorter2 = new MergeSorter<>(ascendingIntOrder);
    Sorter<Integer> intSorter3 = new InsertionSorter<>(ascendingIntOrder);
    Sorter<Integer> intSorter4 = new SelectionSorter<>(ascendingIntOrder);
    Sorter<Integer> intSorter5 = new MorenoNicoleSort<>(ascendingIntOrder);
    SortExperiments.oneExperiment(pen, intArray, intSorter);
    SortExperiments.oneExperiment(pen, intArray2, intSorter2);
    SortExperiments.oneExperiment(pen, intArray3, intSorter3);
    SortExperiments.oneExperiment(pen, intArray4, intSorter4);
    SortExperiments.oneExperiment(pen, intArray5, intSorter5);

    intSorter = new Quicksorter<>(descendingIntOrder);
    SortExperiments.oneExperiment(pen, intArray, intSorter);

    // String sorting experiments
    Comparator<String> alphabeticalOrder = String::compareTo;
    Comparator<String> lengthOrder = Comparator.comparingInt(String::length);
    Comparator<String> asciiSumOrder = Comparator.comparingInt(s -> s.chars().sum());
    String[] stringArray = {"apple", "orange", "banana", "kiwi", "grape"};
    String[] stringArray2 = {"apple", "orange", "banana", "kiwi", "grape"};
    String[] stringArray3 = {"apple", "orange", "banana", "kiwi", "grape"};
    String[] stringArray4 = {"apple", "orange", "banana", "kiwi", "grape"};
    String[] stringArray5 = {"apple", "orange", "banana", "kiwi", "grape"};

    Sorter<String> stringSorter = new Quicksorter<>(alphabeticalOrder);
    Sorter<String> stringSorter2 = new MergeSorter<>(alphabeticalOrder);
    Sorter<String> stringSorter3 = new SelectionSorter<>(alphabeticalOrder);
    Sorter<String> stringSorter4 = new MergeSorter<>(alphabeticalOrder);
    Sorter<String> stringSorter5 = new MorenoNicoleSort<>(alphabeticalOrder);
    SortExperiments.oneExperiment(pen, stringArray, stringSorter);
    SortExperiments.oneExperiment(pen, stringArray2, stringSorter2);
    SortExperiments.oneExperiment(pen, stringArray3, stringSorter3);
    SortExperiments.oneExperiment(pen, stringArray4, stringSorter4);
    SortExperiments.oneExperiment(pen, stringArray5, stringSorter5);

    stringSorter = new Quicksorter<>(lengthOrder);
    SortExperiments.oneExperiment(pen, stringArray, stringSorter);

    stringSorter = new Quicksorter<>(asciiSumOrder);
    SortExperiments.oneExperiment(pen, stringArray, stringSorter);
    pen.close();
  } // main(String[])
} // class QuicksortExperiments

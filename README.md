# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Nicole Moreno Gonzalez
* Samuel A. Rebelsky (starter code)

Acknowledgements

* Credits to Sam for providing new Sort idea.

This code may be found at <https://github.com/morenoni/mp-sorting-maven.git>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
---------------------------------------
 MorenoNicoleSort is a sorting algorithm that orders the data by using a mix of Merge Sort and Insertion Sort.
 It switches between the two based on the size of the subarray, making it efficient for larger data structures
 while taking advantage of Insertion Sort’s simplicity on smaller or almost-sorted sections.


Notes on using Copilot (or other AI)
------------------------------------

Citation: I used ChatGPT for coming up with sorter ideas. It suggested the same idea Sam gave in class. I went with it because my head hurts from trying to implement Quicksort. ChatGPT gave me suggestions on implementing straightforward methods of insert and merge sorts (to avoid referencing other classes) and also on creating a threshold to indicate when to do insertion sort.

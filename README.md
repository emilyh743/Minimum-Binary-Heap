# Minimum-Binary-Heap

Creating a Java Priority Queue ADT in Java by building a minimum binary heap with numerically smallest priority at the root of the tree.

Instead of just a single integer as heap element, the heap element is represented by an integer (for priority queue position) and a string (associated information). As the elements move around in the heap, the we keep the associated information associated.

The Minimum Binary Heap (Min BHEAP) class will provide these operations:

insert:
       
      in: an EntryPair object, containing the priority and string, (assume no duplicate priorities will be inserted)
          
      return: void
      
      effect: 
            - inserts element into Min BHEAP
            - ordering is done based on integer priorities in the elements that are inserted; ignore string for ordering
            - there are no duplicate priorities
            - size is incremented by 1, and return
      
delMin:
      ```
      in: nothing
      
      return: void
      
      effect: 
            - the minimum element (located at the root according to Minimum Binary Heap structure) is removed (removes entire             object)
            - size of the heap is decremented by 1 (if heap was not empty)
            - if delMin is performed on an empty heap, then treat it as a no-op.
    ```
getMin:
    ```
      in: nothing
      
      return: an element (an EntryPair object)
      
      effect: 
            - provides the minimum element (which is the root, according to Min BHEAP structure)
            - does NOT alter the heap (heap has same elements in the same arrangement as before)
            - if getMin is performed on an empty heap, then return null (as you cannot obtain the minimum element if nothing               is in the heap)
    ```
size:
    ```
      in: nothing
      
      return: integer 0 or greater
      
      effect: 
            - provides count of how many elements are in the Min BHEAP
            - if the heap is empty then size is 0
            - size op will always return a 0 or greater
    ```
build:
    ```
      in: array of elements that need to be in the heap
      
      return: void (assume for a build that the bheap will start empty)
      
      effect:
            - operation starts with empty heap and receives an array of element objects as input. The effect is to produce a               valid heap that contains only those input elements. (When this operation is done, the heap will have both proper               structure and show correct heap order.)
            - this is the O(N) operation that starts with placing all elements into the heap array with no care to heap order.             You then go to the parent of the last node, and bubble down until you have done the root.
    ```
BubbleUp:
    ```
      - if element inserted is smaller than its parent node (Min BHEAP), then swap the element with its parent
      - keep repeating step above until node reaches the correct position
    ```
BubbleDown:
    ```
      - if replaced element is greater than any of its child node (Min BHEAP), then swap the element with its smallest child
      - keep repeating step above until node reaches the correct position
    ```

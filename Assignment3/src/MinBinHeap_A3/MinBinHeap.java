package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	EntryPair temp;
	private static final int arraySize = 10000; // Everything in the array will
	// initially
	// be null. This is ok! Just
	// build out
	// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for
		// simplicity
		// of child/parent
		// computations...
		// the book/animation page
		// both do this.
	}

	public void BubbleUp(int i) {
		int hole;

		EntryPair temp = array[i];
		int element = array[i].getPriority();

		for (hole = i; hole > 1 && element < array[hole / 2].getPriority(); hole /= 2) {
			array[hole] = array[hole / 2];
		}
		array[hole] = temp;
	}

	public void BubbleDown(int i) { // not recursively this time.
		int minChildIndex;

		for (int hole = i; hole * 2 <= size; hole = minChildIndex) {
			minChildIndex = hole * 2;

			// going through right subtree here:
			if (minChildIndex != size) {
				if (array[minChildIndex].getPriority() > array[minChildIndex + 1].getPriority()) {
					minChildIndex++;
				}
			}
			// hole is made child here:
			if (array[hole].getPriority() > array[minChildIndex].getPriority()) {
				EntryPair temp = array[hole]; // so we don't lose array[hole]
												// val for later
				array[hole] = array[minChildIndex];
				array[minChildIndex] = temp;
			}
		}
	}

	// Please do not remove or modify this method! Used to test your entire
	// Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		if (size == 0) {
			array[1] = entry;
			size++;
		} else {
			// store entry pair at size +1 (last index)
			array[size + 1] = entry;
			BubbleUp(size + 1);
			size++;
		}
	}

	@Override
	public void delMin() {
		if (size == 0) {
			return;
		}
		array[1] = array[size];
		size--;
		BubbleDown(1);
		return;
	}

	@Override
	public EntryPair getMin() {
		if (size == 0) {
			return null;
		} else {
			return array[1];
		}
	}

	@Override
	public int size() {
		if (size == 0) {
			return 0;
		} else {
			return size;
		}
	}

	@Override
	public void build(EntryPair[] entries) {

		// iterate through entries, and one by one, place into array
		for (int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i]; // offset by 1..change array[i] to
			// array[i +1] to get slot 1
		}
		size = entries.length; // sets heap size to entries building from
		for (int i = size / 2; i > 0; i--) {
			BubbleDown(i);
		}
	}
}
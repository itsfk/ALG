import java.util.ArrayList;

public class SortingAlgos {
    public static ArrayList<String> quickSort(ArrayList<String> list) {
        if (list.isEmpty())
            return list; // start with recursion base case
        ArrayList<String> sorted;  // this shall be the sorted list to return, no needd to initialise
        ArrayList<String> smaller = new ArrayList<String>(); // Strings smaller than pivot
        ArrayList<String> greater = new ArrayList<String>(); // Strings greater than pivot
        String pivot = list.get(0);  // first String in list, used as pivot
        int i;
        String j;     // Variable used for Strings in the loop
        for (i = 1; i < list.size(); i++) {
            j = list.get(i);
            if (j.compareTo(pivot) < 0)   // make sure String has proper compareTo method
                smaller.add(j);
            else
                greater.add(j);
        }
        smaller = quickSort(smaller);  // capitalise 's'
        greater = quickSort(greater);  // sort both halfs recursively
        smaller.add(pivot);          // add initial pivot to the end of the (now sorted) smaller Strings
        smaller.addAll(greater);     // add the (now sorted) greater Strings to the smaller ones (now smaller is essentially your sorted list)
        sorted = smaller;            // assign it to sorted; one could just as well do: return smaller

        return sorted;
    }

    public static ArrayList<String> bubbleSort(ArrayList<String> list) {
        boolean sorted = false;
        String temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
        return list;
    }


    public static ArrayList<String> mergeSort(ArrayList<String> list, int startIndex, int endIndex) {

        ArrayList<String> sortedList = new ArrayList<>();
        //Divide till you breakdown your list to single element
        if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
            int mid = (endIndex + startIndex) / 2;
            mergeSort(list, startIndex, mid);
            mergeSort(list, mid + 1, endIndex);

            //merging Sorted array produce above into one sorted array
            sortedList = merger(list, startIndex, mid, endIndex);
        }
        return sortedList;
    }

    public static ArrayList<String> merger(ArrayList<String> list, int startIndex, int midIndex, int endIndex) {

        //Below is the mergedarray that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
        ArrayList<String> mergedSortedArray = new ArrayList<String>();

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        while (leftIndex <= midIndex && rightIndex <= endIndex) {
            if (list.get(leftIndex).compareTo(list.get(rightIndex)) < 0) {
                mergedSortedArray.add(list.get(leftIndex));
                leftIndex++;
            } else {
                mergedSortedArray.add(list.get(rightIndex));
                rightIndex++;
            }
        }

        //Either of below while loop will execute
        while (leftIndex <= midIndex) {
            mergedSortedArray.add(list.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex <= endIndex) {
            mergedSortedArray.add(list.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while (i < mergedSortedArray.size()) {
            list.set(j, mergedSortedArray.get(i++));
            j++;
        }
        return list;
    }

}

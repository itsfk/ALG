import java.util.ArrayList;

public class SortingAlgos {
    

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

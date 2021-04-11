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
            for (int i = 0; i < list.size()-1 ; i++) {
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
}


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LexiconTester {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();


        String[] files = {"in.txt"};
        HashMap<String, Integer> wordsCountHashMap = new HashMap<>();
        HashMap<String, ArrayList<String>> wordNeighborHashMap = new HashMap<>();
        ArrayList<String> allWords = getAllWordsFromAFileAndItsCount(wordsCountHashMap, files);
        allWords = sortArrayOfStrings(allWords);
        addNeighbor(allWords, wordNeighborHashMap);
        try {
            for (String word :
                    allWords) {
                String line = word + " " + wordsCountHashMap.get(word) + " " + wordNeighborHashMap.get(word);
                writeOutToAFile("out.txt", line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);

    }

    public static void writeOutToAFile(String filename, String line) throws IOException {
        File file = new File(filename);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(line);
        bw.newLine();
        bw.close();
    }

    public static ArrayList<String> getAllWordsFromAFileAndItsCount(HashMap<String, Integer> wordsCountHashMap, String[] fileNames) {
        BufferedReader objReader = null;
        String[] wordsEachLine = null;
        ArrayList<String> allWords = new ArrayList<>();
        for (int i = 0; i < fileNames.length; i++) {

            try {
                String strCurrentLine;
                objReader = new BufferedReader(new FileReader(fileNames[i]));
                while ((strCurrentLine = objReader.readLine()) != null) {
                    strCurrentLine = strCurrentLine.replaceAll("[-+.^:,?'!0-9\"]", "");
                    wordsEachLine = strCurrentLine.split(" ");
                    for (String word : wordsEachLine
                    ) {
                        if (!word.equals("") && !word.equals(" ") && !word.equals("\n")) {
                            word = word.toLowerCase();
                            if (wordsCountHashMap.get(word) == null) {
                                allWords.add(word);
                            }
                            calculateEachWordCount(wordsCountHashMap, word);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (objReader != null)
                        objReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return allWords;
    }

    public static void calculateEachWordCount(HashMap<String, Integer> wordCountHashMap, String word) {
        Integer wordCount = wordCountHashMap.get(word);

        if (wordCount != null && wordCount >= 1) {
            wordCountHashMap.put(word, ++wordCount);
        } else {
            wordCountHashMap.put(word, 1);
        }
    }

    public static ArrayList<String> sortArrayOfStrings(ArrayList<String> arrayList) {
        return SortingAlgos.mergeSort(arrayList, 0, arrayList.size() - 1);
    }

    public static void addNeighbor(ArrayList<String> words, HashMap<String, ArrayList<String>> wordNeighborHashMap) {
        for (int i = 0; i < words.size(); i++) {
            ArrayList<String> neighborWords = new ArrayList<>();
            double lengthOfWord = words.get(i).length();
            String wordToFind = words.get(i);
            for (int j = 0; j < words.size(); j++) {
                double found = 0;
                if (j == i) {
                    continue;
                } else if (lengthOfWord == words.get(j).length()) {
                    String sameLengthWord = words.get(j);
                    if (lengthOfWord == 1) {
                        neighborWords.add(sameLengthWord);
                    } else {
                        for (int k = 0; k < wordToFind.length(); k++) {
                            if (sameLengthWord.charAt(k) == wordToFind.charAt(k)) {
                                ++found;
                            }
                        }
                        double percentOfSimilarity = (found / lengthOfWord) * 100;
                        if (percentOfSimilarity >= 50) {
                            neighborWords.add(sameLengthWord);
                        }
                    }
                }
            }
            neighborWords = sortArrayOfStrings(neighborWords);
            wordNeighborHashMap.put(wordToFind, neighborWords);
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws FileNotFoundException {
        findMaxInclude(getStatistics(scanAndSortFile()));
    }

    /**
     *
     * @return
     * @retrun list with sorted words.
     */
    private static List<String> scanAndSortFile() throws FileNotFoundException {
        //Scan File
        Scanner scanner = new Scanner(new File("C:\\1.txt"));

        System.out.println("Original text:");
        List<String> words = new ArrayList<>();

        //Get words and reducing different marks frome them
        while (scanner.hasNext()) {
            String word = scanner.useDelimiter("\\s+").next();
            System.out.printf("%s ",word);
            word = word.replaceAll("[^A-Za-zА-Яа-я0-9]", "").toLowerCase();
            words.add(word);
        }

        //Sort Collection of words
        Collections.sort(words);

        //Output sorted collection
        System.out.println("\nSorted :");
        System.out.println(words);

        return words;
    }

    /**
     *
     * @param words is List of sorted words, which is needed for counting each words
     * @return
     */
    private static Map<String, Integer> getStatistics(List<String> words){
        Map <String,Integer> statistics = new HashMap<>();
        Iterator<String> iterator = words.iterator();

        while(iterator.hasNext()){
            String word = iterator.next();
            Integer count = statistics.get(word);
            if (count == null) {
                count = 0;
            }
            statistics.put(word, ++count);
        }
        System.out.println("Statistics:");
        System.out.println(statistics);

        return statistics;
    }

    /**
     *
     * @param statistics is HasMap collections, which is used for getting Max coincidence
     */
    private static void findMaxInclude(Map statistics) {

        //Object for Max coincidence
        Map.Entry<String, Integer> MaxEntry = null;

        // Passing in cycle and finding max coincidence
        for (Object i : statistics.entrySet()) {
            Map.Entry<String, Integer> unit = (Map.Entry<String, Integer>) i;
            if (MaxEntry == null){
                MaxEntry = unit;
            }
            if (MaxEntry.getValue() < unit.getValue()) {
                MaxEntry = unit;
                MaxEntry.setValue(unit.getValue());
            } else if (MaxEntry.getValue() == unit.getValue()) {
                if (MaxEntry.getKey().length() < unit.getKey().length()) {
                    MaxEntry = unit;
                }
            }
        }

        //Output Max coincidence
        System.out.printf("Longest word with max included value in text: %s ",MaxEntry);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Handler {
    private List<Integer> savedNumbers = new ArrayList<>();
    private Map<String, Integer> numeralPool = new HashMap<>();

    {
        numeralPool.put("one", 1);
        numeralPool.put("two", 2);
        numeralPool.put("three", 3);
        numeralPool.put("four", 4);
        numeralPool.put("five", 5);
        numeralPool.put("six", 6);
        numeralPool.put("seven", 7);
        numeralPool.put("eight", 8);
        numeralPool.put("nine", 9);
        numeralPool.put("ten", 10);
        numeralPool.put("eleven", 11);
        numeralPool.put("twelve", 12);
        numeralPool.put("thirteen", 13);
        numeralPool.put("fourteen", 14);
        numeralPool.put("fifteen", 15);
        numeralPool.put("sixteen", 16);
        numeralPool.put("seventeen", 17);
        numeralPool.put("eighteen", 18);
        numeralPool.put("nineteen", 19);
        numeralPool.put("twenty", 20);
        numeralPool.put("thirty", 30);
        numeralPool.put("forty", 40);
        numeralPool.put("fifty", 50);
        numeralPool.put("sixty", 60);
        numeralPool.put("seventy", 70);
        numeralPool.put("eighty", 80);
        numeralPool.put("ninety", 90);
        numeralPool.put("hundred", 100);
        numeralPool.put("thousand", 1000);
    }

    public void addNewNumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numerals = null;
        try {
            numerals = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            if (numerals != null) {
                try {
                    int number = convertNumeralsToNumber(numerals);
                    savedNumbers.add(number);
                    notify();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public synchronized void showNumberAndDeleteIt() {
        if (!savedNumbers.isEmpty()) {
            Integer min = Collections.min(savedNumbers);
            savedNumbers.remove(min);
            System.out.println(min);
        } else {
            System.out.println("The store ran out of data!");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected int convertNumeralsToNumber(String numerals) throws Exception {
        String[] arrayNumerals = numerals.trim().split("\\s+");
        List<String> listNumerals = Arrays.asList(arrayNumerals);
        Collections.reverse(listNumerals);
        int result = 0;
        int storedValue = 1;
        int minValue = 1;
        int maxValue = 1000;
        boolean hundredAlreadyExists = false;
        boolean thousandAlreadyExists = false;
        for (int i = 0; i < listNumerals.size(); i++) {
            String currentWord = listNumerals.get(i).toLowerCase();
            Integer currentValue = numeralPool.get(currentWord);
            if (currentValue == null || currentValue < minValue || currentValue > maxValue ||
                    (currentValue == 100 && hundredAlreadyExists) || (currentValue == 1000 && thousandAlreadyExists)) {
                throw new Exception("Invalid input!");
            }
            if (currentValue < 10) {
                minValue = 20;
                maxValue = 1001;
            }
            if (currentValue > 9 && currentValue < 100) {
                minValue = 100;
                maxValue = 1001;
            }
            if (currentValue == 100) {
                hundredAlreadyExists = true;
                minValue = 1;
                maxValue = 9;
            }
            if (currentValue == 1000) {
                thousandAlreadyExists = true;
                minValue = 1;
                maxValue = 9;
            }
            if (currentValue == 100 && i != (listNumerals.size() - 1)) {
                storedValue = 100;
                continue;
            }
            if (currentValue == 1000 && i != (listNumerals.size() - 1)) {
                storedValue = 1000;
                continue;
            }
            result += storedValue * currentValue;
            storedValue = 1;
        }
        return result;
    }
}

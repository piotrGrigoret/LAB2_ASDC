import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\КОКСИК\\IdeaProjects\\asdc2lab\\src\\products.txt"))) {
            // Calculate the number of lines in the file
            int count = 0;
            while (scanner.hasNextLine()) {
                count++;
                scanner.nextLine();
            }
            scanner.close();

            // Initialize the arrays based on the number of lines in the file
            String[] names = new String[count];
            String[] descriptions = new String[count];
            int[] prices = new int[count];
            int[] quantities = new int[count];
            String[] units = new String[count];
            int[] ids = new int[count];

            // Read the file and fill the arrays with data
            Scanner scanner1 = new Scanner(new File("C:\\Users\\КОКСИК\\IdeaProjects\\asdc2lab\\src\\products.txt"));
            int i = 0;
            while (scanner1.hasNextLine()) {
                String[] line = scanner1.nextLine().split(";");
                // Check if the line contains all the necessary fields
                if (line.length == 6) { // need 6 fields
                    names[i] = line[0];
                    descriptions[i] = line[1];
                    prices[i] = Integer.parseInt(line[2]);
                    quantities[i] = Integer.parseInt(line[3]);
                    units[i] = line[4];
                    ids[i] = Integer.parseInt(line[5]);
                    i++;
                }
            }
            scanner1.close();

            // Sort the array using selection sort
            int comparisons = 0;
            int swaps = 0;
            long startTime = System.nanoTime();
            for (i = 0; i < ids.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < ids.length; j++) {
                    comparisons++;
                    if (ids[j] < ids[minIndex]) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    swap(names, i, minIndex);
                    swap(descriptions, i, minIndex);
                    swap(prices, i, minIndex);
                    swap(quantities, i, minIndex);
                    swap(units, i, minIndex);
                    swap(ids, i, minIndex);
                    swaps++;
                }
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // время выполнения в миллисекундах

            // выводим результаты
            System.out.println("Сортировка выбором:");
            System.out.println("Теоретическая оценка сложности: O(n^2)");
            System.out.println("Количество сравнений: " + comparisons);
            System.out.println("Количество перестановок: " + swaps);
            System.out.println("Время выполнения алгоритма: " + duration + " мс");
            System.out.println("Отсортированный массив:");
            for (i = 0; i < count; i++) {
                System.out.println(names[i] + ";" + descriptions[i] + ";" + prices[i] + ";" + quantities[i] + ";" + units[i] + ";" + ids[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    public static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
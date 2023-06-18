import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Tovar {
    String name;
    String description;
    int price;
    int number;
    String unit;
    long idnp;

    public Tovar(String name, String description, int price, int number, String unit, long idnp) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.number = number;
        this.unit = unit;
        this.idnp = idnp;
    }
}

public class SelectionSort {

    public static void main(String[] args) {
        Tovar[] tovars = readTovarsFromFile("C:\\Users\\КОКСИК\\IdeaProjects\\asdc2lab\\src\\products.txt");
        printTovars(tovars);
        sortAndPrint(tovars, "idnp");
        sortAndPrint(tovars, "name");
        sortAndPrint(tovars, "description");
        sortAndPrint(tovars, "price");
        sortAndPrint(tovars, "number");
        sortAndPrint(tovars, "unit");
        // sortAndPrint(tovars, "invalid_key"); // для тестирования исключения IllegalArgumentException
    }

    public static Tovar[] readTovarsFromFile(String fileName) {
        ArrayList<Tovar> tovars = new ArrayList<Tovar>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                String name = fields[0];
                String description = fields[1];
                int price = Integer.parseInt(fields[2].trim());
                int number = Integer.parseInt(fields[3].trim());
                String unit = fields[4];
                long idnp = Long.parseLong(fields[5].trim());
                tovars.add(new Tovar(name, description, price, number, unit, idnp));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tovars.toArray(new Tovar[tovars.size()]);
    }

    public static void selectionSort(Tovar[] tovars, String key) {
        int comparisons = 0;
        int swaps = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < tovars.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tovars.length; j++) {
                comparisons++;
                switch (key) {
                    case "idnp":
                        if (tovars[j].idnp < tovars[minIndex].idnp) {
                            minIndex = j;
                        }
                        break;
                    case "name":
                        if (tovars[j].name.compareToIgnoreCase(tovars[minIndex].name) < 0) {
                            minIndex = j;
                        }
                        break;
                    case "description":
                        if (tovars[j].description.compareToIgnoreCase(tovars[minIndex].description) < 0) {
                            minIndex = j;
                        }
                        break;
                    case "price":
                        if (tovars[j].price < tovars[minIndex].price) {
                            minIndex = j;
                        }
                        break;
                    case "number":
                        if (tovars[j].number < tovars[minIndex].number) {
                            minIndex = j;
                        }
                        break;
                    case "unit":
                        if (tovars[j].unit.compareToIgnoreCase(tovars[minIndex].unit) < 0) {
                            minIndex = j;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid key: " + key);
                }
            }
            if (i != minIndex) {
                Tovar temp = tovars[i];
                tovars[i] = tovars[minIndex];
                tovars[minIndex] = temp;
                swaps++;
            }
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Sorting by " + key + ":");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Time taken: " + duration + " ns");
        System.out.println();
    }

    public static void printTovars(Tovar[] tovars) {
        System.out.println("All products:");
        for (Tovar tovar : tovars) {
            System.out.println(tovar.name + " - " + tovar.description + " - " + tovar.price + " - " +
                    tovar.number + " " + tovar.unit + " - " + tovar.idnp);
        }
        System.out.println();
    }

    public static void sortAndPrint(Tovar[] tovars, String key) {
        Tovar[] sortedTovars = tovars.clone();
        selectionSort(sortedTovars, key);
        System.out.println("Sorted products by " + key + ":");
        for (Tovar tovar : sortedTovars) {
            System.out.println(tovar.name + " - " + tovar.description + " - " + tovar.price + " - " +
                    tovar.number + " " + tovar.unit + " - " + tovar.idnp);
        }
        System.out.println();
    }
}

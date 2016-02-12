import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static HashMap<Character, ArrayList<String>> countries = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        readFile("countries.txt");
        while (true) {
            System.out.print("Enter a letter:");

            String fileName = scanner.nextLine().toUpperCase() + "_Countries.txt";
            String fileContent = fileContent(fileName);
            writeFile(fileName, fileContent);

        }



    }

    static String fileContent(String fileName){
        ArrayList<String> selectedCountries = new ArrayList<>();
        for (String country: countries.get(fileName.toLowerCase().charAt(0))){
            selectedCountries.add(country);
        }
        return String.join(", ", selectedCountries);


    }

    static void readFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country();
            country.setAbbreviation(columns[0]);
            country.setCountry(columns[1]);
            if (countries.containsKey(columns[1].charAt(0))){
                countries.get(columns[1].charAt(0)).add(columns[1]);
            }
            else {
                countries.put(columns[1].charAt(0), new ArrayList<String>());
                countries.get(columns[1].charAt(0)).add(columns[1]);
            }
        }
    }

    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}

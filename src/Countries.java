import jodd.json.JsonSerializer;
import sun.plugin2.message.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static HashMap<Character, ArrayList<Country>> countries = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        readFile("countries.txt");
        while (true) {
            printMap();
            System.out.print("Enter a letter:");

            String response = validResponse();

            String fileName = response.toUpperCase() + "_Countries.txt";
            ArrayList<Country> fileContent = fileContent(fileName);
            writeFile(fileName, fileContent);
        }
    }

    static String validResponse(){
        boolean valid = false;
        while (!valid){
            String response = scanner.nextLine();
            if (response.length() == 1){
                valid = true;
                return response;
            } else{
                System.out.println("Please enter a single letter:");
            }
        }
        return null;
    }

    static void printMap(){
        System.out.println(countries.toString());

    }

    static ArrayList<Country> fileContent(String fileName){
        ArrayList<Country> selectedCountries = new ArrayList<>();
        for (Country country: countries.get(fileName.toLowerCase().charAt(0))){
            selectedCountries.add(country);
        }
        return selectedCountries;
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
                countries.get(columns[1].charAt(0)).add(country);
            }
            else {
                countries.put(columns[1].charAt(0), new ArrayList<Country>());
                countries.get(columns[1].charAt(0)).add(country);
            }
        }
    }

    static void writeFile(String fileName, ArrayList<Country> fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        JsonSerializer serializer = new JsonSerializer();
        String json = "";
        for (Country country:fileContent){
            json += (serializer.serialize(country) + "\n");
        }
        fw.write(json);
        fw.close();
    }
}

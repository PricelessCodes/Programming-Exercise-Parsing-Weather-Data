
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp != -9999 && currentTemp < smallestTemp) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    public File getSmallestOfTwoNames (CSVRecord currentRow, CSVRecord smallestSoFar, File f, File fileWithSmallest) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
            fileWithSmallest = f;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp != -9999 && currentTemp < smallestTemp) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
                fileWithSmallest = f;
            }
        }
        return fileWithSmallest;
    }
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest hour in file " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        File fileWithSmallest = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            fileWithSmallest = getSmallestOfTwoNames(currentRow, smallestSoFar, f , fileWithSmallest);
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return fileWithSmallest.getName();
    }
    
    public void testFileWithColdestTemperature () {
        String fileWithSmallest = fileWithColdestTemperature();
        System.out.println("coldest file " + fileWithSmallest);
        FileResource fr = new FileResource("nc_weather/2013/" + fileWithSmallest);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest hour in file " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getlowestOfTwoHumidity(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public CSVRecord getlowestOfTwoHumidity (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            if (!currentRow.get("Humidity").equalsIgnoreCase("N/A"))
            {
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < smallestTemp) {
                    //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    
    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity In File " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles () {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getlowestOfTwoHumidity(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public void testLowestHumidityInManyFiles () {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("coldest hour in file " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
        double total = 0;
        int count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            double temp = Double.parseDouble(currentRow.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (temp != -9999) {
                total += temp;
            }
            count++;
        }
        //The largestSoFar is the answer
        return (total/count);
    }

    public void testAverageTemperatureInFile () {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-08-10.csv");
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("average of temp " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile  (CSVParser parser, int value) {
        double total = 0;
        int count = 0;
        
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentRow.get("Humidity") != "N/A")
            {
                double Humidity = Double.parseDouble(currentRow.get("Humidity"));
                if (Humidity >= value) {
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentTemp != -9999) {
                total += currentTemp;
                count++;
            }
            }
            }
        }
        //The largestSoFar is the answer
        return (total/count);
    }

    public void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-09-02.csv");
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        System.out.println("average of Humidity " + average);
    }

}

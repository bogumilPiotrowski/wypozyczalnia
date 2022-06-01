package com.auto;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileMenagement {

    public List<Car> wszytajSamochodyZPliku(String filePath) throws FileNotFoundException {

        ArrayList<Car> result = new ArrayList<>();

        Scanner s = new Scanner(new FileReader(filePath));
        while (s.hasNext()) {
            String[] car = s.nextLine().split(";");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            Car newCar = new Car(car[0], car[1], LocalDateTime.parse(car[2], formatter), Integer.parseInt(car[3]));
            result.add(newCar);
        }
        return result;

    }


    public void zapiszDoPliku(String ścieżkadopliku, List<Car> listaSamochodów) throws IOException {
        try {
            FileWriter writer = new FileWriter(ścieżkadopliku);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            for (Car car : listaSamochodów) {
                writer.write(car.getBrand() + ";" + car.getModel() + ";" + car.getProductionDate().format(formatter) + ";" + car.getMileage() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

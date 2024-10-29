package org.mrprogre.copy.utils;

import org.mrprogre.copy.model.Record;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Формат списка: CSV, TXT
 */

public class ListReader {

    public static List<Record> read(String csvFilePath) {
        String separator = ";";
        List<Record> fileNames = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(csvFilePath)),
                StandardCharsets.UTF_8))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(",")) separator = ",";
                String[] fields = line.split(separator);

                fileNames.add(new Record(fields[0].trim(), Integer.parseInt(fields[1].trim())));
            }

        } catch (Exception e) {
            Common.showAlert("The list is empty or does not exist");
        }

        return fileNames;
    }

}
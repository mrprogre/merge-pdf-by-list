package org.mrprogre.copy.utils;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.mrprogre.copy.model.Record;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Merge {

    public static void mergePdfFiles(List<String> fileNamesToCopy, String outputFile) {
        PDFMergerUtility merger = new PDFMergerUtility();

        try {
            for (String sourceFile : fileNamesToCopy) {
                merger.addSource(new File(sourceFile));
            }
            merger.setDestinationFileName(outputFile);

            // Выполняем объединение один раз
            merger.mergeDocuments(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String start(String sourceDir, String targetDir, List<Record> fileNamesToCopy) {
        int counter = 0;
        File sourceDirectory = new File(sourceDir);
        File targetDirectory = new File(targetDir);
        List<String> pdfList = new LinkedList<>();

        // Создание целевой директории, если она не существует
        if (!targetDirectory.exists()) {
            boolean created = targetDirectory.mkdirs();
            if (created) {
                System.out.println("target directory created: " + targetDir);
            }
        }

        File[] files = sourceDirectory.listFiles();

        if (files != null) {
            for (Record record : fileNamesToCopy) {
                String name = Common.removeSpecialCharacters(record.getName());
                int quantity = record.getQuantity();

                for (File file : files) {
                    String recordName = Common.removeSpecialCharacters(file.getName());

                    if (recordName.contains(name)) {
                        if (!file.exists() || quantity < 1) {
                            return "invalid file or quantity";
                        }

                        for (int i = 1; i <= quantity; i++) {
                            System.out.println(sourceDir + File.separator + file.getName());
                            pdfList.add(sourceDir + File.separator + file.getName());
                        }

                        counter++;
                    }
                }
            }

            // Объединяем файлы после завершения добавления
            mergePdfFiles(pdfList, targetDir + File.separator + "all_pdf_files.pdf");

            return "merged files " + counter + " of " + fileNamesToCopy.size() +
                    " (" + (int) (((double) counter / fileNamesToCopy.size()) * 100) + " %)";

        } else {
            return Common.ERR_TEXT_SPECIFY_ALL;
        }
    }

}

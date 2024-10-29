package org.mrprogre.copy.utils;

import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;

public final class Common {
    public static final String ERR_TEXT_SPECIFY_ALL = "specify all paths to files and folders";

    public static void setTheme() {
        UIManager.put("Component.arc", 10);
        UIManager.put("ProgressBar.arc", 6);
        UIManager.put("Button.arc", 8);
        UIManager.put("TextField.background", Color.GRAY);
        UIManager.put("TextField.foreground", Color.BLACK);
        FlatCobalt2IJTheme.setup();
    }

    public static void showAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // В тексте могут быть символы переноса строки, которых не видно визуально
    public static String removeSpecialCharacters(String str) {
        return str.replaceAll("[^а-яА-Яa-zA-Z0-9.]", "");
    }

    // Выбрать путь к папке, где находятся файлы для печати по списку
    public static String getPathToFiles() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File file = new File(System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "Desktop");
        chooser.setCurrentDirectory(file);
        int res = chooser.showDialog(null, "Select Directory");

        if (res == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    // Выбрать путь к списку файлов для печати
    public static String getPathToList() {
        JFileChooser chooser = new JFileChooser();
        File file = new File(System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "Desktop");
        chooser.setCurrentDirectory(file);
        int res = chooser.showDialog(null, "Open");

        if (res == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return "";
    }

    public static void openPage(String url) {
        if (url != null && !url.equals("no data found")) {
            url = url.replaceAll(("https://|http://"), "");

            URI uri;
            try {
                uri = new URI("https://" + url);

            Desktop desktop = Desktop.getDesktop();

                desktop.browse(uri);
            } catch (Exception e) {
                showAlert(e.getMessage());
            }
        }
    }

}
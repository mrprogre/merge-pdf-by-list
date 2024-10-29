package org.mrprogre.copy.gui;

import org.mrprogre.copy.model.Record;
import org.mrprogre.copy.utils.ListReader;
import org.mrprogre.copy.utils.Common;
import org.mrprogre.copy.utils.Merge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Gui extends JFrame {
    private final JLabel status;
    private static final Font GUI_FONT = new Font("Tahoma", Font.BOLD, 14);
    private static final Color GUI_COLOR = new Color(234, 234, 234);
    private static final Color GUI_FONT_COLOR = new Color(48, 0, 0);
    private static final Color BTN_FONT_COLOR = new Color(216, 216, 216);
    public static final ImageIcon LOGO_ICON = new ImageIcon(Toolkit.getDefaultToolkit()
            .createImage(Gui.class.getResource("/logo.png")));

    private String pathFrom = "D:\\from";
    private String pathTo = "D:\\to";
    private String pathToList = "D:\\list.csv";

    public Gui() {
        Common.setTheme();

        setName("CopyFilesByList");
        setResizable(false);
        getContentPane().setBackground(GUI_COLOR);
        setIconImage(LOGO_ICON.getImage());
        setTitle("Merge files by list");
        setFont(GUI_FONT);
        setBounds(680, 300, 480, 204);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // BOUNDS
        int objectX = 10;
        int objectY = 10;
        int objectWidth = 90;
        int objectHeight = 30;
        int buttonWidth = 100;

        // FROM
        JLabel filesLabel = new JLabel("Откуда");
        filesLabel.setFont(GUI_FONT);
        filesLabel.setBounds(objectX, objectY - 2, objectWidth - 10, objectHeight);
        filesLabel.setForeground(GUI_FONT_COLOR);
        getContentPane().add(filesLabel);

        JLabel pathToFilesLabel = new JLabel(pathFrom);
        pathToFilesLabel.setFont(GUI_FONT);
        pathToFilesLabel.setBounds(objectWidth + buttonWidth + 10, objectY - 2, 400, objectHeight);
        pathToFilesLabel.setForeground(GUI_FONT_COLOR);
        getContentPane().add(pathToFilesLabel);

        JButton getFilesDirectoryPath = new JButton("find");
        getFilesDirectoryPath.setFont(GUI_FONT);
        getFilesDirectoryPath.setForeground(BTN_FONT_COLOR);
        getFilesDirectoryPath.setBounds(objectWidth - 10, objectY, buttonWidth, objectHeight);
        getFilesDirectoryPath.addActionListener(x -> {
            pathFrom = Common.getPathToFiles();
            pathToFilesLabel.setText(pathFrom);
            pathToFilesLabel.setToolTipText(pathFrom);
        });
        getContentPane().add(getFilesDirectoryPath);

        // COPY TO
        JLabel filesDestinationLabel = new JLabel("Куда");
        filesDestinationLabel.setFont(GUI_FONT);
        filesDestinationLabel.setBounds(objectX, objectY + 40 - 2, objectWidth - 10, objectHeight);
        filesDestinationLabel.setForeground(GUI_FONT_COLOR);
        getContentPane().add(filesDestinationLabel);

        JLabel pathToFilesDestinationLabel = new JLabel(pathTo);
        pathToFilesDestinationLabel.setFont(GUI_FONT);
        pathToFilesDestinationLabel.setBounds(objectWidth + buttonWidth + 10, objectY + 40 - 2, 400, objectHeight);
        pathToFilesDestinationLabel.setForeground(GUI_FONT_COLOR);
        getContentPane().add(pathToFilesDestinationLabel);

        JButton getFilesDestinationDirectoryPath = new JButton("find");
        getFilesDestinationDirectoryPath.setFont(GUI_FONT);
        getFilesDestinationDirectoryPath.setForeground(BTN_FONT_COLOR);
        getFilesDestinationDirectoryPath.setBounds(objectWidth - 10, objectY + 40, buttonWidth, objectHeight);
        getFilesDestinationDirectoryPath.addActionListener(x -> {
            pathTo = Common.getPathToFiles();
            pathToFilesDestinationLabel.setText(pathTo);
            pathToFilesDestinationLabel.setToolTipText(pathTo);
        });
        getContentPane().add(getFilesDestinationDirectoryPath);

        // LIST TO PRINT
        JLabel listToPrintLabel = new JLabel("Список");
        listToPrintLabel.setFont(GUI_FONT);
        listToPrintLabel.setBounds(objectX, objectY + 80 - 2, 60, objectHeight);
        listToPrintLabel.setForeground(GUI_FONT_COLOR);
        getContentPane().add(listToPrintLabel);

        JLabel pathToListLabel = new JLabel(pathToList);
        pathToListLabel.setFont(GUI_FONT);
        pathToListLabel.setBounds(objectWidth + buttonWidth + 10, objectY + 80 - 2, 400, objectHeight);
        pathToListLabel.setForeground(GUI_FONT_COLOR);
        getContentPane().add(pathToListLabel);

        JButton getCsvPath = new JButton("find");
        getCsvPath.setFont(GUI_FONT);
        getCsvPath.setForeground(BTN_FONT_COLOR);
        getCsvPath.setBounds(objectWidth - 10, objectY + 80, buttonWidth, objectHeight);
        getCsvPath.addActionListener(x -> {
            pathToList = Common.getPathToList();
            pathToListLabel.setText(pathToList);
            pathToListLabel.setToolTipText(pathToList);
        });
        getContentPane().add(getCsvPath);

        // Merge
        JButton mergeButton = new JButton("Merge PDF");
        mergeButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        mergeButton.setForeground(BTN_FONT_COLOR);
        mergeButton.setBounds(objectX - 5, objectY + 120, buttonWidth + 20, objectHeight);
        mergeButton.addActionListener(x -> {
                    if (!pathToList.isEmpty() && !pathFrom.isEmpty() && !pathTo.isEmpty()) {
                        List<Record> fileNamesToCopy = ListReader.read(pathToList);
                        String result = Merge.start(pathFrom, pathTo, fileNamesToCopy);
                        setStatus(result);
                    } else {
                        setStatus("укажите все пути к файлам и папкам");
                    }
                }
        );
        getContentPane().add(mergeButton);

        // Статус
        status = new JLabel();
        status.setFont(GUI_FONT);
        status.setBounds(objectX + buttonWidth + 25, objectY + 120, 400, objectHeight);
        status.setForeground(GUI_FONT_COLOR);
        getContentPane().add(status);

        // Подпись
        JLabel sign = new JLabel("developer");
        sign.setToolTipText("click to open page");
        sign.setForeground(new Color(123, 84, 0));
        sign.setBounds(400, 0, 60, 24);
        getContentPane().add(sign);
        animation(sign);

        // Отображение приложения
        setVisible(true);
    }

    public void setStatus(String message) {
        status.setText("»»» " + message);
    }

    public static void animation(JComponent component) {

        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                component.setEnabled(false);
                component.setForeground(new Color(123, 84, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                component.setEnabled(true);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Common.openPage("https://github.com/mrprogre");
            }
        });
    }

}
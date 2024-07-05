package eightman.library.GUI.GUI_tool;

import de.matthiasmann.dds4j.DDSImage;
import de.matthiasmann.dds4j.DDSWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static eightman.library.GUI.language.CVRT;

public class Convert_GUI extends JFrame {
    private JPanel upperArea;
    private JPanel lowerArea;
    private JButton convertButton;

    public Convert_GUI() {
        initializeUI();
        setupDragAndDrop();
        setupConvertButtonAction();
    }

    private void setupConvertButtonAction() {
        convertButton.addActionListener(e -> {
            Component[] components = upperArea.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    String fileName = ((JLabel) component).getText();
                    File inputFile = new File(fileName);
                    try {
                        // Convert and save the DDS file
                        File outputFile = new File(".\\Hoi4_modding_Tool\\output\\" + inputFile.getName().replaceAll("\\.(jpeg|png|tga)$", ".dds"));
                        convertToDDS(inputFile, outputFile);

                        // Display the converted file name in the lower area
                        lowerArea.add(new JLabel(outputFile.getName()));
                        lowerArea.revalidate();
                        lowerArea.repaint();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    private void convertToDDS(File inputFile, File outputFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            BufferedImage image = ImageIO.read(fis);
            DDSWriter.write(fos, image, DDSImage.CompressionType.DXT5);
        }
    }

    public void initializeUI() {
        setTitle(CVRT);
        setSize(600, 700);
        setLayout(new BorderLayout());

        upperArea = new JPanel();
        lowerArea = new JPanel();
        convertButton = new JButton(CVRT);

        add(upperArea, BorderLayout.NORTH);
        add(lowerArea, BorderLayout.SOUTH);
        add(convertButton, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupDragAndDrop() {
        upperArea.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    java.util.List<File> droppedFiles = (java.util.List<File>)
                            evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        if (file.getName().matches(".*\\.(jpeg|png|tga)$")) {
                            // Display the file name in the upper area or handle as needed
                            upperArea.add(new JLabel(file.getName()));
                            upperArea.revalidate();
                            upperArea.repaint();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Convert_GUI::new);
    }
}
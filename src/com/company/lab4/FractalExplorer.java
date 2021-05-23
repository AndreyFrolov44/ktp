package com.company.lab4;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay image;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    private int rowsRemaining;

    public FractalExplorer(int size) {
        displaySize = size;
        range = new Rectangle2D.Double();
        fractal = new Mandelbrot();
        fractal.getInitialRange(range);
        image = new JImageDisplay(displaySize, displaySize);
    }

    private JComboBox comboBox = new JComboBox();
    private JButton saveButton = new JButton("Save");
    private JButton resetButton = new JButton("Reset");

    public void createAndShowGUI(){
        image.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal Explorer");
        frame.add(image, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton resetButton = new JButton("Reset Display");
        Handler resetHandler = new Handler();
        Handler saveHandler = new Handler();
        resetButton.addActionListener(resetHandler);
        saveButton.addActionListener(saveHandler);
        buttons.add(saveButton);
        buttons.add(resetButton);
        frame.add(buttons, BorderLayout.SOUTH);
        MouseHandler click = new MouseHandler();
        image.addMouseListener(click);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        Handler handler = new Handler();
        comboBox.addActionListener(handler);
        JLabel label = new JLabel("Fractal:");
        JPanel fractalChoicePanel = new JPanel();
        fractalChoicePanel.add(label);
        fractalChoicePanel.add(comboBox);
        frame.add(fractalChoicePanel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void enableUI(boolean val) {
        comboBox.setEnabled(val);
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
    }

    private void drawFractal()
    {
        /** Loop through every pixel in the display */
//        for (int x=0; x<displaySize; x++){
//            for (int y=0; y<displaySize; y++){
//                double xCoord = fractal.getCoord(
//                        range.x, range.x + range.width, displaySize, x);
//                double yCoord = fractal.getCoord(
//                        range.y, range.y + range.height, displaySize, y);
//                int iteration = fractal.numIterations(xCoord, yCoord);
//                if (iteration == -1) {
//                    image.drawPixel(x, y, 0);
//                }
//                else {
//                    float hue = 0.7f + (float) iteration / 200f;
//                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
//                    image.drawPixel(x, y, rgbColor);
//                }
//
//            }
//        }
//        image.repaint();

        enableUI(false);

        rowsRemaining = displaySize;

        /** Loop through every row in the display */
        for (int x = 0; x < displaySize; x++) {
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }
    }

    private class Handler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if (command.equals("Save")) {
                JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);

                int userSelection = chooser.showSaveDialog(image);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = chooser.getSelectedFile();

                    try {
                        BufferedImage displayImage = image.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }

                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(image, exception.getMessage(),"Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (rowsRemaining != 0) return;
            int x = e.getX();
            double xCoord = fractal.getCoord(
                    range.x, range.x + range.width, displaySize, x);
            int y = e.getY();
            double yCoord = fractal.getCoord(
                    range.y, range.y + range.height, displaySize, y);
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(500);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }

    private class FractalWorker extends SwingWorker<Object, Object>{
        int yCoordInt;
        int[] RGBList;

        private FractalWorker(int row){
            yCoordInt = row;
        }

        protected Object doInBackground() {
            RGBList = new int[displaySize];

            for (int i = 0; i < RGBList.length; i++) {
                /**
                 * Find the corresponding coordinates xCoord and yCoord
                 * in the fractal's display area.
                 */
                double xCoord = fractal.getCoord(range.x,
                        range.x + range.width, displaySize, i);
                double yCoord = fractal.getCoord(range.y,
                        range.y + range.height, displaySize, yCoordInt);

                /**
                 * Compute the number of iterations for the coordinates in
                 * the fractal's display area.
                 */
                int iteration = fractal.numIterations(xCoord, yCoord);

                /** If number of iterations is -1, set the pixel to black. */
                if (iteration == -1){
                    RGBList[i] = 0;
                }

                else {
                    /**
                     * Otherwise, choose a hue value based on the number
                     * of iterations.
                     */
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    /** Update the display with the color for each pixel. */
                    RGBList[i] = rgbColor;
                }
            }
            return null;
        }

        protected void done(){
            for (int i = 0; i < RGBList.length; i++) {
                image.drawPixel(i, yCoordInt, RGBList[i]);
            }

            image.repaint(0, 0, yCoordInt, displaySize, 1);
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

}

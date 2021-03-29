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

    public FractalExplorer(int size) {
        displaySize = size;
        range = new Rectangle2D.Double();
        fractal = new Mandelbrot();
        fractal.getInitialRange(range);
        image = new JImageDisplay(displaySize, displaySize);
    }

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

    private void drawFractal()
    {
        /** Loop through every pixel in the display */
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){
                double xCoord = fractal.getCoord(
                        range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(
                        range.y, range.y + range.height, displaySize, y);
                int iteration = fractal.numIterations(xCoord, yCoord);
                if (iteration == -1) {
                    image.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    image.drawPixel(x, y, rgbColor);
                }

            }
        }
        image.repaint();
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
}

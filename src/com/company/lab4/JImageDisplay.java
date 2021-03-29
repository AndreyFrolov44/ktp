package com.company.lab4;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;


class JImageDisplay extends JComponent{
    private BufferedImage img;

    public JImageDisplay(int width, int height){
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);
    }

    public BufferedImage getImage(){
        return img;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage (img, 0, 0, img.getWidth(), img.getHeight(), null);
    }

    public void clearImage(){
        Graphics g = img.getGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
    }

    public void drawPixel(int x, int y, int rgbColor){
        img.setRGB(x, y, rgbColor);
    }
}
package com.company.lab4;

import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range){
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }

    public int numIterations(double x, double y){
        int iteration = 0;
        double zx = 0;
        double zy = 0;
        while (iteration < MAX_ITERATIONS &&
                zx * zx + zy * zy < 4)
        {
            double xtemp = zx * zx - zy * zy + x;
            zy = -2 * zx * zy + y;
            zx = xtemp;
            iteration += 1;
        }
        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }

        return iteration;
    }

    public String toString(){
        return "Tricorn";
    }
}

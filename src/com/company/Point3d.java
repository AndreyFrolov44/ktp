package com.company;

public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;

    public Point3d(double x, double y, double z){
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d(){
        this(0, 0, 0);
    }

    public void setX(double val){
        xCoord = val;
    }

    public void setY(double val){
        yCoord = val;
    }

    public void setZ(double val){
        zCoord = val;
    }

    public double getX(){
        return xCoord;
    }

    public double getY(){
        return yCoord;
    }

    public double getZ(){
        return zCoord;
    }

    public double distanceTo(Point3d anotherPoint) {
        return Math.sqrt(Math.pow(xCoord - anotherPoint.getX(), 2) + Math.pow(yCoord - anotherPoint.getY(), 2) + Math.pow(zCoord - anotherPoint.getZ(), 2));
    }
}

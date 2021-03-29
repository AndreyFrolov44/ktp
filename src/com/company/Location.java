package com.company;
import java.util.*;

public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;
        Location l = (Location) o;
        return xCoord == l.xCoord && yCoord == l.yCoord;
    }

    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }
}

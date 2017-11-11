package eliseGL;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

/**
 * Created by Elise Haram Vannes on 05.11.2017.
 */
// custom implemented BoundingBox-class
public class CollisionBox3D{

    private double xPos;
    private double yPos;
    private double zPos;
    private double width;
    private double height;

    public CollisionBox3D(double minX, double minY, double minZ, double width, double height, double depth) {
        this.xPos = minX;
        this.yPos = minY;
        this.zPos = minZ;
    }
    /*
    public boolean isEmpty() {
        return getMaxX() < getMinX() || getMaxY() < getMinY() || getMaxZ() < getMinZ();
    }

    public boolean contains(Point2D p) {
        if (p == null) return false;
        return contains(p.getX(), p.getY(), 0.0f);
    }

    public boolean contains(Point3D p) {
        if (p == null) return false;
        return contains(p.getX(), p.getY(), p.getZ());
    }

    public boolean contains(double x, double y) {
        return contains(x, y, 0.0f);
    }

    public boolean contains(double x, double y, double z) {
        if (isEmpty()) return false;
        return x >= getMinX() && x <= getMaxX() && y >= getMinY() && y <= getMaxY()
                && z >= getMinZ() && z <= getMaxZ();
    }

    public boolean contains(Bounds b) {
        if ((b == null) || b.isEmpty()) return false;
        return contains(b.getMinX(), b.getMinY(), b.getMinZ(),
                b.getWidth(), b.getHeight(), b.getDepth());
    }

    public boolean contains(double x, double y, double w, double h) {
        return contains(x, y) && contains(x + w, y + h);
    }

    public boolean contains(double x, double y, double z, double w, double h, double d) {
        return contains(x, y, z) && contains(x + w, y + h, z + d);
    }
    /*
    public boolean intersects(Bounds b) {
        if ((b == null) || b.isEmpty()) return false;
        return intersects(b.getMinX(), b.getMinY(), b.getMinZ(),
        b.getWidth(), b.getHeight(), b.getDepth());
    }

    public boolean intersects(double x, double y, double w, double h) {
        return intersects(x, y, 0, w, h, 0);
    }

    public boolean intersects(double x, double y, double z,
            double w, double h, double d) {
       if (isEmpty() || w < 0 || h < 0 || d < 0) return false;
            return (x + w >= getMinX() &&
                 y + h >= getMinY() &&
                 z + d >= getMinZ() &&
                 x <= getMaxX() &&
                 y <= getMaxY() &&
                 z <= getMaxZ());
    }*/

    public void setXpos(double x){
        this.xPos = x;
    }

    public void setYpos(double y){
        this.yPos = y;
    }

    public void setZpos(double z){
        this.zPos = z;
    }

    public double getxPos(){
        return xPos;
    }

    public double getyPos(){
        return yPos;
    }

    public double getzPos(){
        return zPos;
    }
}

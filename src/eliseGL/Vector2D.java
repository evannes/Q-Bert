package eliseGL;

/**
 * Created by Elise Haram Vannes on 18.09.2017.
 */
public class Vector2D {
    public double horizontal; // x
    public double vertical;   // y

    public Vector2D(){}

    public Vector2D(int horizontal, int vertical){
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Vector2D(Vector2D vector){
        this.horizontal = vector.getHorizontal();
        this.vertical = vector.getVertical();
    }

    public void setVector(double horizontal, double vertical){
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public double getHorizontal(){
        return horizontal;
    }

    public double getVertical(){
        return vertical;
    }

    public Vector2D getVector(){
        return this;
    }
}

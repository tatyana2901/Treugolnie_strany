public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double calcDistTo(Point newPoint) {
        return Math.sqrt((newPoint.getX()-getX()) * (newPoint.getX()-getX()) + (newPoint.getY()-getY())*(newPoint.getY()-getY()));
    }
}

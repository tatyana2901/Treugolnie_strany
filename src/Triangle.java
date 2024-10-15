import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Triangle {
    Point a;
    Point b;
    Point c;

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double calcPerimeter() {
        return getA().calcDistTo(getB())+getA().calcDistTo(getC())+getB().calcDistTo(getC());
    }

    public double calcArea() {
        double halfPer = calcPerimeter() / 2;
        return Math.sqrt(halfPer * (halfPer - getA().calcDistTo(getB())) * (halfPer - getB().calcDistTo(getC())) * (halfPer - getA().calcDistTo(getC())));
    }

    public boolean ifContains(Point point) {
        double areaThis = calcArea();
        double area1 = new Triangle(point, getB(), getC()).calcArea();
        double area2 = new Triangle(point, getA(), getB()).calcArea();
        double area3 = new Triangle(point, getA(), getC()).calcArea();
        double sumOfAreas = area1 + area2 + area3;
        return !(Math.round(sumOfAreas * 100.0) / 100.0 > Math.round(areaThis * 100.0) / 100.0 || Math.round(area1 * 100.0) / 100.0 == 0 || Math.round(area2 * 100.0) / 100.0 == 0 || Math.round(area3 * 100.0) / 100.0 == 0);
    }

    public static void printResult(ArrayList<Point> points) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        int count = 0;
        for (int i = 1; i < points.size(); i = i + 3) {
            if ((new Triangle(points.get(i), points.get(i + 1), points.get(i + 2))).ifContains(points.getFirst())) {
                arr.set(0, arr.getFirst() + 1);
                count++;
                arr.add(count);
            }
            else count++;
        }
        System.out.println(arr.getFirst());
        for (int i = 1; i <arr.size() ; i++) {
            System.out.print(arr.get(i) + " ");
        }
    }

    public static ArrayList<Point> getDataFromFile(String fileName) {
        ArrayList<Point> arr = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int counter = 0;
            while (scanner.hasNext()) {
                if (counter == 1) {
                    scanner.nextInt();
                } else {
                    arr.add(new Point(scanner.nextInt(), scanner.nextInt()));
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }

    public static void main(String[] args) {

     printResult(getDataFromFile("notes.txt"));

    }
}

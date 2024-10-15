import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
   /* public static void main(String[] args) {
        printResult(solution(getDataFromFile("notes.txt")));
    }*/


    public static ArrayList<Integer> getDataFromFile(String fileName) {
        ArrayList<Integer> arr = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                arr.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }


    public static double calcSegment(int fromX, int toX, int fromY, int toY) {
        return Math.sqrt((fromX - toX) * (fromX - toX) + (fromY - toY) * (fromY - toY));
    }

    public static double calcTriangleArea(double segmentA, double segmentB, double segmentC) {
        double half_perimeter = (segmentA + segmentB + segmentC) / 2;
        return Math.sqrt(half_perimeter * (half_perimeter - segmentA) * (half_perimeter - segmentB) * (half_perimeter - segmentC));
    }

    public static ArrayList<Integer> solution(ArrayList<Integer> arraylist) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        ArrayList<Integer> copyOf = new ArrayList<>(arraylist);
        copyOf.removeFirst();
        copyOf.removeFirst();
        copyOf.removeFirst();

        int cordinateX = arraylist.getFirst();
        int cordinateY = arraylist.get(1);
        int countOfTriangles = arraylist.get(2);//?нужно или нет??

        int mainCounter = 0;

        for (int i = 0; i < copyOf.size(); i = i + 6) {
            mainCounter++;
            //считаем площадь треугольника - страны:
            double segmentAB = calcSegment(copyOf.get(i), copyOf.get(i + 2), copyOf.get(i + 1), copyOf.get(i + 3));

            double segmentBC = calcSegment(copyOf.get(i + 2), copyOf.get(i + 4), copyOf.get(i + 3), copyOf.get(i + 5));

            double segmentAC = calcSegment(copyOf.get(i), copyOf.get(i + 4), copyOf.get(i + 1), copyOf.get(i + 5));

            double areaABC = calcTriangleArea(segmentAC, segmentBC, segmentAB);

            //считаем площадь треугольников (3 штуки), которые получились в результате соединения
            // каждой вершины треугольника с "точкой-городом":
            double segmentAD = calcSegment(copyOf.get(i), cordinateX, copyOf.get(i + 1), cordinateY);
            double segmentBD = calcSegment(copyOf.get(i + 2), cordinateX, copyOf.get(i + 3), cordinateY);
            double segmentCD = calcSegment(copyOf.get(i + 4), cordinateX, copyOf.get(i + 5), cordinateY);
            double areaABD = calcTriangleArea(segmentAD, segmentAB, segmentBD);
            double areaBDC = calcTriangleArea(segmentBD, segmentBC, segmentCD);
            double areaACD = calcTriangleArea(segmentAD, segmentCD, segmentAC);
            //вычисляем сумму площадей трех треугольников, рассчитанных на предыдущем шаге:
            double sumOfAreas = areaABD + areaBDC + areaACD;
            //сравниваем сумму площадей трех треугольников с площадью треугольника АВС:
            if (Math.round(sumOfAreas * 100.0) / 100.0 > Math.round(areaABC * 100.0) / 100.0 || Math.round(areaABD * 100.0) / 100.0 == 0 || Math.round(areaACD * 100.0) / 100.0 == 0 || Math.round(areaBDC * 100.0) / 100.0 == 0) {
                continue;
            } else {
                result.set(0, result.getFirst() + 1);
                result.add(mainCounter);
            }
        }
        return result;
    }

    public static void printResult(ArrayList<Integer> arrayList) {
        System.out.println(arrayList.getFirst());
        for (int i = 1; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
    }

}
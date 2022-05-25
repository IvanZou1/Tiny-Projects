package classicalgorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/*
 *
 * ClosestPair - Find a pair of points, (x1, y2) and (x2, y2), with the smallest
 * distance between them given n points in the Cartesian coordinate plane
 *
 */

public class ClosestPair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the Closest Pair of Points!");
        System.out.print("Input the number of points in the plane: ");
        int n = sc.nextInt();
        checkIfInvalid(n);
        Point[] points = new Point[n];
        System.out.println("Input the " + n + " points in the plane: ");
        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + ") ");
            System.out.print("x = ");
            int x = sc.nextInt();
            System.out.print("y = ");
            int y = sc.nextInt();
            points[i] = new Point(x, y);
        }
        sc.close();
        System.out.println("__________________________________________________");
        System.out.println("Points in the Plane: ");
        printPoints(points);
        System.out.println("__________________________________________________");
        System.out.println("The Closest Pair: ");
        Point[] bruteForce = bruteForce(points, points);
        Point[] divideAndConquer = closestPair(points);
        System.out.println("Using Brute Force (O(n^2)): " +
                bruteForce[0] + " and " + bruteForce[1]);
        System.out.println("The Distance is " + distance(bruteForce[0], bruteForce[1]));
        System.out.println("\nUsing Divide-and-Conquer (O(nlogn)): " +
                divideAndConquer[0] + " and " + divideAndConquer[1]);
        System.out.println("The Distance is " + distance(divideAndConquer[0], divideAndConquer[1]));
    }

    private static void checkIfInvalid(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException("Invalid Input: Please Input a Number > 1");
        }
    }

    private static void printPoints(Point[] points) {
        int i = 1;
        for (Point point : points) {
            System.out.print(point + ", ");
            if (i % 15 == 0) {
                System.out.println();
            }
            i++;
        }
        System.out.println();
    }

    // O(n^2)
    private static Point[] bruteForce(Point[] pX, Point[] pY) {
        Point[] closestPair = new Point[2];
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < pX.length - 1; i++) {
            for (int j = i + 1; j < pX.length; j++) {
                Point pX1 = pX[i], pX2 = pX[j];
                Point pY1 = pY[i], pY2 = pY[j];
                double currDistanceX = distance(pX1, pX2);
                double currDistanceY = distance(pY1, pY2);
                if (currDistanceX < minDistance) {
                    minDistance = currDistanceX;
                    closestPair[0] = pX1;
                    closestPair[1] = pX2;
                }

                if (currDistanceY < minDistance) {
                    minDistance = currDistanceY;
                    closestPair[0] = pY1;
                    closestPair[1] = pY2;
                }
            }
        }
        return closestPair;
    }

    // O(1)
    private static double distance(Point p1, Point p2) {
        int xSq = (int) Math.pow(p1.x - p2.x, 2);
        int ySq = (int) Math.pow(p1.y - p2.y, 2);
        return Math.sqrt(xSq + ySq);
    }

    // O(nlogn)
    private static Point[] closestPair(Point[] points) {
        int n = points.length;
        Point[] sortedX = Arrays.copyOf(points, n);
        Point[] sortedY = Arrays.copyOf(points, n);
        Arrays.sort(sortedX, Comparator.comparingInt(p -> p.x));
        Arrays.sort(sortedY, Comparator.comparingInt(p -> p.y));
        return closest(sortedX, sortedY);
    }

    // O(nlogn)
    private static Point[] closest(Point[] pX, Point[] pY) {
        int n = pX.length;
        if (n <= 3) {
            return bruteForce(pX, pY);
        } else {
            Point[] closestPair;
            double minDistance;
            int median = pX.length / 2;

            Point[] pXLeft = new Point[median];
            Point[] pXRight = new Point[n - median];

            Point[] pYLeft = new Point[median];
            Point[] pYRight = new Point[n - median];

            System.arraycopy(pX, 0, pXLeft, 0, median);
            System.arraycopy(pX, median, pXRight, 0, n - median);

            System.arraycopy(pY, 0, pYLeft, 0, median);
            System.arraycopy(pY, median, pYRight, 0, n - median);

            Point[] leftPair = closest(pXLeft, pYLeft);
            double leftMinDistance = distance(leftPair[0], leftPair[1]);

            Point[] rightPair = closest(pXRight, pYRight);
            double rightMinDistance = distance(rightPair[0], rightPair[1]);

            if (leftMinDistance < rightMinDistance) {
                closestPair = leftPair;
                minDistance = leftMinDistance;
            } else {
                closestPair = rightPair;
                minDistance = rightMinDistance;
            }

            LinkedList<Point> yStrip = new LinkedList<>();
            double leftBoundary = pX[median].x - minDistance;
            double rightBoundary = pX[median].x + minDistance;
            for (int i = 0; i < n; i++) {
                if (pY[i].x > leftBoundary && pY[i].x < rightBoundary) {
                    yStrip.add(pY[i]);
                }
            }

            int m = yStrip.size();
            for (int i = 0; i < m - 1; i++) {
                for (int j = i + 1; j < Math.min(m, i + 7); j++) {
                    Point p1 = yStrip.get(i), p2 = yStrip.get(j);
                    double currDistance = distance(p1, p2);
                    if (currDistance < minDistance) {
                        minDistance = currDistance;
                        closestPair[0] = p1;
                        closestPair[1] = p2;
                    }
                }
            }
            return closestPair;
        }
    }
}

class Point {
    final int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
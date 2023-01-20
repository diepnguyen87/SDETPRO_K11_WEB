package tests;

public class CheckTriangle {

    public static int checkTriangle(Point point1, Point point2, Point point3){
        //Area of Triangle = 1/2 (x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2))
        int area = point1.getX() * (point2.getY() - point3.getY()) +
                point2.getX() * (point3.getY() - point1.getY()) +
                point3.getX() * (point1.getY() - point2.getY());
        return area/2;
    }
    public static void main(String[] args) {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 4);
        Point point3 = new Point(1, 5);

        int area = checkTriangle(point1, point2, point3);
        if(area == 0){
            System.out.println("NO");
        }else{
            System.out.println("YES");
        }
    }
}

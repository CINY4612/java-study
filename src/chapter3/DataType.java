package chapter3;

public class DataType {
    public static void main(String[] args) {
        int num1 = 9;
        long num2 = 2200000000L;
        float num3 = -3.4F;
        double num4 = 1.7;

        System.out.println(num1);   // 출력값 : 9
        System.out.println(++num1); // 출력값 : 10
        System.out.println(num1);   // 출력값 : 10
        System.out.println(num1++); // 출력값 : 10
        System.out.println(num1);   // 출력값 : 11
    }
}

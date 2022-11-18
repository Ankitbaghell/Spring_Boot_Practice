
import java.util.*;

class HelloWorld {
    static void printAllOdd(List<Integer> numbers){
        numbers.stream().filter(number->number%2 != 0).forEach(System.out::println);
    }

    static void printCourses(List<String> courses){
        courses.stream().forEach(System.out::println);
    }

    static void printSpringCourses(List<String> courses){
        courses.stream().filter(HelloWorld :: check).forEach(System.out::println);
    }

    static boolean check(String x){
        return x.indexOf("Spring") != -1;
    }

    static void printStringFour(List<String> courses){
        courses.stream().filter(course->course.length() >= 4).forEach(System.out::println);
    }

    static void printSquares(List<Integer> numbers){
        numbers.stream().filter(number->number%2==0).forEach(HelloWorld::printsq);
    }

    static void printsq(int x){
        System.out.println(x*x);
    }


    // print Squares by using map

    static void printEvenNoSquares(List<Integer> numbers){
        numbers.stream().filter(number->number%2==0).map(number->number*number).forEach(System.out::println);
    }

    static void printOddNoCubes(List<Integer> numbers){
        numbers.stream().filter(number->number%2!=0).map(number->number*number*number).forEach(System.out::println);
    }

    static void printNumberOfCharacters(List<String> courses){
        courses.stream().map(course->course.length()).forEach(System.out::println);
    }

    static void multiplyByFive(List<Integer> numbers){
        numbers.stream().map(number->5*number).forEach(System.out::println);
    }


    public static void main(String[] args) {
        // printAllOdd(List.of(11, 12, 13, 14, 15, 16, 18, 19));

        // printCourses(List.of("Spring", "Spring Boot", "Java", "Python"));


        //  printSpringCourses(List.of("Spring", "Spring Boot", "Java", "Python"));

        //  printStringFour(List.of("Spring", "Spring Boot", "Java", "Python", "c", "C++"));

        //  printSquares(List.of(2, 4, 6, 11, 12, 13, 14, 15, 16, 18, 19));

        // printEvenNoSquares(List.of(2, 4, 6, 11, 12, 13, 14, 15, 16, 18, 19));

        // printOddNoCubes(List.of(1,2,3,4,5,6,7,8));

        // printNumberOfCharacters(List.of("Spring", "Spring Boot", "Java", "Python", "c", "C++"));

        multiplyByFive(List.of(2, 4, 6, 11, 12, 13, 14, 15, 16, 18, 19));


    }
}


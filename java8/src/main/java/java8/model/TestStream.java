package java8.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by wuys on 2017/9/18.
 */
public class TestStream {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

       /* List<String> collect = menu.stream().filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        System.out.println(collect);*/
       /* List<String> collect = menu.stream().filter(d -> {
            System.out.println("filtering" + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("mapping" + d.getName());
            return d.getName();
        }).limit(3).collect(toList());
        System.out.println(collect);*/
       /* long count = menu.stream().filter(d -> d.getCalories() > 500).distinct().count();
        System.out.println(count);*/
        /*List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes);*/
       /* List<Dish> dishes =
                menu.stream()
                        .filter(d -> d.getType() == Dish.Type.MEAT)
                        .limit(2)
                        .collect(toList());
        System.out.println(dishes);*/
        /*List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);*/
      /*  List<String[]> collect = menu.stream().map(d -> d.getName().split("")).distinct().collect(toList());
        System.out.println(collect);*/
       /* List<String[]> collect = menu.stream().map(d -> d.getName()).map(m -> m.split("")).distinct().collect(toList());
        System.out.println(collect);*/
       
    }

}

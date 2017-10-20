package java8.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by wuys on 2017/9/18.
 */
public class TestStream {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

        /*
         * List<String> collect = menu.stream().filter(d -> d.getCalories() >
         * 300) .map(Dish::getName) .limit(3) .collect(toList());
         * System.out.println(collect);
         */
        /*
         * List<String> collect = menu.stream().filter(d -> {
         * System.out.println("filtering" + d.getName()); return d.getCalories()
         * > 300; }).map(d -> { System.out.println("mapping" + d.getName());
         * return d.getName(); }).limit(3).collect(toList());
         * System.out.println(collect);
         */
        /*
         * long count = menu.stream().filter(d -> d.getCalories() >
         * 500).distinct().count(); System.out.println(count);
         */
        /*
         * List<Dish> dishes = menu.stream() .filter(d -> d.getCalories() > 300)
         * .skip(2) .collect(toList()); System.out.println(dishes);
         */
        /*
         * List<Dish> dishes = menu.stream() .filter(d -> d.getType() ==
         * Dish.Type.MEAT) .limit(2) .collect(toList());
         * System.out.println(dishes);
         */
        /*
         * List<Integer> dishNameLengths = menu.stream() .map(Dish::getName)
         * .map(String::length) .collect(toList());
         * System.out.println(dishNameLengths);
         */
        /*
         * List<String[]> collect = menu.stream().map(d ->
         * d.getName().split("")).distinct().collect(toList());
         * System.out.println(collect);
         */
        /*
         * List<String[]> collect = menu.stream().map(d -> d.getName()).map(m ->
         * m.split("")).distinct().collect(toList());
         * System.out.println(collect);
         */
        /*
         * List<String> collect = menu.stream().map(d -> d.getName().split(""))
         * .flatMap(Arrays::stream) .distinct().collect(Collectors.toList());
         * System.out.println(collect);
         */

        /*
         * List<Integer> numbers = Arrays.asList(1,2,3,4,5); List<Integer>
         * collect = numbers.stream().map(n -> n * n).collect(toList());
         * System.out.println(collect);
         */

        /*
         * List<Integer> numbers1 = Arrays.asList(1, 2, 3); List<Integer>
         * numbers2 = Arrays.asList(3, 4); List<int[]> collect =
         * numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] {
         * i, j })) .collect(toList()); System.out.println(collect);
         */

       /* List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Stream<int[]>> collect = numbers1.stream()
                .map(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());
        System.out.println(collect);*/
    /*******************************查找和匹配***********************************/
     /*  if(menu.stream().anyMatch(Dish::isVegetarian)){
           System.out.println("The menu is (somewhat) vegetarian friendly!");
       }
        boolean allMatch = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println(allMatch);*/

     /*   Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> first = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        first.ifPresent(f -> System.out.println(f));

        Optional<Integer> any = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findAny();
        any.ifPresent(f -> System.out.println(f));*/

        /************************************归约**********************/
      /*  List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(reduce);
        System.out.println(list.stream().reduce(0,Integer::sum));
        Optional<Integer> reduce1 = list.stream().reduce(Integer::max);
        System.out.println(reduce1.get());

        Integer reduce2 = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        System.out.println(reduce2);
        long count = menu.stream().count();
        System.out.println(count);*/

     /*   Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> transactions1 = transactions.stream().filter(d -> d.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        //交易员都在哪些不同的城市工作过
        List<String> collect = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        Set<String> collect1 = transactions.stream().map(t -> t.getTrader().getCity()).collect(toSet());
        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> cambridge = transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equals("Cambridge"))
                .distinct().sorted(Comparator.comparing(Trader::getName)).collect(toList());
        //返回所有交易员的姓名字符串，按字母顺序排序
        //效率不高
        String reduce = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted().reduce("", (a, b) -> a + b);
        String collect2 = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(joining());
        //有没有交易员是在米兰工作的
        boolean milan = transactions.stream().allMatch(t -> t.getTrader().getCity().equals("Milan"));
        //打印生活在剑桥的交易员的所有交易额
        Integer cambridge1 = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(n -> n.getValue()).reduce(0, Integer::sum);
        //所有交易中，最高的交易额是多少
        Optional<Integer> reduce1 = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        //找到交易额最小的交易
        Optional<Transaction> min = transactions.stream().min(Comparator.comparing(Transaction::getValue));*/

       /* Integer calories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(calories);

        int sum = menu.stream().mapToInt(Dish::getCalories).sum();

        IntStream stream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = stream.boxed();

        OptionalInt optionalInt = menu.stream().mapToInt(Dish::getCalories).max();

        int i = optionalInt.orElse(1);
        System.out.println(i);*/
       /* IntStream stream = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(stream.count());*/
       /*int a = 0;
       Stream<int[]> stream = IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
       Stream<int[]> stream1 = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});*/

       /*******************平方根*************************/
       /* Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        stream.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        Stream<double[]> stream1 = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(
                b -> new double[]{a, b, Math.sqrt(a * a + b * b)}
        )).filter(t -> t[2] % 1 == 0);*/

       /*************************创建流***************************/
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*斐波纳契数列是著名的经典编程练习。下面这个数列就是斐波纳契数列的一部分：0, 1, 1,
                2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和。
        斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：(0, 1),
        (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …
        你的任务是用 iterate 方法生成斐波纳契元组序列中的前20个元素。*/
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0] + t[1]}).limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

}

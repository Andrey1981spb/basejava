package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class MainLambda {

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0,(a,b)->a*10+b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
            Map<Boolean, List<Integer>> map = integers.stream()
                    .collect(partitioningBy(x -> x % 2 == 0, toList()));
            return map.get(map.get(false).size() % 2 != 0);

    }

    public static void main(String[] args) {
        int[] a = {3,3,2,1};
        System.out.println(minValue(a));

        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(3);
        integers.add(2);
        System.out.println(oddOrEven(integers));
    }

}

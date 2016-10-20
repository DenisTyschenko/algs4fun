package com.github.dtyshchenko.algs4fun;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by denis on 10/16/16.
 */
public class ParenthesisCombinationsTest {


    public static void main(String[] args) {
        System.out.println(combParenthesis(-1));
        System.out.println(combParenthesis(0));
        System.out.println(combParenthesis(1));
        System.out.println(combParenthesis(2).size());
        System.out.println(combParenthesis(3).size());
        System.out.println(combParenthesis(4).size());
        System.out.println(combParenthesis(5).size());
        System.out.println(combParenthesis(6).size());
    }
}

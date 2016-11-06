package com.github.tyshchenko.algs4fun.hackerrank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author denis on 11/6/16.
 */
@RunWith(JUnitParamsRunner.class)
public class ContactsTest {

    public Object[] data() {
        Function<Contacts, Contacts> hAdd = cts -> {
            cts.add("hack");
            cts.add("hackerrank");
            cts.add("h");
            return cts;
        };

        Function<Contacts, Contacts> bAdd = cts -> {
            cts.add("bac");
            cts.add("bah");
            return cts;
        };

        return new Object[][]{
                {hAdd, "hac", 2},
                {hAdd, "hak", 0},
                {hAdd, "h", 3},
                {hAdd.andThen(bAdd), "ba", 2},
                {bAdd.andThen(hAdd), "ba", 2},
                {bAdd.andThen(bAdd), "ba", 2},
                {bAdd.andThen(hAdd), "hac", 2},
                {bAdd.andThen(hAdd), "hak", 0},
                {bAdd.andThen(hAdd), "h", 3},
        };
    }


    @Test
    @Parameters(method = "data")
    public void verify(Function<Contacts, Contacts> contacts, String partial, int expected) {
        Contacts inCts = contacts.apply(new Contacts());
        assertThat(inCts.find(partial), is(expected));
    }
}

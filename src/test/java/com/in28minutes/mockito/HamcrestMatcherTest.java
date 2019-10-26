package com.in28minutes.mockito;

import org.hamcrest.Matchers;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class HamcrestMatcherTest {
    @Test
    public void test(){
        List<Integer> scores = Arrays.asList(99,100,101, 105);
        //Scores has 4 items
        assertThat(scores, hasSize(4));

        //every item
        assertThat(scores,everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(190)));

        //String
        assertThat("", isEmptyString());

        //Array
        Integer[] marks = {1,2,3};
        //Test size of the array
        assertThat(marks, Matchers.<Integer>arrayWithSize(3));
        assertThat(marks, arrayContaining(1,2,3));



    }

    //String

}

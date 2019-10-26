package com.in28minutes.mockito;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    public void test(){
        //Spy actual classes
        List arrayListSpy = spy(ArrayList.class);
        assertEquals(0,arrayListSpy.size());
        arrayListSpy.add("Dummy");
        assertEquals(1,arrayListSpy.size());

    }
}

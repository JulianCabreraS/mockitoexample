package com.in28minutes.business;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void letsMockListSizeMethod(){
        //Given
        List listMock = mock(List.class);
        //When - actual method call
        when(listMock.size()).thenReturn(2);

        //Then
        assertEquals(2,listMock.size());
    }

    @Test
    public void letsMockListGet(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenReturn("in28Minutes");

        assertEquals("in28Minutes", listMock.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_thrownAnException(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
        listMock.get(0);

    }
}

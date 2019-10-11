package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMockTest {

      @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    // TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);


    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock(){


        List<String> todos = Arrays.asList("Learng Spring MVC", "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);



        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());

    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD(){

        //Given

        List<String> todos = Arrays.asList("Learng Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);


        //When
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        //Then
        assertThat(filteredTodos.size(), is(2));
    }

    @Test
    public void testDeleteTodosRelatedToSpring_usingBDD(){

        //Given


        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);


        //When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
        //Then
        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");

        //then
        then(todoServiceMock).should().deleteTodo("Learn to Dance");

        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
    }
    @Test
    public void testDeleteTodosRelatedToSpring_usingBDD_argumentCapture(){

        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);


        //When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
        //Then
        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");

        //then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));


    }
}

package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;

import com.sun.org.apache.xpath.internal.Arg;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock(){
        TodoService  todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learng Spring MVC", "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());

    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD(){

        //Given
        TodoService  todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learng Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        //When
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        //Then
        assertThat(filteredTodos.size(), is(2));
    }

    @Test
    public void testDeleteTodosRelatedToSpring_usingBDD(){

        //Given
        TodoService  todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
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

        //Declare argument captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //Given
        TodoService  todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        //When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
        //Then
        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");

        //then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));


    }
}

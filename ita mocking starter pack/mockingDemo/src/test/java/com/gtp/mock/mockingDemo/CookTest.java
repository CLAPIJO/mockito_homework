package com.gtp.mock.mockingDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)


class CookTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Cook cook = new Cook();

    @BeforeEach
    public void initialize(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void readOrder_should_return_currentOrders_when_queuedOrders(){
        cook.readOrder(Collections.singletonList("Pancit Canton Original w/Egg"));
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Order has been added to queue");
    }

    @Test
    public void cookOrder_should_return_currentOrders_when_queuedOrders(){
        cook.readOrder(Collections.singletonList("Pancit Canton Original w/Egg"));
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Order has been added to queue");
    }


}
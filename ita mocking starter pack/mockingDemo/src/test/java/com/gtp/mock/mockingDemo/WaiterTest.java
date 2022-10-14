package com.gtp.mock.mockingDemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class WaiterTest {

    private Waiter waiter;
    @Test
    public void takeOrder_should_return_orders(){
        waiter = new Waiter(Collections.singletonList("Chicken"), null);
        assertThat(waiter.takeOrder("Chicken")).isEqualTo("Took order Chicken");
    }

    @Test
    public void repeatOrder_should_return_current_order(){
        waiter = new Waiter(Collections.singletonList("Chicken"), null);
        assertThat(waiter.repeatOrder()).isEqualTo("Your current order/s :Chicken");
    }

    @Test
    public void relayOrder_should_return_read_one_order() throws InterruptedException {
        Cook mockedCook = Mockito.mock(Cook.class);
        waiter = new Waiter(Collections.singletonList("Chicken"), mockedCook);

        waiter.relayOrder();

        verify(mockedCook,atLeastOnce()).readOrder(any());
    }

    @Test
    public void serveOrder_should_serve_one_order(){
        Cook mockedCook = Mockito.mock(Cook.class);
        when(mockedCook.isBusy()).thenReturn(false);
        when(mockedCook.plateOrder()).thenReturn(Collections.singletonList("Pizza"));

        waiter = new Waiter(Collections.singletonList("Chicken"), mockedCook);
        String returnValue = waiter.serveOrder();

        verify(mockedCook,atLeastOnce()).isBusy();
        verify(mockedCook,atLeastOnce()).plateOrder();
        assertThat(returnValue).isEqualTo("Now serving:Pizza");
    }

    @Test
    public void serveOrder_should_not_serve_when_cook_isBusy(){
        Cook mockedCook = Mockito.mock(Cook.class);
        when(mockedCook.isBusy()).thenReturn(true);

        waiter = new Waiter(Collections.singletonList("Chicken"), mockedCook);
        String returnValue = waiter.serveOrder();

        verify(mockedCook,atLeastOnce()).isBusy();
        assertThat(returnValue).isEqualTo("All orders are not ready yet. Please wait");
    }
}
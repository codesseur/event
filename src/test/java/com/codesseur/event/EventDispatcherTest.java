package com.codesseur.event;

import com.codesseur.Universe;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventDispatcherTest {

  @Mock
  private Consumer<TestEvent1> consumer1;
  @Mock
  private Consumer<TestEvent2> consumer2;

  @Test
  public void sendEvent() {
    Universe universe = new Universe();
    ConditionalEventListener.forType(TestEvent1.class).listen(consumer1).register(universe);
    ConditionalEventListener.forType(TestEvent2.class).listen(consumer2).register(universe);

    TestEvent1 event = new TestEvent1();
    universe.post(event);

    Mockito.verify(consumer1).accept(event);
    Mockito.verify(consumer2, Mockito.never()).accept(Mockito.any());
  }
}
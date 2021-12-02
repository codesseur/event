package com.codesseur.event;

import com.codesseur.Universe;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void setUp() {
    Universe.get().clear();
  }

  @Test
  public void sendEvent() {
    ConditionalEventListener.forType(TestEvent1.class).listen(consumer1).register();
    ConditionalEventListener.forType(TestEvent2.class).listen(consumer2).register();

    TestEvent1 event = new TestEvent1();
    Universe.get().post(event);

    Mockito.verify(consumer1).accept(event);
    Mockito.verify(consumer2, Mockito.never()).accept(Mockito.any());
  }

  @Test
  public void sendEventThenUnsubscribe() {
    ListenerSubscription subscription = ConditionalEventListener.forType(TestEvent1.class).listen(consumer1).register();
    TestEvent1 event = new TestEvent1();

    Universe.get().post(event);
    subscription.unsubscribe();
    Universe.get().post(event);

    Mockito.verify(consumer1, Mockito.times(1)).accept(Mockito.any());
  }

  @Test
  public void eventStream() {
    ConditionalEventListener.forType(TestEvent1.class).listen(consumer1).register();
    TestEvent1 event = new TestEvent1();
    EventStream eventStream = () -> List.of(event);

    Assertions.assertThat((Stream<Event>) eventStream.eventSequence()).containsExactly(event);

    eventStream.publishEvents();
    Mockito.verify(consumer1, Mockito.times(1)).accept(Mockito.any());
  }
}
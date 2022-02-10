package com.codesseur.event;

import com.codesseur.iterate.Streamed;
import java.util.function.UnaryOperator;

public interface EnhancedEventStream<T extends EnhancedEventStream<T>> extends EventStream {

  default T withEvents(UnaryOperator<Streamed<Event>> events) {
    return withEvents(events.apply(eventSequence()));
  }

  T withEvents(Iterable<? extends Event> events);

}

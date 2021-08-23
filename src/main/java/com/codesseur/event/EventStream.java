package com.codesseur.event;

import com.codesseur.Universe;
import com.codesseur.iterate.container.Sequence;
import java.util.List;

public interface EventStream {

  List<Event> events();

  default Sequence<Event> eventSequence() {
    return Sequence.of(events());
  }

  default void publishEvents() {
    events().forEach(Universe.get()::post);
  }

}

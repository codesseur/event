package net.sfr.tv.event;

import java.util.Collections;
import java.util.List;
import net.sfr.tv.Universe;
import net.sfr.tv.mixin.iterate.Streamed;
import org.immutables.value.Value;

public interface EventStream {

  @Value.Default
  default List<Event> events() {
    return Collections.emptyList();
  }

  default Streamed<Event> eventStream() {
    return Streamed.from(events());
  }

  default void publishEvents() {
    events().forEach(Universe.get()::post);
  }

}

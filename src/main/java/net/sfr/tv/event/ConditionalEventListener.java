package net.sfr.tv.event;

import java.util.function.Consumer;
import java.util.function.Predicate;
import net.sfr.tv.Universe;

public class ConditionalEventListener<T extends Event> implements EventListener<T> {

  private final Predicate<Event> matcher;
  private final EventListener<T> consumer;

  public ConditionalEventListener(Predicate<Event> matcher, EventListener<T> consumer) {
    this.matcher = matcher;
    this.consumer = consumer;
  }

  public static <T extends Event> Builder<T> forType(Class<T> type) {
    return matching(type::isInstance);
  }

  public static <T extends Event> Builder<T> matching(Predicate<Event> matcher) {
    return new Builder<>(matcher);
  }

  @Override
  public void on(T event) {
    if (match(event)) {
      consumer.on(event);
    }
  }

  private boolean match(Event event) {
    return matcher.test(event);
  }

  public static class Builder<T extends Event> {

    private final Predicate<Event> matcher;
    private EventListener<T> eventListener;

    public Builder(Predicate<Event> matcher) {
      this.matcher = matcher;
    }

    public Builder<T> listen(Consumer<T> consumer) {
      eventListener = new ConditionalEventListener<>(matcher, consumer::accept);
      return this;
    }

    public void register(Universe universe) {
      universe.register(eventListener);
    }

    public void register() {
      register(Universe.get());
    }

  }
}

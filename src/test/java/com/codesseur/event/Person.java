package com.codesseur.event;

import com.codesseur.iterate.Streamed;
import java.util.ArrayList;
import java.util.List;

public class Person implements EnhancedEventStream<Person> {

  private final List<Event> events;

  public Person() {
    this(new ArrayList<>());
  }

  public Person(List<Event> events) {
    this.events = events;
  }

  @Override
  public List<Event> events() {
    return events;
  }

  @Override
  public Person withEvents(Iterable<? extends Event> events) {
    return new Person(Streamed.of(events).safeCast(Event.class).toList());
  }
}

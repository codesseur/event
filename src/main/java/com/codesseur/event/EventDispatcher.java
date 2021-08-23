package com.codesseur.event;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDispatcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcher.class);

  private final List<EventListener<Event>> listeners = new ArrayList<>();

  public void send(Event event) {
    LOGGER.debug("dispatching event : {}", event);
    listeners.forEach(listener -> listener.on(event));
  }

  public void register(EventListener<? extends Event> listener) {
    listeners.add((EventListener<Event>) listener);
  }

}

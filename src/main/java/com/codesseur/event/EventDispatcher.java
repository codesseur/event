package com.codesseur.event;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDispatcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcher.class);

  private final Map<UUID, EventListener<Event>> listeners = new ConcurrentHashMap<>();

  public void send(Event event) {
    LOGGER.debug("dispatching event : {}", event);
    listeners.forEach((ticket, listener) -> listener.on(event));
  }

  public ListenerSubscription register(EventListener<? extends Event> listener) {
    UUID ticket = UUID.randomUUID();
    listeners.put(ticket, (EventListener<Event>) listener);
    return () -> listeners.remove(ticket);
  }

  public void clear() {
    listeners.clear();
  }

}

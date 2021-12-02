package com.codesseur.dialog;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Audience {

  private final Map<UUID, Responder<Question>> responders = new ConcurrentHashMap<>();

  public Answer ask(Question question) {
    return Answer.of(responders.values().stream().map(r -> r.ask(question)));
  }

  public ResponderSubscription register(Responder<? extends Question> responder) {
    UUID ticket = UUID.randomUUID();
    responders.put(ticket, (Responder<Question>) responder);
    return () -> responders.remove(ticket);
  }

  public void clear() {
    responders.clear();
  }
}
